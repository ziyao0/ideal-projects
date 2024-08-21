package com.ziyao.ideal.config.core;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.crypto.Property;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 * @author ziyao zhang
 */
public class OriginTrackedYamlProcessor extends YamlProcessor {

    public OriginTrackedYamlProcessor(String yamlContent) {
        setYamlContent(yamlContent);
    }


    public OriginTrackedYamlProcessor(InputStream inputStream) {
        setStreams(inputStream);
    }

    public OriginTrackedYamlProcessor(List<Property> properties) {
        setProperties(properties);
    }

    @Override
    protected Yaml createYaml() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setAllowRecursiveKeys(true);
        return createYaml(loaderOptions);
    }

    private Yaml createYaml(LoaderOptions loaderOptions) {
        BaseConstructor constructor = new OriginTrackingConstructor(loaderOptions);
        DumperOptions dumperOptions = new DumperOptions();
        Representer representer = new Representer(dumperOptions);
        NoTimestampResolver resolver = new NoTimestampResolver();
        return new Yaml(constructor, representer, dumperOptions, loaderOptions, resolver);
    }

    public List<Map<String, Object>> load() {
        List<Map<String, Object>> result = new ArrayList<>();
        process((properties, map) -> result.add(getFlattenedMap(map)));
        return result;
    }

    @NonNull
    public String resolve() {
        return process();
    }

    /**
     * {@link Constructor} that tracks property origins.
     */
    private static class OriginTrackingConstructor extends SafeConstructor {

        OriginTrackingConstructor(LoaderOptions loadingConfig) {
            super(loadingConfig);
        }

        @Override
        public Object getData() throws NoSuchElementException {
            Object data = super.getData();
            if (data instanceof CharSequence && !Strings.hasLength(((CharSequence) data))) {
                return null;
            }
            return data;
        }


    }

    /**
     * {@link Resolver} that limits {@link Tag#TIMESTAMP} tags.
     */
    private static class NoTimestampResolver extends Resolver {

        @Override
        public void addImplicitResolver(Tag tag, Pattern regexp, String first, int limit) {
            if (tag == Tag.TIMESTAMP) {
                return;
            }
            super.addImplicitResolver(tag, regexp, first, limit);
        }

    }

}
