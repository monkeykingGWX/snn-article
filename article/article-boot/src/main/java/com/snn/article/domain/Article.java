package com.snn.article.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
public class Article {
    private Long articleId;

    @NotNull(message = "请选择作文分类")
    private Long cateId;

    @NotBlank(message = "请填写作文标题")
    private String title;

    private String keyword;

    @NotNull
    private Byte isHot;

    @NotNull
    private Byte isFavor;

    @NotNull
    private Byte isSub;

    private Date createTime;

    @NotBlank(message="请输入文章内容")
    private String content;
}