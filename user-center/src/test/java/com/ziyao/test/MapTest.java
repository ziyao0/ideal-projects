package com.ziyao.test;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class MapTest {


    public static void main(String[] args) {

        try {


            Map<String, Integer> genericTestMap = new HashMap<String, Integer>();


            TypeVariable<? extends Class<? extends Map>>[] typeParameters = genericTestMap.getClass().getTypeParameters();


            System.out.println("Key: " + typeParameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
