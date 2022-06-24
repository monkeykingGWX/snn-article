package com.mybatis.dao;

import com.snn.domain.AdminUser;
import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(AdminUser record);

    AdminUser selectByPrimaryKey(Long userId);

    List<AdminUser> selectAll();

    int updateByPrimaryKey(AdminUser record);
}