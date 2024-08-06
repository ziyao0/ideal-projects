package com.ziyao.ideal.config.core;

import com.ziyao.ideal.crypto.Property;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Dates;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.io.IOUtils;
import com.ziyao.ideal.core.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.reader.UnicodeReader;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ziyao zhang
 */
public abstract class YamlProcessor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ResolutionMethod resolutionMethod = ResolutionMethod.OVERRIDE;
    private InputStream[] streams = new InputStream[0];
    @Setter
    private List<Property> properties = new ArrayList<>();
    private List<DocumentMatcher> documentMatchers = Collections.emptyList();
    @Getter
    @Setter
    private String yamlContent = null;

    /**
     * -- SETTER --
     * 标志，指示一个文档，其中所有
     */
    @Setter
    private boolean matchDefault = true;

    private Set<String> supportedTypes = Collections.emptySet();


    /**
     * 文档匹配器映射，允许调用方有选择地仅使用 YAML 资源中的部分文档。
     * 在 YAML 中，文档由 {@code ---} 行分隔，并且每个文档在进行匹配之前都会转换为属性。
     * <pre class="code">
     * environment: dev
     * url: https://dev.bar.com
     * name: Developer Setup
     * ---
     * environment: prod
     * url:https://foo.bar.com
     * name: My Cool App
     * </pre>
     * when mapped with
     * <pre class="code">
     * setDocumentMatchers(properties -&gt;
     *     ("prod".equals(properties.getProperty("environment")) ? MatchStatus.FOUND : MatchStatus.NOT_FOUND));
     * </pre>
     * would end up as
     * <pre class="code">
     * environment=prod
     * url=https://foo.bar.com
     * name=My Cool App
     * </pre>
     */
    public void setDocumentMatchers(DocumentMatcher... matchers) {
        this.documentMatchers = List.of(matchers);
    }

    /**
     * 用于解决资源问题的方法。每个资源都将转换为 Map，
     * 因此此属性用于决定在此工厂的最终输出中保留哪些 map 条目。
     * 默认值为 {@link ResolutionMethod#OVERRIDE}.
     */
    public void setResolutionMethod(ResolutionMethod resolutionMethod) {
        Assert.notNull(resolutionMethod, "ResolutionMethod must not be null");
        this.resolutionMethod = resolutionMethod;
    }

    public void setStreams(InputStream... streams) {
        this.streams = streams;
    }

    /**
     * 设置可以从 YAML 文档加载的受支持类型。
     * <p>
     * 如果未配置受支持的类型，则仅支持 YAML 文档中遇到的 Java 标准类
     * （定义见 {@link org.yaml.snakeyaml.constructor.SafeConstructor}）。
     * 如果遇到不支持的类型，在处理对应的 YAML 节点时，将抛出 {@link IllegalStateException}。
     *
     * @param supportedTypes 支持的类型，或使用空数组来清除支持的类型
     * @see #createYaml()
     */
    public void setSupportedTypes(Class<?>... supportedTypes) {
        if (Objects.isNull(supportedTypes)) {
            this.supportedTypes = Collections.emptySet();
        } else {
            Assert.noNullElements(supportedTypes, "'supportedTypes' must not contain null elements");
            this.supportedTypes = Arrays.stream(supportedTypes).map(Class::getName)
                    .collect(Collectors.toUnmodifiableSet());
        }
    }

    /**
     * 为子类提供一个机会来处理从提供的资源中解析的 Yaml。
     * 每个资源都会依次进行解析，并且其中的文档会根据
     * {@link #setDocumentMatchers（DocumentMatcher...） 匹配器} 进行检查。
     * 如果文档匹配，则将其连同其作为 Properties 的表示形式一起传递到回调中。
     * 根据 {@link #setResolutionMethod（ResolutionMethod）} 的不同，并非所有文档都会被解析。
     *
     * @param callback 找到匹配的文档后委托的回调
     * @see #createYaml()
     */
    protected void process(MatchCallback callback) {
        Yaml yaml = createYaml();
        if (this.yamlContent != null) {
            boolean found = process(callback, yaml, this.yamlContent);
        } else {
            for (InputStream inputStream : this.streams) {
                boolean found = process(callback, yaml, inputStream);
                if (this.resolutionMethod == ResolutionMethod.FIRST_FOUND && found) {
                    return;
                }
            }
        }
    }

    protected String process() {
        Yaml yaml = createYamlBlockFlowStyle();
        Map<String, Object> yamlMap = createYamlMap(this.properties);
        return yaml.dump(yamlMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> createYamlMap(List<Property> properties) {
        Map<String, Object> yamlMap = new LinkedHashMap<>();
        for (Property property : properties) {
            String key = property.getKey();
            Object value = property.getValue();
            Map<String, Object> currentMap = yamlMap;
            String[] keys = Strings.split(key, "\\.");

            for (int i = 0; i < keys.length; i++) {
                String currentKey = keys[i];

                if (currentKey.contains("[")) {
                    String baseKey = currentKey.substring(0, currentKey.indexOf('['));
                    int index = Integer.parseInt(currentKey.substring(currentKey.indexOf('[') + 1, currentKey.indexOf(']')));

                    if (!currentMap.containsKey(baseKey)) {
                        currentMap.put(baseKey, new ArrayList<>());
                    }

                    List<Object> list = (List<Object>) currentMap.get(baseKey);

                    while (list.size() <= index) {
                        list.add(new HashMap<>());
                    }

//                    if (i == keys.length - 1) {
//                        list.get(index).put(keys[++i], value);
//                    } else {
//                        currentMap = list.get(index);
//                    }
                    if (i == keys.length - 1) {
                        // 处理数组终止条件，将值设置到相应位置
                        list.set(index, value);
                    } else {
                        // 处理嵌套结构
                        Object nextElement = list.get(index);
                        if (!(nextElement instanceof Map)) {
                            nextElement = new HashMap<>();
                            list.set(index, nextElement);
                        }
                        currentMap = (Map<String, Object>) nextElement;
                    }

                } else {
                    if (i == keys.length - 1) {
                        currentMap.put(currentKey, value);
                    } else {
                        currentMap = (Map<String, Object>) currentMap.computeIfAbsent(currentKey, k -> new HashMap<>());
                    }
                }
            }
        }
        return yamlMap;
    }

    /**
     * 转换类型
     */
    public Object convert(Object value) {
        if (value instanceof String) {
            return value.toString();
        }
        return null;
    }

    /**
     * 创建要使用的 {@link Yaml} 实例。
     * <p>
     * 默认实现将“allowDuplicateKeys”标志设置为 {@code false}，从而在 SnakeYAML 1.18+ 中启用内置的重复键处理。
     * <p>
     * 从 Spring Framework 5.1.16 开始，如果配置了自定义 {@linkplain #setSupportedTypes 支持的类型}，
     * 则默认实现会创建一个 {@code Yaml} 实例，用于过滤掉 YAML 文档中遇到的不受支持的类型。如果遇到不受支持的类型，
     * 则在处理节点时将抛出 {@link IllegalStateException}。
     *
     * @see LoaderOptions#setAllowDuplicateKeys(boolean)
     */
    protected Yaml createYaml() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        DumperOptions dumperOptions = new DumperOptions();
        return new Yaml(new FilteringConstructor(loaderOptions), new Representer(dumperOptions),
                dumperOptions, loaderOptions);
    }

    protected Yaml createYamlBlockFlowStyle() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        DumperOptions dumperOptions = new DumperOptions();

        dumperOptions.setPrettyFlow(true);
        dumperOptions.setIndent(2);
        dumperOptions.setIndicatorIndent(2);
        dumperOptions.setIndentWithIndicator(true);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        dumperOptions.setTimeZone(Dates.getDefaultTimeZone());
        return new Yaml(new FilteringConstructor(loaderOptions), new Representer(dumperOptions),
                dumperOptions, loaderOptions);
    }

    private boolean process(MatchCallback callback, Yaml yaml, InputStream inputStream) {
        int count = 0;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Loading from YAML: {}", inputStream);
            }
            try (Reader reader = new UnicodeReader(inputStream)) {
                for (Object object : yaml.loadAll(reader)) {
                    if (object != null && process(asMap(object), callback)) {
                        count++;
                        if (this.resolutionMethod == ResolutionMethod.FIRST_FOUND) {
                            break;
                        }
                    }
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Loaded {} document{} from YAML resource: {}", count, count > 1 ? "s" : "", inputStream);
                }
            }
        } catch (IOException ex) {
            handleProcessError(inputStream, ex);
        } finally {
            IOUtils.close(inputStream);
        }
        return (count > 0);
    }

    private boolean process(MatchCallback callback, Yaml yaml, String yamlstring) {
        int count = 0;

        for (Object object : yaml.loadAll(yamlstring)) {
            if (object != null && process(asMap(object), callback)) {
                count++;
                if (this.resolutionMethod == ResolutionMethod.FIRST_FOUND) {
                    break;
                }
            }
        }
        return (count > 0);
    }

    private void handleProcessError(InputStream inputStream, IOException ex) {
        if (this.resolutionMethod != ResolutionMethod.FIRST_FOUND &&
                this.resolutionMethod != ResolutionMethod.OVERRIDE_AND_IGNORE) {
            throw new IllegalStateException(ex);
        }
        if (logger.isWarnEnabled()) {
            logger.warn("Could not load map from " + inputStream + ": " + ex.getMessage());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Map<String, Object> asMap(Object object) {
        // YAML can have numbers as keys
        Map<String, Object> result = new LinkedHashMap<>();
        if (!(object instanceof Map map)) {
            // A document can be a text literal
            result.put("document", object);
            return result;
        }

        map.forEach((key, value) -> {
            if (value instanceof Map) {
                value = asMap(value);
            }
            if (key instanceof CharSequence) {
                result.put(key.toString(), value);
            } else {
                // It has to be a map key in this case
                result.put("[" + key.toString() + "]", value);
            }
        });
        return result;
    }

    private boolean process(Map<String, Object> map, MatchCallback callback) {
        Properties properties = this.createStringAdaptingProperties();
        properties.putAll(getFlattenedMap(map));

        if (this.documentMatchers.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Merging document (no matchers set): " + map);
            }
            callback.process(properties, map);
            return true;
        }

        MatchStatus result = MatchStatus.ABSTAIN;
        for (DocumentMatcher matcher : this.documentMatchers) {
            MatchStatus match = matcher.matches(properties);
            result = MatchStatus.getMostSpecific(match, result);
            if (match == MatchStatus.FOUND) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Matched document with document matcher: " + properties);
                }
                callback.process(properties, map);
                return true;
            }
        }

        if (result == MatchStatus.ABSTAIN && this.matchDefault) {
            if (logger.isDebugEnabled()) {
                logger.debug("Matched document with default matcher: " + map);
            }
            callback.process(properties, map);
            return true;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Unmatched document: {}", map);
        }
        return false;
    }

    private Properties createStringAdaptingProperties() {
        return new SortedProperties(false) {
            @Serial
            private static final long serialVersionUID = 2515427249366884977L;

            @Override
            @Nullable
            public String getProperty(String key) {
                Object value = get(key);
                return (value != null ? value.toString() : null);
            }
        };
    }

    /**
     * 返回给定映射的扁平化版本，递归跟随任何嵌套的 Map 或 Collection 值。
     * 生成的映射中的条目将保持与源相同的顺序。当使用 Map 从 {@link MatchCallback} 调用时，
     * 结果将包含与 {@link MatchCallback} 属性相同的值。
     *
     * @param source 资源map
     * @return 返回keyvalue形式的map
     */
    protected final Map<String, Object> getFlattenedMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<>();
        buildFlattenedMap(result, source, null);
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        source.forEach((key, value) -> {
            if (Strings.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map map) {
                // Need a compound key
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection collection) {
                // Need a compound key
                if (collection.isEmpty()) {
                    result.put(key, "");
                } else {
                    int count = 0;
                    for (Object object : collection) {
                        buildFlattenedMap(result, Collections.singletonMap(
                                "[" + (count++) + "]", object), key);
                    }
                }
            } else {
                result.put(key, (value != null ? value : ""));
            }
        });
    }


    /**
     * 用于处理 YAML 解析结果的回调接口。
     */
    @FunctionalInterface
    public interface MatchCallback {

        /**
         * 处理分析结果的给定表示形式。
         *
         * @param properties 要处理的属性（在集合或映射的情况下，作为带有索引键的扁平化表示形式）
         * @param map        结果映射（保留 YAML 文档中的原始值结构）
         */
        void process(Properties properties, Map<String, Object> map);
    }


    /**
     * 策略接口，用于测试属性是否匹配。
     */
    @FunctionalInterface
    public interface DocumentMatcher {

        /**
         * 测试给定的属性是否匹配
         *
         * @param properties 要测试的属性
         * @return 匹配状态
         */
        MatchStatus matches(Properties properties);
    }


    /**
     * 返回匹配状态 {@link DocumentMatcher#matches(java.util.Properties)}.
     */
    public enum MatchStatus {

        /**
         * 找到匹配项
         */
        FOUND,

        /**
         * 没有找到匹配项
         */
        NOT_FOUND,

        /**
         * 不考虑匹配器
         */
        ABSTAIN;

        /**
         * 比较两个 {@link MatchStatus} 项，返回最具体的状态。
         */
        public static MatchStatus getMostSpecific(MatchStatus a, MatchStatus b) {
            return (a.ordinal() < b.ordinal() ? a : b);
        }
    }


    /**
     * 用于解决资源问题的方法。
     */
    public enum ResolutionMethod {

        /**
         * 替换列表中前面的值。
         */
        OVERRIDE,

        /**
         * 替换列表中前面的值，忽略任何失败。
         */
        OVERRIDE_AND_IGNORE,

        /**
         * 选择列表中存在的第一个资源，并仅使用该资源。
         */
        FIRST_FOUND
    }


    /**
     * {@link Constructor}，支持过滤不受支持的类型。
     * <p>
     * 如果在 YAML 文档中遇到不受支持的类型，
     * 将从 {@link #getClassForName} 抛出 {@link IllegalStateException}。
     */
    private class FilteringConstructor extends Constructor {

        FilteringConstructor(LoaderOptions loaderOptions) {
            super(loaderOptions);
        }

        @Override
        protected Class<?> getClassForName(String name) throws ClassNotFoundException {
            Assert.state(YamlProcessor.this.supportedTypes.contains(name),
                    () -> "Unsupported type encountered in YAML document: " + name);
            return super.getClassForName(name);
        }
    }

    public static Yaml getDefaultYamlInstance() {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setIndent(2);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        dumperOptions.setTimeZone(Dates.getDefaultTimeZone());
        return new Yaml(dumperOptions);
    }

}
