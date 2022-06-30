package com.snn.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snn.article.dao.AdminUserMapper;
import com.snn.article.domain.AdminUser;
import com.snn.article.domain.Pageination;
import com.snn.article.domain.groups.ChangeUserPassGroup;
import com.snn.article.domain.groups.LoginUserGroup;
import com.snn.article.domain.groups.UserAddGroup;
import com.snn.article.service.IAdminUserService;
import com.snn.article.utils.IdUtils;
import com.snn.article.utils.IpUtils;
import com.snn.article.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
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

    // 登录
    @Override
    public void login(HttpServletRequest request, AdminUser adminUser) {
        String loginName = adminUser.getLoginName();
        String loginPass = adminUser.getLoginPass();

        // 账号校验
        AdminUser exists = adminUserMapper.selectByLoginName(loginName);
        if (exists == null) {
            throw new RuntimeException("该登录账号不存在");
        }

        // 密码校验
        String passHalt = exists.getPassHalt();
        String encrypt = exists.getLoginPass();
        loginPass = encrypt(loginPass, passHalt);

        if (!encrypt.equals(loginPass)) {
            throw new RuntimeException("密码输入错误");
        }

        // 成功登录后操作
        HttpSession session = request.getSession();
        Long userId = exists.getUserId();
        session.setAttribute("admin", userId);

        adminUser = new AdminUser();
        adminUser.setUserId(userId);
        adminUser.setLastLoginIp(IpUtils.getIpAddr(request));
        adminUser.setLastLoginTime(new Date());
        adminUserMapper.updateByPrimaryKey(adminUser);
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
