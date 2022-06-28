package com.snn.article.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 15:07
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 后台首页
    @GetMapping({"", "/index", "index.html"})
    public String index () {
        return "admin/index";
    }
}
