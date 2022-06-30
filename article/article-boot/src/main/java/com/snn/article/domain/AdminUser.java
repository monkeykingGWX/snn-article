package com.snn.article.domain;

import com.snn.article.domain.groups.ChangeUserPassGroup;
import com.snn.article.domain.groups.LoginUserGroup;
import com.snn.article.domain.groups.UserAddGroup;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@ToString
public class AdminUser {
    @NotNull(message = "数据异常", groups = ChangeUserPassGroup.class)
    private Long userId;

    @NotNull(message = "昵称不得为空", groups = UserAddGroup.class)
    @Size(min=2, max=8, message = "昵称长度在2~8个字符之间", groups = UserAddGroup.class)
    private String name;

    @NotBlank(message = "登录账号不得为空", groups = {UserAddGroup.class, LoginUserGroup.class})
    @Size(min=2, max=8, message = "登录账号长度在2~12个字符之间", groups = UserAddGroup.class)
    @Pattern(regexp = "^\\w+$", message = "登录账号只能有数字及字母组成", groups = UserAddGroup.class)
    private String loginName;

    @NotBlank(message = "登录密码不得为空", groups = {ChangeUserPassGroup.class, UserAddGroup.class, LoginUserGroup.class})
    @Size(min=6, max=12, message = "登录密码长度在6~12个字符之间", groups = {ChangeUserPassGroup.class, UserAddGroup.class})
    private String loginPass;

    private String passHalt;

    private Date lastLoginTime;

    private String lastLoginIp;
}