package com.snn.article.dao;

import com.snn.article.domain.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    Article selectByPrimaryKey(Long articleId);

    List<Article> selectArticleList(Article article);

    int updateByPrimaryKey(Article record);

    // 查询6篇 最新/最热/推荐 作文
    List<Article> selectSixArticleByTag (String tagName);
}