package com.snn.article.service;

import com.snn.article.domain.WebInfo;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-30 9:54
 */
public interface IWebInfoService {

    WebInfo selectWebInfo ();

    int saveWebInfo (WebInfo webInfo);
}
