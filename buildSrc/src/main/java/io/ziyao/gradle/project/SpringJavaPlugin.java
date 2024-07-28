package io.ziyao.gradle.project;

import org.gradle.api.DefaultTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

/**
 * @author ziyao
 */
public class SpringJavaPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        project.getPlugins().apply(CopyPropertyFormRootProjectPlugin.class);

        project.getConfigurations().configureEach(
                configuration -> configuration.setTransitive(true)
        );

        JavaPluginExtension javaPluginExtension = project.getExtensions().getByType(JavaPluginExtension.class);
        // 设置jdk版本
        javaPluginExtension.toolchain(toolchain -> {
            Object javaLanguageVersion = project.findProperty("javaLanguageVersion");
            if (javaLanguageVersion != null) {
                toolchain.getLanguageVersion().set(JavaLanguageVersion.of(javaLanguageVersion.toString()));
            }
        });
        // 设置编译源码
        javaPluginExtension.withSourcesJar();
        // 设置 java doc
//        javaPluginExtension.withJavadocJar();

        //
        // set encoding and sourceCompatibility
//        project.getTasks().withType(
//                JavaCompile.class, javaCompile -> {
//                    javaCompile.setSourceCompatibility(
//                            ProjectUtils.findProperty("sourceCompatibilityVersion", project));
//
//                    javaCompile.getOptions().setEncoding(
//                            ProjectUtils.findProperty("encoding", project));
//                });
    }
}
