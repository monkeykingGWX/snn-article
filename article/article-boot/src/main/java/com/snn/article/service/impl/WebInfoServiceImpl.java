package com.snn.article.service.impl;

import com.snn.article.dao.WebInfoMapper;
import com.snn.article.domain.WebInfo;
import com.snn.article.service.IWebInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 9:55
 */

@Service
public class WebInfoServiceImpl implements IWebInfoService {

    @Resource
    private WebInfoMapper webInfoMapper;

    @Override
    public WebInfo selectWebInfo() {
        return webInfoMapper.selectOne();
    }

    @Override
    public int saveWebInfo(WebInfo webInfo) {
        Long webId = webInfo.getWebId();

        if (webId == null) {
            return webInfoMapper.insert(webInfo);
        } else {
            return webInfoMapper.updateByPrimaryKey(webInfo);
        }
    }
}
