package com.ziyao.ideal.data.redis.core;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ziyao zhang
 *
 */
public class Container {

    private static final Comparator<String> COMPARATOR = Comparator.nullsFirst(Comparator.naturalOrder());

    private final NavigableMap<String, Object> data = new TreeMap<>(COMPARATOR);

    Container(Map<String, Object> data) {

        Assert.notNull(data, "Initial data must not be null");
        this.data.putAll(data);
    }

    public void put(String path, @Nullable Object value) {

        Assert.hasText(path, "Path to property must not be null or empty");
        data.put(path, value);
    }

    public void remove(String path) {

        Assert.hasText(path, "Path to property must not be null or empty");
        data.remove(path);
    }

    @Nullable
    public Object get(String path) {

        Assert.hasText(path, "Path to property must not be null or empty");
        return data.get(path);
    }

    public boolean hasValue(String path) {
        return get(path) != null;
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return data.entrySet();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public Collection<Object> values() {
        return data.values();
    }

    public Set<String> keySet() {
        return data.keySet();
    }

    public Map<String, Object> asMap() {
        return Collections.unmodifiableMap(this.data);
    }

    public static Container newBucketFromRawMap(Map<String, Object> source) {
        return new Container(source);
    }

    public Container extract(String prefix) {
        return new Container(data.subMap(prefix, prefix + Character.MAX_VALUE));
    }

    public Set<String> extractAllKeysFor(String path) {

        if (!StringUtils.hasText(path)) {
            return keySet();
        }

        Pattern pattern = Pattern.compile("^(" + Pattern.quote(path) + ")\\.\\[.*?]");

        Set<String> keys = new LinkedHashSet<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {

            Matcher matcher = pattern.matcher(entry.getKey());
            if (matcher.find()) {
                keys.add(matcher.group());
            }
        }

        return keys;
    }


    public ContainerPropertyPath getPath() {
        return ContainerPropertyPath.from(this);
    }

    public ContainerPropertyPath getPropertyPath(String property) {
        return ContainerPropertyPath.from(this, property);
    }


    public static class ContainerPropertyPath {

        @Getter
        private final Container container;
        private final String prefix;

        private ContainerPropertyPath(Container container, String prefix) {

            Assert.notNull(container, "Bucket must not be null");

            this.container = container;
            this.prefix = prefix;
        }

        public static ContainerPropertyPath from(Container container) {
            return new ContainerPropertyPath(container, null);
        }

        public static ContainerPropertyPath from(Container container, @Nullable String prefix) {
            return new ContainerPropertyPath(container, prefix);
        }

        public Object get(String key) {
            return container.get(getPath(key));
        }

        public void put(String key, Object value) {
            container.put(getPath(key), value);
        }


        private String getPath(String key) {
            return StringUtils.hasText(prefix) ? prefix + "." + key : key;
        }

        @Nullable
        public String getPrefix() {
            return this.prefix;
        }
    }
}
