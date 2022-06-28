package com.snn.article.controller.admin;

import com.snn.article.domain.AdminUser;
import com.snn.article.service.IAdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 16:17
 */

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Resource
    private IAdminUserService adminUserService;

    @GetMapping()
    public String list (@Validated Pageination pageination, ModelMap modelMap) {
        return "admin/user/list";
    }

    @GetMapping("/create")
    public String add () {
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String add (@Validated AdminUser user, ModelMap modelMap) {
        adminUserService.createAdminUser(user);
        modelMap.put("jumpUrl", "/admin/user/create");  // 操作成功跳转地址
        return "success";
    }

}
