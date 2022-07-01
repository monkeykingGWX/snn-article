package com.snn.article.controller.admin;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.ArticleCate;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IArticleCateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author monkeykingGWX
 * email 704835519@qq.com
 * created 2022-07-01 21:36
 */

@Controller
@RequestMapping("/admin/articleCate")
public class AdminArticleCateController {

    @Resource
    private IArticleCateService articleCateService;

    @GetMapping
    public String list (Pageination pageination, ModelMap modelMap) {
        PageInfo<ArticleCate> pageInfo = articleCateService.selectList(pageination);

        modelMap.put("pageInfo", pageInfo);
        return "admin/articleCate/list";
    }

    @GetMapping("/create")
    public String addPage () {
        return "admin/articleCate/add";
    }

    @PostMapping("/create")
    public String create (@Validated ArticleCate cate) {
        articleCateService.add(cate);
        return "success";
    }

    @GetMapping("/removeOne/{cateId}")
    public String removeOne (@PathVariable Long cateId) {
        articleCateService.removeOne(cateId);
        return "success";
    }

    @GetMapping("/removeSome")
    public String removeSome (String ids ) {
        List<Long> cateIds = AdminCommonFunc.ids2List(ids);
        articleCateService.removeSome(cateIds);

        return "success";
    }

    @GetMapping("/orderby")
    public String setOrder (String data) {
        List<OrderNum> list = AdminCommonFunc.dealOrderStr(data);
        articleCateService.updateOrder(list);

        return "success";
    }

    @GetMapping("/edit/{cateId}")
    public String edit (@PathVariable Long cateId, ModelMap modelMap) {
        ArticleCate articleCate = articleCateService.selectOneByPrimary(cateId);
        modelMap.put("cate", articleCate);

        return "admin/articleCate/edit";
    }

    @PostMapping("/edit")
    public String edit (@Validated ArticleCate articleCate) {
        articleCateService.update(articleCate);

        return "success";
    }
}
