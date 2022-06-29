package com.snn.article.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@ToString
public class AdminUser {
    @NotNull(message = "数据异常", groups = ChangeUserPassGroup.class)
    private Long userId;

    @NotNull(message = "昵称不得为空")
    @Size(min=2, max=8, message = "昵称长度在2~8个字符之间")
    private String name;

    @NotNull(message = "登录账号不得为空")
    @Size(min=2, max=8, message = "登录账号长度在2~12个字符之间")
    @Pattern(regexp = "^\\w+$", message = "登录账号只能有数字及字母组成")
    private String loginName;

    @NotNull(message = "登录密码不得为空", groups = ChangeUserPassGroup.class)
    @Size(min=6, max=12, message = "登录密码长度在6~12个字符之间", groups = ChangeUserPassGroup.class)
    private String loginPass;

    private String passHalt;

    private Date lastLoginTime;

    private String lastLoginIp;
}