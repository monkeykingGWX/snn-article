package com.snn.article.service;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.FriendLink;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;

import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 15:30
 */
public interface IFriendLinkService {

    int addFriendLink (FriendLink friendLink);
    int updateFriendLink (FriendLink friendLink);
    int removeOne (Long linkId);
    int removeSome (List<Long>linkIds);
    PageInfo<FriendLink> selectList (Pageination pageination, FriendLink friendLink);
    FriendLink selectByPrimaryId (Long linkId);
    int updateOrder (List<OrderNum> orderNums);
}
