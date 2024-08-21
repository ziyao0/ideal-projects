package com.ziyao.ideal.data.redis.core.convert;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.data.redis.core.Container;
import com.ziyao.ideal.data.redis.core.RedisRawData;
import org.springframework.core.CollectionFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.data.mapping.PropertyHandler;
import org.springframework.data.mapping.model.EntityInstantiators;
import org.springframework.data.mapping.model.PersistentEntityParameterValueProvider;
import org.springframework.data.mapping.model.PropertyValueProvider;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.redis.core.convert.DefaultRedisTypeMapper;
import org.springframework.data.redis.core.convert.Jsr310Converters;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;
import org.springframework.data.redis.core.mapping.RedisPersistentProperty;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.ProxyUtils;
import org.springframework.data.util.TypeInformation;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ziyao zhang
 */
final class ObjectConversionProvider implements ConversionProvider {

    private static final String INVALID_TYPE_ASSIGNMENT = "Value of type %s cannot be assigned to property %s of type %s";


    private final ObjectMapper mapper;
    private final GenericConversionService converters;
    private final BoostRedisTypeMapper typeMapper;
    private final RedisMappingContext mappingContext = new RedisMappingContext();
    private final EntityInstantiators instantiators = new EntityInstantiators();
    private final SimpleTypeHolder typeHolder;


    public ObjectConversionProvider() {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Module> modules = Jackson2Modules.getModules(classLoader);
        mapper.registerModules(modules);
        this.mapper = mapper;

        this.converters = new DefaultConversionService();

        Collection<Converter<?, ?>> converters = BinaryConverters.getConvertersToRegister();

        converters.addAll(Jsr310Converters.getConvertersToRegister());
        converters.addAll(TimesConverters.getConvertersToRegister());
        converters.add(new BytesToMapConverter());
        converters.add(new MapToBytesConverter());

        for (Converter<?, ?> converter : converters) {
            this.converters.addConverter(converter);
        }
        this.typeMapper = new BoostRedisTypeMapper(DefaultRedisTypeMapper.DEFAULT_TYPE_KEY, this.mappingContext);

        Iterator<Converter<?, ?>> iterator = converters.stream().iterator();

        Set<Class<?>> types = new HashSet<>();

        if (iterator.hasNext()) {
            Converter<?, ?> converter = iterator.next();
            Class<?>[] arguments = GenericTypeResolver.resolveTypeArguments(converter.getClass(), Converter.class);
            assert arguments != null;
            types.add(arguments[1]);
        }
        this.typeHolder = new SimpleTypeHolder(types, true);
    }

    @Override
    public <T> byte[] write(T source) {
        try {
            if (source instanceof String) {
                return this.converters.convert(source, byte[].class);
            }
            return mapper.writeValueAsBytes(source);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize: " + e.getMessage(), e);
        }
    }

    @Override
    public void write(@NonNull Object source, @NonNull RedisRawData sink) {

        RedisPersistentEntity<?> entity = mappingContext.getRequiredPersistentEntity(source.getClass());

        sink.setKeyspace(entity.getKeySpace());

        if (entity.getTypeInformation().isCollectionLike()) {
            writeCollection(entity.getKeySpace(), "", (List) source, entity.getTypeInformation().getRequiredComponentType(),
                    sink);
        } else {
            writeInternal(entity.getKeySpace(), "", source, entity.getTypeInformation(), sink);
        }

        Object identifier = entity.getIdentifierAccessor(source).getIdentifier();

        if (identifier != null) {
            sink.setId(converters.convert(identifier, String.class));
        }

        Long ttl = entity.getTimeToLiveAccessor().getTimeToLive(source);
        if (ttl != null && ttl > 0) {
            sink.setTimeToLive(ttl);
        }

        byte[] raw = this.converters.convert(sink.getContainer().asMap(), byte[].class);
        sink.setRaw(raw);
    }

    @Override
    public <T> T read(Class<T> type, byte[] source) {
        try {
            if (source == null || source.length == 0) {
                return null;
            }
            return mapper.readValue(source, type);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize: " + e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> type, RedisRawData source) {

        Map<String, Object> rawMap = this.converters.convert(source.getRaw(), Map.class);

        RedisRawData newSource = new RedisRawData(rawMap);

        newSource.setId(source.getId());
        newSource.setKeyspace(source.getKeyspace());
        newSource.setTimeToLive(source.getTimeToLive());

        TypeInformation<?> readType = typeMapper.readType(newSource.getContainer().getPath(), ClassTypeInformation.from(type));

        return readType.isCollectionLike()
                ? (T) readCollectionOrArray("", ArrayList.class, Object.class, newSource.getContainer())
                : doReadInternal("", type, newSource);
    }

    @Override
    public <T> T convert(Object source, Class<T> type) {

        if (this.converters.canConvert(type, source.getClass())) {

            return this.converters.convert(source, type);
        }
        throw new IllegalArgumentException(source + "不支持转换为：" + type.getName());
    }


    @Override
    public RedisMappingContext getMappingContext() {
        return this.mappingContext;
    }

    @Override
    public void initializeConverters() {

    }

    @SuppressWarnings("unchecked")
    private <R> R doReadInternal(String path, Class<R> type, RedisRawData source) {

        TypeInformation<?> readType = typeMapper.readType(source.getContainer().getPath(), ClassTypeInformation.from(type));

        if (converters.canConvert(Map.class, readType.getType())) {

            Map<String, Object> partial = new HashMap<>();

            if (!path.isEmpty()) {

                for (Map.Entry<String, Object> entry : source.getContainer().extract(path + ".").entrySet()) {
                    partial.put(entry.getKey().substring(path.length() + 1), entry.getValue());
                }

            } else {
                partial.putAll(source.getContainer().asMap());
            }
            R instance = (R) this.converters.convert(partial, readType.getType());

            RedisPersistentEntity<?> entity = mappingContext.getPersistentEntity(readType);
            if (entity != null && entity.hasIdProperty()) {

                PersistentPropertyAccessor<R> propertyAccessor = entity.getPropertyAccessor(instance);

                propertyAccessor.setProperty(entity.getRequiredIdProperty(), source.getId());
                instance = propertyAccessor.getBean();
            }
            return instance;
        }

        if (this.converters.canConvert(byte[].class, readType.getType())) {
            return (R) this.converters.convert(source.getContainer().get(StringUtils.hasText(path) ? path : "_raw"),
                    readType.getType());
        }

        RedisPersistentEntity<?> entity = mappingContext.getRequiredPersistentEntity(readType);


        Object instance = instantiators.getInstantiatorFor(entity)
                .createInstance((RedisPersistentEntity<RedisPersistentProperty>) entity,
                        new PersistentEntityParameterValueProvider<>(entity,
                                new ConverterAwareParameterValueProvider(path, source, this.converters), this.converters));

        PersistentPropertyAccessor<Object> accessor = entity.getPropertyAccessor(instance);

        entity.doWithProperties((PropertyHandler<RedisPersistentProperty>) persistentProperty -> {
            //TODO jdk8兼容以下注释掉的代码
//
//            InstanceCreatorMetadata<RedisPersistentProperty> creator = entity.getInstanceCreatorMetadata();
//
//            if (creator != null && creator.isCreatorParameter(persistentProperty)) {
//                return;
//            }

            Object targetValue = readProperty(path, source, persistentProperty);

            if (targetValue != null) {
                accessor.setProperty(persistentProperty, targetValue);
            }
        });

        return (R) accessor.getBean();
    }

    private Object readProperty(String path, RedisRawData source, RedisPersistentProperty persistentProperty) {

        String currentPath = !path.isEmpty() ? path + "." + persistentProperty.getName() : persistentProperty.getName();
        TypeInformation<?> typeInformation = typeMapper.readType(source.getContainer().getPropertyPath(currentPath),
                persistentProperty.getTypeInformation());

        if (typeInformation.isMap()) {

            Class<?> mapValueType = null;

            if (typeInformation.getMapValueType() != null) {
                mapValueType = typeInformation.getMapValueType().getType();
            }

            if (mapValueType == null && persistentProperty.isMap()) {
                mapValueType = persistentProperty.getMapValueType();
            }

            if (mapValueType == null) {
                throw new IllegalArgumentException("Unable to retrieve MapValueType");
            }

            if (this.converters.canConvert(byte[].class, mapValueType)) {
                return readMapOfSimpleTypes(currentPath, typeInformation.getType(),
                        typeInformation.getRequiredComponentType().getType(), mapValueType, source);
            }

            return readMapOfComplexTypes(currentPath, typeInformation.getType(),
                    typeInformation.getRequiredComponentType().getType(), mapValueType, source);
        }

        if (typeInformation.isCollectionLike()) {

            if (!isByteArray(typeInformation)) {

                return readCollectionOrArray(currentPath, typeInformation.getType(),
                        typeInformation.getRequiredComponentType().getType(), source.getContainer());
            }

            if (!source.getContainer().hasValue(currentPath) && isByteArray(typeInformation)) {

                return readCollectionOrArray(currentPath, typeInformation.getType(),
                        typeInformation.getRequiredComponentType().getType(), source.getContainer());
            }
        }

        if (mappingContext.getPersistentEntity(typeInformation) != null
                && !converters.canConvert(byte[].class, typeInformation.getRequiredActualType().getType())) {

            Container container = source.getContainer().extract(currentPath + ".");

            RedisRawData newBucket = new RedisRawData(container);

            return readInternal(currentPath, typeInformation.getType(), newBucket);
        }

        Object sourceObject = source.getContainer().get(currentPath);

        if (typeInformation.getType().isPrimitive() && sourceObject == null) {
            return null;
        }

        if (persistentProperty.isIdProperty() && ObjectUtils.isEmpty(path)) {
            return sourceObject != null ? fromObject(sourceObject, typeInformation.getType()) : source.getId();
        }

        if (sourceObject == null) {
            return null;
        }

        if (this.converters.canConvert(byte[].class, persistentProperty.getType())) {
            return fromObject(sourceObject, persistentProperty.getType());
        }

        Class<?> typeToUse = getTypeHint(currentPath, source.getContainer(), persistentProperty.getType());
        return fromObject(sourceObject, typeToUse);
    }

    private Class<?> getTypeHint(String path, Container container, Class<?> fallback) {

        TypeInformation<?> typeInformation = typeMapper.readType(container.getPropertyPath(path),
                ClassTypeInformation.from(fallback));
        return typeInformation.getType();
    }

    private static boolean isByteArray(TypeInformation<?> type) {
        return type.getType().equals(byte[].class);
    }

    @Nullable
    private <R> R readInternal(String path, Class<R> type, RedisRawData source) {
        return source.getContainer().isEmpty() ? null : doReadInternal(path, type, source);
    }


    @Nullable
    private Map<?, ?> readMapOfSimpleTypes(String path, Class<?> mapType, Class<?> keyType, Class<?> valueType,
                                           RedisRawData source) {

        Container partial = source.getContainer().extract(path + ".[");

        Map<Object, Object> target = CollectionFactory.createMap(mapType, partial.size());

        for (Map.Entry<String, Object> entry : partial.entrySet()) {

            if (typeMapper.isTypeKey(entry.getKey())) {
                continue;
            }

            Object key = extractMapKeyForPath(path, entry.getKey(), keyType);
            Class<?> typeToUse = getTypeHint(path + ".[" + key + "]", source.getContainer(), valueType);
            target.put(key, fromObject(entry.getValue(), typeToUse));
        }

        return target.isEmpty() ? null : target;
    }


    @Nullable
    private Map<?, ?> readMapOfComplexTypes(String path, Class<?> mapType, Class<?> keyType, Class<?> valueType,
                                            RedisRawData source) {

        Set<String> keys = source.getContainer().extractAllKeysFor(path);

        Map<Object, Object> target = CollectionFactory.createMap(mapType, keys.size());

        for (String key : keys) {

            Container partial = source.getContainer().extract(key);

            Object mapKey = extractMapKeyForPath(path, key, keyType);

            TypeInformation<?> typeInformation = typeMapper.readType(source.getContainer().getPropertyPath(key),
                    ClassTypeInformation.from(valueType));

            Object o = readInternal(key, typeInformation.getType(), new RedisRawData(partial));
            target.put(mapKey, o);
        }

        return target.isEmpty() ? null : target;
    }

    @Nullable
    private Object extractMapKeyForPath(String path, String key, Class<?> targetType) {

        String regex = "^(" + Pattern.quote(path) + "\\.\\[)(.*?)(])";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(key);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    String.format("Cannot extract map value for key '%s' in path '%s'.", key, path));
        }

        Object mapKey = matcher.group(2);

        if (ClassUtils.isAssignable(targetType, mapKey.getClass())) {
            return mapKey;
        }

        return converters.convert(toBytes(mapKey), targetType);
    }

    public byte[] toBytes(Object source) {

        if (source instanceof byte[]) {
            return (byte[]) source;
        }

        return converters.convert(source, byte[].class);
    }

    @Nullable
    private Object readCollectionOrArray(String path, Class<?> collectionType, Class<?> valueType, Container container) {

        List<String> keys = new ArrayList<>(container.extractAllKeysFor(path));

        boolean isArray = collectionType.isArray();
        Class<?> collectionTypeToUse = isArray ? ArrayList.class : collectionType;
        Collection<Object> target = CollectionFactory.createCollection(collectionTypeToUse, valueType, keys.size());

        for (String key : keys) {

            if (typeMapper.isTypeKey(key)) {
                continue;
            }

            Container elementData = container.extract(key);

            TypeInformation<?> typeInformation = typeMapper.readType(elementData.getPropertyPath(key),
                    ClassTypeInformation.from(valueType));

            Class<?> typeToUse = typeInformation.getType();
            if (this.converters.canConvert(byte[].class, typeToUse)) {
                target.add(fromObject(elementData.get(key), typeToUse));
            } else {
                target.add(readInternal(key, typeToUse, new RedisRawData(elementData)));
            }
        }

        return isArray ? toArray(target, collectionType, valueType) : (target.isEmpty() ? null : target);
    }


    public <T> T fromObject(Object source, Class<T> type) {

        if (type.isInstance(source)) {
            return type.cast(source);
        }

        return this.converters.convert(source, type);
    }

    @Nullable
    private Object toArray(Collection<Object> source, Class<?> arrayType, Class<?> valueType) {

        if (source.isEmpty()) {
            return null;
        }

        if (!ClassUtils.isPrimitiveArray(arrayType)) {
            return source.toArray((Object[]) Array.newInstance(valueType, source.size()));
        }

        Object targetArray = Array.newInstance(valueType, source.size());
        Iterator<Object> iterator = source.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Array.set(targetArray, i, this.converters.convert(iterator.next(), valueType));
            i++;
        }
        return i > 0 ? targetArray : null;
    }


    private void writeInternal(@Nullable String keyspace, String path, @Nullable Object value,
                               TypeInformation<?> typeHint, RedisRawData sink) {

        if (value == null) {
            return;
        }


        if (value.getClass() != typeHint.getType()) {
            typeMapper.writeType(value.getClass(), sink.getContainer().getPropertyPath(path));
        }

        RedisPersistentEntity<?> entity = mappingContext.getRequiredPersistentEntity(value.getClass());
        PersistentPropertyAccessor<Object> accessor = entity.getPropertyAccessor(value);

        entity.doWithProperties((PropertyHandler<RedisPersistentProperty>) persistentProperty -> {

            String propertyStringPath = (!path.isEmpty() ? path + "." : "") + persistentProperty.getName();

            Object propertyValue = accessor.getProperty(persistentProperty);
            if (persistentProperty.isIdProperty()) {

                if (propertyValue != null) {
                    sink.getContainer().put(propertyStringPath, propertyValue);
                }
                return;
            }
            // 写入map
            if (persistentProperty.isMap()) {

                if (propertyValue != null) {
                    writeMap(keyspace, propertyStringPath, persistentProperty.getMapValueType(), (Map<?, ?>) propertyValue, sink);
                }
                // 写入集合
            } else if (persistentProperty.isCollectionLike() && isNotByteArray(persistentProperty)) {

                if (propertyValue == null) {
                    writeCollection(keyspace, propertyStringPath, null,
                            persistentProperty.getTypeInformation().getRequiredComponentType(), sink);
                } else {

                    if (Iterable.class.isAssignableFrom(propertyValue.getClass())) {

                        writeCollection(keyspace, propertyStringPath, (Iterable<?>) propertyValue,
                                persistentProperty.getTypeInformation().getRequiredComponentType(), sink);
                    } else if (propertyValue.getClass().isArray()) {

                        writeCollection(keyspace, propertyStringPath, CollectionUtils.arrayToList(propertyValue),
                                persistentProperty.getTypeInformation().getRequiredComponentType(), sink);
                    } else {

                        throw new RuntimeException("Don't know how to handle " + propertyValue.getClass() + " type collection");
                    }
                }

            } else if (propertyValue != null) {
                // 如果是简单类型
                if (typeHolder.isSimpleType(ProxyUtils.getUserClass(propertyValue.getClass()))) {
                    // 直接写入
                    writeToBucket(propertyStringPath, propertyValue, sink);
                } else {
                    // 如果是复杂类型则执行
                    writeInternal(keyspace, propertyStringPath, propertyValue,
                            persistentProperty.getTypeInformation().getRequiredActualType(), sink);
                }
            }
        });
    }


    private void writeMap(@Nullable String keyspace, String path, Class<?> mapValueType, Map<?, ?> source,
                          RedisRawData sink) {

        if (CollectionUtils.isEmpty(source)) {
            return;
        }

        for (Map.Entry<?, ?> entry : source.entrySet()) {

            if (entry.getValue() == null || entry.getKey() == null) {
                continue;
            }

            String currentPath = path + ".[" + mapMapKey(entry.getKey()) + "]";

            if (!ClassUtils.isAssignable(mapValueType, entry.getValue().getClass())) {
                throw new MappingException(
                        String.format(INVALID_TYPE_ASSIGNMENT, entry.getValue().getClass(), currentPath, mapValueType));
            }

            writeInternal(keyspace, currentPath, entry.getValue(), ClassTypeInformation.from(mapValueType), sink);

        }
    }

    private String mapMapKey(Object key) {

        if (converters.canConvert(key.getClass(), byte[].class)) {
            return Strings.toString(converters.convert(key, byte[].class));
        }

        return converters.convert(key, String.class);
    }

    private static boolean isNotByteArray(RedisPersistentProperty property) {
        return !property.getType().equals(byte[].class);
    }

    private void writeCollection(@Nullable String keyspace, String path, @Nullable Iterable<?> values,
                                 TypeInformation<?> typeHint, RedisRawData sink) {

        if (values == null) {
            return;
        }

        int i = 0;
        for (Object value : values) {

            if (value == null) {
                break;
            }

            String currentPath = path + (path.isEmpty() ? "" : ".") + "[" + i + "]";

            if (!ClassUtils.isAssignable(typeHint.getType(), value.getClass())) {
                throw new MappingException(
                        String.format(INVALID_TYPE_ASSIGNMENT, value.getClass(), currentPath, typeHint.getType()));
            }
            writeInternal(keyspace, currentPath, value, typeHint, sink);
            i++;
        }
    }

    private void writeToBucket(String path, @Nullable Object value, RedisRawData sink) {

        if (value == null || (value instanceof Optional && !((Optional<?>) value).isPresent())) {
            return;
        }

        Class<?> it = value.getClass();

        if (ClassUtils.isAssignable(Map.class, it)) {
            Map<?, ?> map = (Map<?, ?>) converters.convert(value, it);
            if (!CollectionUtils.isEmpty(map)) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    sink.getContainer().put(path + (StringUtils.hasText(path) ? "." : "") + entry.getKey(),
                            entry.getValue());
                }
            }
        } else if (ClassUtils.isAssignable(LocalDateTime.class, it)) {
            sink.getContainer().put(path, converters.convert(value, String.class));
        } else {
            sink.getContainer().put(path, value);
        }

    }

    private class ConverterAwareParameterValueProvider implements PropertyValueProvider<RedisPersistentProperty> {

        private final String path;
        private final RedisRawData source;
        private final ConversionService conversionService;

        ConverterAwareParameterValueProvider(String path, RedisRawData source, ConversionService conversionService) {

            this.path = path;
            this.source = source;
            this.conversionService = conversionService;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getPropertyValue(@NonNull RedisPersistentProperty property) {

            Object value = readProperty(path, source, property);

            if (value == null || ClassUtils.isAssignableValue(property.getType(), value)) {
                return (T) value;
            }

            return (T) conversionService.convert(value, property.getType());
        }
    }
}
