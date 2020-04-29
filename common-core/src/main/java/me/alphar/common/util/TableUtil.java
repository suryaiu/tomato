package me.alphar.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根椐 Entity 生成 SQL
 */
public class TableUtil {

    public static void main(String[] args) {
        genTableSql("me.alphar.common.entity.InnerUser");
    }

    private static void genTableSql(String classPath) {
        try {
            Class clazz = Class.forName(classPath);
            List<Field> fieldList = new ArrayList<>(50);
            while (clazz != null) {
                fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }
            System.out.println(fieldList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
