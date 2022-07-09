package com.snn.article.service;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.Article;
import com.snn.article.domain.FriendLink;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;

import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 15:30
 */
public interface IArticleService {

    int addArticle(Article article);
    int updateArticle(Article article);
    int removeOne(Long articleId);
    int removeSome(List<Long> articleIds);
    PageInfo<Article> selectList(Pageination pageination, Article article);
    Article selectByPrimaryId(Long articleId);
    int updateOrder(List<OrderNum> orderNums);
    List<Article> selectSixArticleByTag(String tagName);
}
