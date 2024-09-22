package com.ziyao.ideal.uaa.support.genertaor;

import com.ziyao.ideal.crypto.TextCipher;
import com.ziyao.ideal.crypto.utils.SmUtils;
import com.ziyao.ideal.generator.GenerationBootstrap;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class CodeAutoGenerator {

    public static void main(String[] args) {
        GenerationBootstrap generator = GenerationBootstrap.init("generator.properties");

        TextCipher textCipher = SmUtils.createSm4CBCTextCipherWithZeroPaddingAndHexCodec("13f70890272c40c07d85b15e5d532bd5", "b18451cd68969ad9ec78f7c700370eea");

        DataSourceSettings dataSourceSettings = generator.getDataSourcePropertiesBuilder().build();


        generator.dataSourceProperties(builder ->
                        builder.url(textCipher.decrypt(dataSourceSettings.getUrl()))
                                .password(textCipher.decrypt(dataSourceSettings.getPassword()))
                                .username(textCipher.decrypt(dataSourceSettings.getUsername())))
                .strategyProperties(builder ->
                        builder.repositoryBuilder()
                                .superClass(JpaRepository.class)
                                .persistentBuilder()
                                .override()
                                .serviceBuilder()
//                                .superClass(JpaService.class)
//                                .supperImplClass(JpaServiceImpl.class)
                                .controllerBuilder()
                                .override())
                .bootstrap();
    }
}
