package com.snn.article.controller.admin;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.Article;
import com.snn.article.domain.ArticleCate;
import com.snn.article.domain.FriendLink;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IArticleCateService;
import com.snn.article.service.IArticleService;
import com.snn.article.utils.HttpQueryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 10:49
 */

@RequestMapping("/admin/article")
@Controller
public class AdminArticleController {

    @Resource
    private IArticleService articleService;

    @Resource
    private IArticleCateService articleCateService;

    @RequestMapping
    public String listPage (Article article, Pageination pageination, ModelMap mmap) throws IllegalAccessException {
        PageInfo<Article> pageInfo = articleService.selectList(pageination, article);
        String searchQuery = HttpQueryUtils.object2QueryStr(article);

        // 获取作文分类
        Pageination tmp = new Pageination(1, 99999);
        PageInfo<ArticleCate> tmpPageInfo = articleCateService.selectList(tmp);
        List<ArticleCate> cateList = tmpPageInfo.getList();
        Map<Long, ArticleCate> cateMap = AdminCommonFunc.list2MapByPrimary(cateList, "cateId");

        mmap.put("pageInfo", pageInfo);
        mmap.put("search", article);
        mmap.put("searchQuery", searchQuery);
        mmap.put("cateList", cateList);
        mmap.put("cateMap", cateMap);
        return "admin/article/list";
    }


    @GetMapping("/create")
    public String addPage (ModelMap mmap) {
        Pageination pageination = new Pageination(1, 99999);
        PageInfo<ArticleCate> pageInfo = articleCateService.selectList(pageination);
        mmap.put("cateList",  pageInfo.getList());
        return "admin/article/add";
    }

    @PostMapping("/create")
    public String add (@Validated Article article, ModelMap modelMap) {
        articleService.addArticle(article);
        modelMap.put("jumpUrl", "/admin/article/create");
        return "success";
    }

    @GetMapping("/removeOne/{articleId}")
    public String removeOne (@PathVariable Long articleId) {
        articleService.removeOne(articleId);
        return "success";
    }

    @GetMapping("/removeSome")
    public String removeSome (String ids) {
        articleService.removeSome(AdminCommonFunc.ids2List(ids));
        return "success";
    }

    @GetMapping("/edit/{articleId}")
    public String editPage (@PathVariable Long articleId, ModelMap mmap) {
        Article article = articleService.selectByPrimaryId(articleId);

        Pageination pageination = new Pageination(1, 99999);
        PageInfo<ArticleCate> pageInfo = articleCateService.selectList(pageination);
        mmap.put("article", article);
        mmap.put("cateList", pageInfo.getList());
        return "admin/article/edit";
    }

    @PostMapping("/edit")
    public String edit (@Validated Article article) {
        articleService.updateArticle(article);

        return "success";
    }
}
