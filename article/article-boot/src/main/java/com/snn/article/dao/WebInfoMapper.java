package com.snn.article.dao;

import com.snn.article.domain.WebInfo;

import java.util.List;

public interface WebInfoMapper {
    int insert(WebInfo record);

    WebInfo selectOne();

    int updateByPrimaryKey(WebInfo record);
}