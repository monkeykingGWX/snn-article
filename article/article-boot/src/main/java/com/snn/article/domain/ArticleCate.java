package com.snn.article.domain;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Data
public class ArticleCate {
    private Long cateId;

    @NotBlank
    private String cateName;

    private Integer orderby;
}