package com.snn.article.controller;

import com.snn.article.dao.AdminUserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 11:41
 */

@Controller
public class IndexController {

    @Resource
    private AdminUserMapper adminUserMapper;

    @GetMapping("/")
    public String index (ModelMap modelMap) {
        modelMap.put("title", "作文大全");
        return "index";
    }
}
