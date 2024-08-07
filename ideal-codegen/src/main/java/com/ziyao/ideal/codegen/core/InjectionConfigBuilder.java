package com.ziyao.ideal.codegen.core;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.ziyao.ideal.codegen.CodeGenConst;
import com.ziyao.ideal.codegen.config.CodeGenConfig;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class InjectionConfigBuilder {

    private static final ConverterFileName converterRepositoryFileName = (entityName -> entityName + CodeGenConst.JPA_REPOSITORY_NAME);
    private static final ConverterFileName converterDTOFileName = (entityName -> entityName + CodeGenConst.JPA_DTO_NAME);
    private static final ConverterFileName converterMapstructFileName = (entityName -> entityName + CodeGenConst.MAPSTRUCT_NAME);


    public static void injectionConfig(
            InjectionConfig.Builder builder, CodeGenConfig codeGenConfig) {

        builder.customMap(cteateCustomMap(codeGenConfig))
                .customFile(cteateDTOCustomFile(codeGenConfig))
                .customFile(cteateJpaRepositoryCustomFile(codeGenConfig))
                .customFile(cteateMapStructCustomFile(codeGenConfig));

    }


    public static Map<String, Object> cteateCustomMap(CodeGenConfig codeGenConfig) {
        String packagePath = codeGenConfig.getParent() + "." + codeGenConfig.getModuleName();
        return new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7702519044952153375L;

            {
                put("dto", packagePath + ".domain.dto");
                put("repositoryJpa", packagePath + ".repository.jpa");
                put("mapstructPkg", packagePath + ".domain.mapstruct");
            }
        };
    }

    public static CustomFile cteateDTOCustomFile(CodeGenConfig codeGenConfig) {
        return new CustomFile.Builder()
                .templatePath(CodeGenConst.ENTITY_DTO_TEMPLATE)
                .packageName("domain.dto")
                .fileName(ConstVal.JAVA_SUFFIX)
                .formatNameFunction(tableInfo -> converterDTOFileName.convert(tableInfo.getEntityName())).build();
    }

    public static CustomFile cteateMapStructCustomFile(CodeGenConfig codeGenConfig) {
        return new CustomFile.Builder()
                .templatePath(CodeGenConst.MAPSTRUCT_TEMPLATE)
                .packageName("domain.mapstruct")
                .fileName(ConstVal.JAVA_SUFFIX)
                .formatNameFunction(tableInfo -> converterMapstructFileName.convert(tableInfo.getEntityName())).build();
    }

    public static CustomFile cteateJpaRepositoryCustomFile(CodeGenConfig codeGenConfig) {
        return new CustomFile.Builder()
                .templatePath(CodeGenConst.JPA_REPOSITORY_TEMPLATE)
                .packageName("repository.jpa")
                .fileName(ConstVal.JAVA_SUFFIX)
                .formatNameFunction(tableInfo -> converterRepositoryFileName.convert(tableInfo.getEntityName())).build();
    }


}
