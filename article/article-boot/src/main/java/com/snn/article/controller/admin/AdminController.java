package com.snn.article.controller.admin;

import com.snn.article.domain.AdminUser;
import com.snn.article.domain.groups.LoginUserGroup;
import com.snn.article.service.IAdminUserService;
import com.snn.article.utils.KaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 15:07
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminUserService adminUserService;

    // 后台首页
    @GetMapping({"", "/index", "index.html"})
    public String index (HttpSession session, ModelMap modelMap) {
        Object admin = session.getAttribute("admin");
        Long userId = (Long) admin;
        AdminUser adminUser = adminUserService.findByUserId(userId);

        modelMap.put("admin", adminUser);
        return "admin/index";
    }

    @GetMapping({"/login", "/login.html"})
    public String login () {
        return "admin/login";
    }

    @GetMapping("/verifycode")
    public void verifyCode (HttpServletRequest request, HttpServletResponse response) throws IOException {
        KaptchaUtils.createVerifyCode(request, response);
    }

    @PostMapping({"/login", "/login.html"})
    public String login (@Validated(LoginUserGroup.class) AdminUser adminUser, @RequestParam String code, HttpServletRequest request) {
        // 验证码校验
        if (code.length() == 0) {
            throw new RuntimeException("请输入验证码");
        }
        if (!KaptchaUtils.checkVerificationCode(code, request)) {
            throw new RuntimeException("验证码输入错误");
        }

        adminUserService.login(request, adminUser);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("admin");

        return "redirect:/admin/login";
    }
}
