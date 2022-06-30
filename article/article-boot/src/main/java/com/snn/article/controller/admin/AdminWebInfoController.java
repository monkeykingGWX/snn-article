package com.snn.article.controller.admin;

import com.snn.article.domain.WebInfo;
import com.snn.article.service.IWebInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 9:39
 */

@RequestMapping("/admin/webinfo")
@Controller
public class AdminWebInfoController {

    @Resource
    private IWebInfoService webInfoService;

    @GetMapping()
    public String index (ModelMap modelMap) {
        WebInfo webInfo = webInfoService.selectWebInfo();

        modelMap.put("webInfo", webInfo);
        return "admin/webinfo/index";
    }

    @PostMapping("/save")
    public String  save (@Validated WebInfo webInfo) {
        webInfoService.saveWebInfo(webInfo);
        return "success";
    }
}
