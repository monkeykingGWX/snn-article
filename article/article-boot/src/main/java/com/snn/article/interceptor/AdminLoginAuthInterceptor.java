package com.snn.article.interceptor;

import com.snn.article.domain.AdminUser;
import com.snn.article.service.IAdminUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 8:56
 */

@Component
public class AdminLoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private IAdminUserService adminUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Object      admin   = session.getAttribute("admin");
        String redirect = request.getContextPath() + "/admin/login";

        if (admin == null) {
            response.sendRedirect(redirect);
            return false;
        }

        Long        userId  = (Long) admin;
        AdminUser   adminUser = adminUserService.findByUserId(userId);
        if (adminUser == null) {
            response.sendRedirect(redirect);
            return false;
        }


        return true;
    }
}
