package com.snn.article.utils;

import java.lang.reflect.Field;

/**
 * 将对象转换为表单形式 如：&a=aaa&b=bbb
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 16:49
 */
public class HttpQueryUtils {

    public static String object2QueryStr (Object obj) throws IllegalAccessException {
        if (obj == null) {
            return "";
        }

        Class objClass = obj.getClass();
        Field[] declaredFields = objClass.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object o = field.get(obj);
            String name = field.getName();
            if (o != null) {
                stringBuilder.append("&").append(name).append("=").append(o.toString());
            }
        }

        return stringBuilder.toString();
    }
}
