package com.snn.article.dao;

import com.snn.article.domain.ArticleContent;

public interface ArticleContentMapper {
    int insert(ArticleContent record);
    int update(ArticleContent record);
    ArticleContent findByArticleId (Long articleId);
}