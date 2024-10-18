package io.ziyao.gradle;

import java.util.List;

/**
 * @author ziyao
 */
public interface CommConstants {
    public static final String LIBS = "/ideal-dependencies/libs/ideal-dependencies.libs";
    public static final String WELL_NUMBER = "#";
    public static final String GRADLE_API = "api";
    public static final String GRADLE_PLUGIN_PLATFORM = "java-platform";
    public static final String GRADLE_PLUGIN_LIBRARY = "java-library";
    public static final String GRADLE_PLUGIN_MAVEN_PUBLISH = "maven-publish";
    /**
     * Name of the {@code optional} configuration.
     */
    public static final String OPTIONAL_CONFIGURATION_NAME = "optional";
    public static final String MANAGEMENT_CONFIGURATION_NAME = "management";
    public static final List<String> SOURCES = List.of("/resources/config/bootstrap-register.yml");
    public static final String TARGET_PATH = "/src/main/resources";

}
