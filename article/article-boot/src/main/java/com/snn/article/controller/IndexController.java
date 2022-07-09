package com.snn.article.controller;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.*;
import com.snn.article.service.IArticleCateService;
import com.snn.article.service.IArticleService;
import com.snn.article.service.IFriendLinkService;
import com.snn.article.service.IWebInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 11:41
 */

@Controller
public class IndexController {

    @Resource
    private IWebInfoService webInfoService;

    @Resource
    private IFriendLinkService friendLinkService;

    @Resource
    private IArticleCateService articleCateService;

    @Resource
    private IArticleService articleService;

    @GetMapping({"/", "/index.html"})
    public String index (ModelMap modelMap) {
        setCommonData(modelMap);

        /* 友情链接 */
        List<FriendLink> friendLinks = friendLinkService.selectShowLinks();
        modelMap.put("friendLinks", friendLinks);

        /* 最新、最热、推荐各6篇 */
        Pageination pageination = new Pageination(1, 6);
        PageInfo<Article> articlePageInfo = articleService.selectList(pageination, null);
        List<Article> lastedArticles = articlePageInfo.getList();
        List<Article> hotArticles = articleService.selectSixArticleByTag("is_hot");
        List<Article> favorArticles = articleService.selectSixArticleByTag("is_favor");
        modelMap.put("lastedArticles", lastedArticles);
        modelMap.put("hotArticles", hotArticles);
        modelMap.put("favorArticles", favorArticles);

        /* 分类作文列表 */
        // 获取6个分类
        List<ArticleCate> sixArticleCate = articleCateService.selectSixCates();

        // 获取分类下的文章
        Article article = new Article();
        for (ArticleCate cate : sixArticleCate) {
            Long cateId = cate.getCateId();
            article.setCateId(cateId);
            PageInfo<Article> pageInfo = articleService.selectList(pageination, article);
            List<Article>     list     = pageInfo.getList();

            cate.setArticles(list);
        }


        // 列表等分两份
        List<ArticleCate> cateList31  = new ArrayList<>(3);
        List<ArticleCate> cateList32  = new ArrayList<>(3);

        int index = 0;
        while (index < 3) {
            cateList31.add(sixArticleCate.get(index++));
        }
        while (index < 6) {
            cateList32.add(sixArticleCate.get(index++));
        }
        modelMap.put("cateList31", cateList31);
        modelMap.put("cateList32", cateList32);

        return "index";
    }

    @GetMapping("/list/{cateId}.html")
    public String list (@PathVariable Long cateId, Pageination pageination, @RequestParam(defaultValue = "") String keywords, ModelMap mmap) {
        setCommonData(mmap);
        ArticleCate articleCate = articleCateService.selectOneByPrimary(cateId);
        mmap.put("cate", articleCate);

        // 获取文章列表
        Article article = new Article();
        article.setCateId(cateId);
        article.setTitle(keywords);
        PageInfo<Article> articlePageInfo = articleService.selectList(pageination, article);
        mmap.put("list", articlePageInfo.getList());
        return "list";
    }

    @GetMapping("/detail/{articleId}.html")
    public String detail (@PathVariable Long articleId, ModelMap modelMap) {
        return "detail";
    }


    // 设置数据
    private void setCommonData (ModelMap modelMap) {
        /* webInfo */
        WebInfo webInfo = webInfoService.selectWebInfo();
        // bottom做下处理
        String bottom = webInfo.getBottom().replace("\n", "<br />");
        modelMap.put("webInfo", webInfo);
        modelMap.put("bottom", bottom);

        /* 导航栏 */
        List<ArticleCate> articleCates = articleCateService.selectAllCate();
        modelMap.put("navs", articleCates);
    }
}
