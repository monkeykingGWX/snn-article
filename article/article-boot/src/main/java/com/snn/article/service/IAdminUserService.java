package com.snn.article.service;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.AdminUser;
import com.snn.article.domain.Pageination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 16:48
 */
public interface IAdminUserService {

    int createAdminUser(AdminUser user);

    int changeUserPass(AdminUser user);

    PageInfo<AdminUser> selectAdminUsers(Pageination pageination);

    AdminUser findByUserId (Long userId);

    int deleteOne (Long userId);

    int deleteByIds (List<Long> userIds);

    void login (HttpServletRequest request, AdminUser adminUser);
}
