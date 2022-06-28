package com.snn.article.controller.admin;

import com.snn.article.domain.AdminUser;
import com.snn.article.service.IAdminUserService;
import org.springframework.stereotype.Controller;
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
    public String list () {
        return "admin/user/list";
    }

    @GetMapping("/create")
    public String add () {
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String add (AdminUser user) {
        // 参数校验
        String name = user.getName();
        String loginName = user.getLoginName();
        String loginPass = user.getLoginPass();

        if (name == null) {
            throw new RuntimeException("用户昵称不得为空");
        }

        int nameLength = name.length();
        if (nameLength < 2 || nameLength > 8) {
            throw new RuntimeException("用户昵称长度在2到8个字符之间");
        }

        // ……loginName及loginPass的校验

        adminUserService.createAdminUser(user);

        return "success";
    }

}
