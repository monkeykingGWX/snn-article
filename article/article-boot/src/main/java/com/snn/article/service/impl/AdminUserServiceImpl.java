package com.snn.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snn.article.dao.AdminUserMapper;
import com.snn.article.domain.AdminUser;
import com.snn.article.domain.ChangeUserPassGroup;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IAdminUserService;
import com.snn.article.utils.IdUtils;
import com.snn.article.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 16:52
 */

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    // 新增管理员
    @Override
    public int createAdminUser(AdminUser user) {
        // 登录账号唯一校验
        String loginName = user.getLoginName();
        AdminUser adminUser = adminUserMapper.selectByLoginName(loginName);

        if (adminUser != null) {
            throw new RuntimeException("该管理员账号已经存在");
        }

        // 密码加密
        String loginPass = user.getLoginPass();
        String halt      = getHalt();
        String encrypt   = encrypt(loginPass, halt);
        user.setPassHalt(halt);
        user.setLoginPass(encrypt);


        return adminUserMapper.insert(user);
    }

    // 修改管理员密码
    @Override
    public int changeUserPass(AdminUser user) {
        Long userId = user.getUserId();

        findByUserId(userId);  // 校验该条数据是否存在

        String loginPass = user.getLoginPass();

        // 密码加密
        String halt      = getHalt();
        String encrypt   = encrypt(loginPass, halt);

        user = new AdminUser();
        user.setUserId(userId);
        user.setPassHalt(halt);
        user.setLoginPass(encrypt);

        return adminUserMapper.updateByPrimaryKey(user);
    }

    // 查询管理员列表
    @Override
    public PageInfo<AdminUser> selectAdminUsers(Pageination pageination) {
        Integer pageNum = pageination.getPageNum();
        Integer pageSize = pageination.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<AdminUser> adminUsers = adminUserMapper.selectAll();
        PageInfo<AdminUser> pageInfo = new PageInfo<>(adminUsers);

        return pageInfo;
    }

    // 查管理员
    @Override
    public AdminUser findByUserId(Long userId) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);

        if (adminUser == null) {
            throw new RuntimeException("不存在此管理员信息");
        }

        return adminUser;
    }

    // 删除一个管理员
    @Override
    public int deleteOne(Long userId) {
        findByUserId(userId);  // 校验该条数据是否存在
        return adminUserMapper.deleteByPrimaryKey(userId);
    }

    // 删除多个管理员
    @Override
    public int deleteByIds(List<Long> userIds) {
        for (Long userId : userIds) {
            adminUserMapper.deleteByPrimaryKey(userId);
        }

        return 0;
    }


    // 获取随机halt
    private String getHalt () {
        String uuid = IdUtils.simpleUUID();
        return uuid.substring(0, 8); // 取前8位做随机halt
    }

    // 密码加密算法MD5(MD5(用户密码+halt))
    private String encrypt (String pass, String halt) {
        return Md5Utils.hash(Md5Utils.hash(pass+halt));
    }
}
