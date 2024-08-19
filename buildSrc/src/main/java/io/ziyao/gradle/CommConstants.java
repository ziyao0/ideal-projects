package io.ziyao.gradle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao
 */
public interface CommConstants {
    public static final String LIBS = "/ideal-deps/libs/ideal-deps.libs";
    public static final String WELL_NUMBER = "#";
    public static final String GRADLE_API = "api";
    public static final String GRADLE_PLUGIN_platform = "java-platform";
    public static final String GRADLE_PLUGIN_library = "java-library";
    public static final String GRADLE_PLUGIN_maven_publish = "maven-publish";
    /**
     * Name of the {@code optional} configuration.
     */
    public static final String OPTIONAL_CONFIGURATION_NAME = "optional";
    public static final String MANAGEMENT_CONFIGURATION_NAME = "management";
    public static final String TARGET_PATH = "/src/main/resources";

    public static List<String> SOURCES() {
        ArrayList<String> list = new ArrayList<>();
        list.add("/resources/config/bootstrap-register.yml");
        return list;
    }

}
