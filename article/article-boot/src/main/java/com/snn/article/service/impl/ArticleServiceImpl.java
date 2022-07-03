package com.snn.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snn.article.dao.ArticleContentMapper;
import com.snn.article.dao.ArticleMapper;
import com.snn.article.domain.*;
import com.snn.article.service.IArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author monkeykingGWX
 * email 704835519@qq.com
 * created 2022-07-02 18:41
 */

@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleContentMapper contentMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int addArticle(Article article) {
        Date date = new Date();
        article.setCreateTime(date);
        articleMapper.insert(article);

        Long articleId = article.getArticleId();

        ArticleContent articleContent = new ArticleContent(articleId, article.getContent());
        contentMapper.insert(articleContent);

        return 1;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int updateArticle(Article article) {
        articleMapper.updateByPrimaryKey(article);
        Long articleId = article.getArticleId();
        String content = article.getContent();

        ArticleContent articleContent = new ArticleContent(articleId, content);
        contentMapper.update(articleContent);
        return 1;
    }

    @Override
    public int removeOne(Long articleId) {
        selectByPrimaryId(articleId);

        return articleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int removeSome(List<Long> articleIds) {
        for (Long articleId : articleIds) {
            articleMapper.deleteByPrimaryKey(articleId);
        }

        return 1;
    }

    @Override
    public PageInfo<Article> selectList(Pageination pageination, Article article) {
        Integer pageNum = pageination.getPageNum();
        Integer pageSize = pageination.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        List<Article>     articles    = articleMapper.selectArticleList(article);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        return pageInfo;
    }

    @Override
    public Article selectByPrimaryId(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);

        if (article == null) {
            throw new RuntimeException("不存在此文章信息");
        }

        ArticleContent articleContent = contentMapper.findByArticleId(articleId);
        String         content        = articleContent.getContent();
        article.setContent(content);
        return article;
    }

    @Override
    public int updateOrder(List<OrderNum> orderNums) {
        return 0;
    }
}
