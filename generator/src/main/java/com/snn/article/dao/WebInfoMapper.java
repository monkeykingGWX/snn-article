package com.snn.article.dao;

import com.snn.article.domain.WebInfo;
import java.util.List;

public interface WebInfoMapper {
    int deleteByPrimaryKey(Long webId);

    int insert(WebInfo record);

    WebInfo selectByPrimaryKey(Long webId);

    List<WebInfo> selectAll();

    int updateByPrimaryKey(WebInfo record);
}