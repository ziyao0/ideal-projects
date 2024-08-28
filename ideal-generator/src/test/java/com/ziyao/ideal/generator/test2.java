package com.ziyao.ideal.generator;

import java.io.File;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class test2 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String projectPath = new File("").getAbsolutePath();
        System.out.println("Current project path: " + projectPath);
        // 获取当前类的类路径
        String subProjectPath = test2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("Sub-project path: " + subProjectPath);
    }
}
