package com.snn.article.config;

import com.snn.article.interceptor.AdminLoginAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 9:04
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AdminLoginAuthInterceptor adminLoginAuthInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginAuthInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/login.html",
                        "/admin/logout",
                        "/admin/verifycode"
                );
    }
}
