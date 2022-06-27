package com.snn.article.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class AdminUser {
    private Long userId;

    private String name;

    private String loginName;

    private String loginPass;

    private String passHalt;

    private Date lastLoginTime;

    private String lastLoginIp;
}