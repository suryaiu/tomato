package me.alphar.common.entity;

import lombok.Data;
import me.alphar.common.core.BaseEntity;

import java.time.LocalDate;

@Data
public class InnerUser extends BaseEntity {
    /* 登录名称 */
    private String loginName;
    /* 真实名称 */
    private String realName;
    /* 密码 */
    private String password;
    /* 性别 */
    private Integer gender;
    /* 生日 */
    private LocalDate birthday;
    /* 用户状态 */
    private Integer status;
    /* 电话号码 */
    private String phone;
}
