package com.snn.article.domain;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ToString
@Data
public class ArticleCate {
    private Long cateId;

    @NotBlank
    private String cateName;

    private Integer orderby;

    private List<Article> articles;
}