package me.alphar.common.util;

import cn.hutool.core.util.StrUtil;
import me.alphar.common.annotation.Comment;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根椐 Entity 生成 SQL
 */
public class TableUtil {

    public static Map<String, String> javaType2JdbcType = new HashMap<>();

    static {
        javaType2JdbcType.put("Long", "bigint(20)");
        javaType2JdbcType.put("Integer", "int(11)");
        javaType2JdbcType.put("Short", "tinyint(4)");
        javaType2JdbcType.put("BigDecimal", "decimal(10,2)");
        javaType2JdbcType.put("Double", "decimal(10,2)");
        javaType2JdbcType.put("Float", "decimal(10,2)");
        javaType2JdbcType.put("Boolean", "BIT(1)");
        javaType2JdbcType.put("Timestamp", "date");
        javaType2JdbcType.put("String", "varchar(255)");
        javaType2JdbcType.put("LocalDate", "date");
        javaType2JdbcType.put("LocalDateTime", "datetime");

    }
    public static void main(String[] args) {
        genTableSql("me.alphar.common.entity.InnerUser", "inner_user");
    }

    private static void genTableSql(String path, String tableName) {
        try {
            Class<?> clazz = Class.forName(path);
            List<Field> fieldList = new ArrayList<>(50);
            while (clazz != null) {
                List<Field> fieldList1 = Arrays.asList(clazz.getDeclaredFields());
                Collections.reverse(fieldList1);
                fieldList.addAll(fieldList1);
                clazz = clazz.getSuperclass();
            }
            Collections.reverse(fieldList);
            StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + "(");
            String comment = "";
            for (Field field: fieldList) {
                boolean annotationPresent = field.isAnnotationPresent(Comment.class);
                if (annotationPresent) {
                    Comment annotation = field.getAnnotation(Comment.class);
                    comment = annotation.value();
                }
                String javaType = javaType2JdbcType.get(field.getType().getSimpleName());
                sql
                        .append("`")
                        .append(camelToUnderline(field.getName()).toLowerCase())
                        .append("`")
                        .append(StrUtil.SPACE)
                        .append(javaType)
                        .append(" NOT NULL COMMENT '")
                        .append(comment)
                        .append("',");
            }
            sql.append("PRIMARY KEY (`tid`)").append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            System.out.println(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String camelToUnderline(String camelString) {
        if (null == camelString || "".equals(camelString)) return camelString;
        camelString = String.valueOf(camelString.charAt(0)).toUpperCase().concat(camelString.substring(1));

        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");    //字母、数字、下划线
        Matcher matcher = pattern.matcher(camelString);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == camelString.length() ? "" : "_");
        }
        return sb.toString();
    }
}
