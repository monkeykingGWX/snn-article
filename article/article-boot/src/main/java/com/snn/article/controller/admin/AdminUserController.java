package com.snn.article.controller.admin;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.AdminUser;
import com.snn.article.domain.groups.ChangeUserPassGroup;
import com.snn.article.domain.Pageination;
import com.snn.article.domain.groups.UserAddGroup;
import com.snn.article.service.IAdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        PageInfo<AdminUser> pageInfo = adminUserService.selectAdminUsers(pageination);
        modelMap.put("pageInfo", pageInfo);
        return "admin/user/list";
    }

    @GetMapping("/create")
    public String add () {
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String add (@Validated(UserAddGroup.class) AdminUser user, ModelMap modelMap) {
        adminUserService.createAdminUser(user);
        modelMap.put("jumpUrl", "/admin/user/create");  // 操作成功跳转地址
        return "success";
    }

    @GetMapping("/changePass/{userId}")
    public String changePass (@PathVariable Long userId, ModelMap modelMap) {
        modelMap.put("userId", userId);
        return "admin/user/edit";
    }

    @PostMapping("/changePass")
    public String changePass (@Validated(ChangeUserPassGroup.class) AdminUser adminUser, ModelMap modelMap) {
        adminUserService.changeUserPass(adminUser);
        return "success";
    }

    @GetMapping("/removeOne/{userId}")
    public String removeOne (@PathVariable Long userId) {
        adminUserService.deleteOne(userId);
        return "success";
    }

    @GetMapping("/removeSome")
    public String removeSome (String ids) {
        String[] split = ids.split(",");
        List<Long> idsList = new ArrayList<>(split.length);

        for (String id : split) {
            idsList.add(Long.valueOf(id));
        }

        adminUserService.deleteByIds(idsList);
        return "success";
    }
}
