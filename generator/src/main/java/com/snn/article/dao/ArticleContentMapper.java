package com.snn.article.dao;

import com.snn.article.domain.ArticleContent;
import java.util.List;

public interface ArticleContentMapper {
    int insert(ArticleContent record);

    List<ArticleContent> selectAll();
}