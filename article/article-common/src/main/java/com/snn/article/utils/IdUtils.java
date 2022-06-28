package com.snn.article.utils;

import java.util.UUID;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 17:21
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }
    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return randomUUID().replace("-", "");
    }
}
