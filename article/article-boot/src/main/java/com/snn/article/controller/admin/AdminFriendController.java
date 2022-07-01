package com.snn.article.controller.admin;

import com.github.pagehelper.PageInfo;
import com.snn.article.domain.FriendLink;
import com.snn.article.domain.OrderNum;
import com.snn.article.domain.Pageination;
import com.snn.article.service.IFriendLinkService;
import com.snn.article.utils.HttpQueryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 10:49
 */

@RequestMapping("/admin/friend")
@Controller
public class AdminFriendController {

    @Resource
    private IFriendLinkService friendLinkService;

    @RequestMapping
    public String listPage (FriendLink friendLink, Pageination pageination, ModelMap mmap) throws IllegalAccessException {
        PageInfo<FriendLink> pageInfo = friendLinkService.selectList(pageination, friendLink);
        String searchQuery = HttpQueryUtils.object2QueryStr(friendLink);

        mmap.put("pageInfo", pageInfo);
        mmap.put("search", friendLink);
        mmap.put("searchQuery", searchQuery);
        return "admin/friend/list";
    }


    @GetMapping ("/create")
    public String addPage () {
        return "admin/friend/add";
    }

    @PostMapping("/create")
    public String add (@Validated FriendLink friendLink, ModelMap modelMap) {
        friendLinkService.addFriendLink(friendLink);
        modelMap.put("jumpUrl", "/admin/friend/create");
        return "success";
    }

    @GetMapping("/removeOne/{linkId}")
    public String removeOne (@PathVariable Long linkId) {
        friendLinkService.removeOne(linkId);
        return "success";
    }

    @GetMapping("/removeSome")
    public String removeSome (String ids) {
        friendLinkService.removeSome(AdminCommonFunc.ids2List(ids));
        return "success";
    }

    @GetMapping("/edit/{linkId}")
    public String editPage (@PathVariable Long linkId, ModelMap mmap) {
        FriendLink friendLink = friendLinkService.selectByPrimaryId(linkId);
        mmap.put("link", friendLink);

        return "admin/friend/edit";
    }

    @PostMapping("/edit")
    public String edit (@Validated FriendLink friendLink) {
        friendLinkService.updateFriendLink(friendLink);

        return "success";
    }

    @GetMapping("/orderby")
    public String setOrder (String data) {
        List<OrderNum> orderNums = AdminCommonFunc.dealOrderStr(data);

        friendLinkService.updateOrder(orderNums);
        return "success";
    }
}
