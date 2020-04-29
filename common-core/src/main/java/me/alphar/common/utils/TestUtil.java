package me.alphar.common.utils;

import java.lang.reflect.Field;
import java.text.ParseException;

public class TestUtil {
    public static void main(String[] args) throws ParseException, ClassNotFoundException {
        Class<?> clazz = Class.forName("me.alphar.common.core.Res");
        Field[] fields = clazz.getDeclaredFields();
        String query = "CREATE TABLE public.employee(";
        for(int i=0;i<fields.length;i++){
            if(fields[i].getType().getSimpleName().toLowerCase().equals("long") || fields[i].getType().getSimpleName().toLowerCase().contains("int")){
                query += fields[i].getName()+" bigint NOT NULL";
            }
            else if (fields[i].getType().getSimpleName().toLowerCase().contains("double") || fields[i].getType().getSimpleName().toLowerCase().equals("float")){
                query += fields[i].getName()+" float NOT NULL";
            }
            else if (fields[i].getType().getSimpleName().toLowerCase().contains("string")){
                query += fields[i].getName()+" text NOT NULL";
            }
            if(i != fields.length-1)
                query+=",";
        }
        query+=")";
        System.out.println(query);
    }
}