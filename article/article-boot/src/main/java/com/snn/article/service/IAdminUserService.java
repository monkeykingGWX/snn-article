package com.snn.article.service;

import com.snn.article.domain.AdminUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 16:48
 */
public interface IAdminUserService {

    int createAdminUser(AdminUser user);

    int modifyAdminUser(AdminUser user);

    List<AdminUser> selectAdminUsers();

    AdminUser findByUserId (Long userId);

    int deleteOne (Long userId);

    int deleteByIds (List<Long> userIds);
}
