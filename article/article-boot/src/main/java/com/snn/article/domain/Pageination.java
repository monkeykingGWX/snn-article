package com.snn.article.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-28 15:55
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pageination {

    // 页码
    @Min(value = 1, message = "页码异常")
    private Integer pageNum = 1;

    // 每页数据数
    @Min(value = 10, message = "每页数最低为10")
    @Max(value = 100, message = "每页数最高为100")
    private Integer pageSize = 10;
}
