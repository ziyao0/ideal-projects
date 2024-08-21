package com.ziyao.ideal.data.redis.core;

import com.ziyao.ideal.core.Collections;
import lombok.Getter;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author ziyao zhang
 */
@Getter
public class DefaultRepositoryMetadata implements RepositoryMetadata {

    private static final String MUST_BE_A_REPOSITORY = String.format("Given type must be assignable to %s",
            RedisRepository.class);
    private final TypeInformation<?> typeInformation;
    private final Class<?> repositoryInterface;

    private TypeInformation<?> javaTypeInformation;
    private TypeInformation<?> idTypeInformation;

    /**
     * Creates a new {@link DefaultRepositoryMetadata} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public DefaultRepositoryMetadata(Class<?> repositoryInterface) {

        Assert.notNull(repositoryInterface, "Given type must not be null");
        Assert.isTrue(repositoryInterface.isInterface(), "Given type must be an interface");

        this.repositoryInterface = repositoryInterface;
        this.typeInformation = ClassTypeInformation.from(repositoryInterface);

        Assert.isTrue(RedisRepository.class.isAssignableFrom(repositoryInterface), MUST_BE_A_REPOSITORY);


        TypeInformation<?> redisTypeInformation = ClassTypeInformation.from(repositoryInterface)//
                .getSuperTypeInformation(RedisRepository.class);
        if (redisTypeInformation != null) {
            List<TypeInformation<?>> hashArguments = redisTypeInformation.getTypeArguments();
            if (Collections.nonNull(hashArguments)) {
                this.javaTypeInformation = resolveTypeParameter(hashArguments, 0,
                        () -> String.format("Could not resolve id type of %s", repositoryInterface));
                this.idTypeInformation = resolveTypeParameter(hashArguments, 1,
                        () -> String.format("Could not resolve id type of %s", repositoryInterface));
            }
        }
    }


    /**
     * Creates a new {@link com.ziyao.ideal.data.redis.core.RepositoryMetadata} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public static RepositoryMetadata getMetadata(Class<?> repositoryInterface) {

        Assert.notNull(repositoryInterface, "Repository interface must not be null");

        return RedisRepository.class.isAssignableFrom(repositoryInterface) ? new DefaultRepositoryMetadata(repositoryInterface)
                : /*new AnnotationRepositoryMetadata(repositoryInterface)*/ null;
    }


    public TypeInformation<?> getIdTypeInformation() {
        return requireNonNullElseGet(this.idTypeInformation,ClassTypeInformation.from(Object.class));
    }

    public TypeInformation<?> getJavaTypeInformation() {
        return requireNonNullElseGet(this.javaTypeInformation, ClassTypeInformation.from(Object.class));
    }

    @Override
    public Class<?> getRepositoryInterface() {
        return this.repositoryInterface;
    }

    private static TypeInformation<?> resolveTypeParameter(List<TypeInformation<?>> arguments, int index,
                                                           Supplier<String> exceptionMessage) {

        if ((arguments.size() <= index) || (arguments.get(index) == null)) {
            throw new IllegalArgumentException(exceptionMessage.get());
        }

        return arguments.get(index);
    }
    private TypeInformation<?> requireNonNullElseGet(TypeInformation<?> typeInformation, TypeInformation<?> typeInformation2){
        return typeInformation == null ? typeInformation2 : typeInformation;
    }
}
