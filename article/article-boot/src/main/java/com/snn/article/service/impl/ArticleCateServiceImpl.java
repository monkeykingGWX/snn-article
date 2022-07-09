package com.snn.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snn.article.dao.ArticleCateMapper;
import com.snn.article.domain.ArticleCate;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IArticleCateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author monkeykingGWX
 * email 704835519@qq.com
 * created 2022-07-01 21:50
 */

@Service
public class ArticleCateServiceImpl implements IArticleCateService {

    @Resource
    private ArticleCateMapper articleCateMapper;

    @Override
    public int add(ArticleCate cate) {
        return articleCateMapper.insert(cate);
    }

    @Override
    public ArticleCate selectOneByPrimary(Long cateId) {
        ArticleCate articleCate = articleCateMapper.selectByPrimaryKey(cateId);

        if (articleCate == null) {
            throw new RuntimeException("不存在此文章分类信息");
        }

        return articleCate;
    }

    @Override
    public int update(ArticleCate cate) {
        Long cateId = cate.getCateId();
        selectOneByPrimary(cateId);
        return articleCateMapper.updateByPrimaryKey(cate);
    }

    @Override
    public int removeOne(Long cateId) {
        selectOneByPrimary(cateId);
        return articleCateMapper.deleteByPrimaryKey(cateId);
    }

    @Override
    public int removeSome(List<Long> cateIds) {
        for (Long cateId : cateIds) {
            articleCateMapper.deleteByPrimaryKey(cateId);
        }

        return 1;
    }

    @Override
    public PageInfo<ArticleCate> selectList(Pageination pageination) {
        Integer pageNum = pageination.getPageNum();
        Integer pageSize = pageination.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        List<ArticleCate>     cates    = articleCateMapper.selectAll();
        PageInfo<ArticleCate> pageInfo = new PageInfo<>(cates);

        return pageInfo;
    }

    @Override
    public int updateOrder(List<OrderNum> orderNums) {
        ArticleCate articleCate = new ArticleCate();

        for (OrderNum orderNum : orderNums) {
            Long primary = orderNum.getPrimary();
            Integer orderby = orderNum.getOrderby();
            articleCate.setCateId(primary);
            articleCate.setOrderby(orderby);
            articleCateMapper.updateByPrimaryKey(articleCate);
        }

        return 1;
    }

    @Override
    public List<ArticleCate> selectAllCate() {
        return articleCateMapper.selectAll();
    }

    @Override
    public List<ArticleCate> selectSixCates() {
        return articleCateMapper.selectSixCates();
    }
}
