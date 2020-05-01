package me.alphar.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import me.alphar.user.annotation.Comment;
import me.alphar.user.core.BaseEntity;

import java.time.LocalDate;

@Data
@TableName("inner_user")
public class InnerUser extends BaseEntity {
    @Comment("登录名称")
    private String loginName;
    @Comment("真实名称")
    private String realName;
    @Comment("密码")
    private String password;
    @Comment("性别")
    private Integer gender;
    @Comment("生日")
    private LocalDate birthday;
    @Comment("用户状态")
    private Integer state;
    @Comment("电话号码")
    private String phone;
}
