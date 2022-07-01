package com.snn.article.service;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.ArticleCate;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;
import javafx.scene.control.Pagination;

import java.util.List;

/**
 * @author monkeykingGWX
 * email 704835519@qq.com
 * created 2022-07-01 21:48
 */


public interface IArticleCateService {

    int add (ArticleCate cate);
    ArticleCate selectOneByPrimary(Long cateId);
    int update (ArticleCate cate);
    int removeOne (Long cateId);
    int removeSome (List<Long> cateIds);
    PageInfo<ArticleCate> selectList(Pageination pageination);
    int updateOrder (List<OrderNum> orderNums);
}
