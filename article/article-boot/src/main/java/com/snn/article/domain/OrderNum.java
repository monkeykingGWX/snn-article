package com.snn.article.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-07-01 14:24
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderNum {

    private Long primary;
    private Integer orderby;
}
