package com.snn.article.dao;

import com.snn.article.domain.ArticleCate;

import java.util.List;

public interface ArticleCateMapper {
    int deleteByPrimaryKey(Long cateId);

    int insert(ArticleCate record);

    ArticleCate selectByPrimaryKey(Long cateId);

    List<ArticleCate> selectAll();

    int updateByPrimaryKey(ArticleCate record);

    List<ArticleCate> selectSixCates ();
}