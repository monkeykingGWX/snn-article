package com.snn.article.controller.admin;

import com.snn.article.domain.OrderNum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-07-01 10:25
 */
public class AdminCommonFunc {

    // 处理类似1,2,5这样的字符串
    public static List<Long> ids2List (String ids) {
        String[]   split   = ids.split(",");
        List<Long> idsList = new ArrayList<>(split.length);

        for (String id : split) {
            idsList.add(Long.valueOf(id));
        }

        return idsList;
    }

    // 处理类似1-100,2-99,3-88类似字符串
    public static List<OrderNum> dealOrderStr (String data) {
        String[] split = data.split(",");
        List<OrderNum> list = new ArrayList<>(split.length);

        for (String orderNum : split) {
            String[] tmpArr = orderNum.split("-");
            Long primary = Long.valueOf(tmpArr[0]);
            Integer orderby = Integer.valueOf(tmpArr[1]);
            list.add(new OrderNum(primary, orderby));
        }

        return list;
    }
}
