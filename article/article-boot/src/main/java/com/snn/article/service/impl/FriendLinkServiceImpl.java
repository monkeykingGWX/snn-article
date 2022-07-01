package com.snn.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snn.article.dao.FriendLinkMapper;
import com.snn.article.domain.FriendLink;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IFriendLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 15:33
 */

@Service
public class FriendLinkServiceImpl implements IFriendLinkService {
    @Resource
    private FriendLinkMapper friendLinkMapper;

    @Override
    public int addFriendLink(FriendLink friendLink) {
        return friendLinkMapper.insert(friendLink);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        selectByPrimaryId(friendLink.getLinkId());
        return friendLinkMapper.updateByPrimaryKey(friendLink);
    }

    @Override
    public int removeOne(Long linkId) {
        selectByPrimaryId(linkId);
        return friendLinkMapper.deleteByPrimaryKey(linkId);
    }

    @Override
    public int removeSome(List<Long> linkIds) {
        for (Long linkId : linkIds) {
            friendLinkMapper.deleteByPrimaryKey(linkId);
        }

        return 1;
    }

    @Override
    public PageInfo<FriendLink> selectList(Pageination pageination, FriendLink friendLink) {
        Integer pageNum = pageination.getPageNum();
        Integer pageSize = pageination.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        List<FriendLink>     links    = friendLinkMapper.selectList(friendLink);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(links);

        return pageInfo;
    }

    @Override
    public FriendLink selectByPrimaryId(Long linkId) {
        FriendLink friendLink = friendLinkMapper.selectByPrimaryKey(linkId);

        if (friendLink == null) {
            throw new RuntimeException("不存在此友链信息");
        }

        return friendLink;
    }

    @Override
    public int updateOrder(List<OrderNum> orderNums) {
        FriendLink friendLink = new FriendLink();

        for (OrderNum orderNum : orderNums) {
            Long primary = orderNum.getPrimary();
            Integer orderby = orderNum.getOrderby();
            friendLink.setLinkId(primary);
            friendLink.setOrderby(orderby);
            friendLinkMapper.updateByPrimaryKey(friendLink);
        }

        return 1;
    }
}
