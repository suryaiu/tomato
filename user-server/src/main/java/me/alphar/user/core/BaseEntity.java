package me.alphar.user.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import me.alphar.user.annotation.Comment;
import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @TableId
    @Comment("主键")
    @TableField(fill = FieldFill.INSERT)
    private Long tid;

    @Comment("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Comment("创建者id")
    @TableField(fill = FieldFill.INSERT)
    private Long creatorTid;

    @Comment("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Comment("更新人主键")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updaterTid;

    @Comment("是否被删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;

    @Comment("版本号(乐观锁)")
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
