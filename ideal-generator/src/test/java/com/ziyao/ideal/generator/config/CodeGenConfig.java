package com.ziyao.ideal.generator.config;

/**
 * @author ziyao zhang
 */

public class CodeGenConfig {

    private boolean enableJpa;

    /**
     * 生成文件的输出目录【默认 D 盘根目录】
     */
    private String projectDir;
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;
    /**
     * 开发人员
     */
    private String author = "ziyao";

    private String userName;
    private String url;
    private String password;

    //PackageConfig

    private String moduleName;
    private String parent;


    // StrategyConfig
    private String superControllerClass;
    /**
     * 表名，多个英文逗号分割
     */
    private String include;


    public boolean isEnableJpa() {
        return enableJpa;
    }

    public void setEnableJpa(boolean enableJpa) {
        this.enableJpa = enableJpa;
    }

    public String getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(String projectDir) {
        this.projectDir = projectDir;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}
