package com.snn.article.dao;

import com.snn.article.domain.FriendLink;

import java.util.List;

public interface FriendLinkMapper {
    int deleteByPrimaryKey(Long linkId);

    int insert(FriendLink record);

    FriendLink selectByPrimaryKey(Long linkId);

    List<FriendLink> selectList(FriendLink friendLink);

    int updateByPrimaryKey(FriendLink record);

    List<FriendLink> selectShowLinks ();
}