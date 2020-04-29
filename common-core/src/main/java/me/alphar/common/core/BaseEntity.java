package me.alphar.common.core;

import lombok.Data;
import me.alphar.common.annotation.Comment;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @Comment("主键")
    private Long tid;
    @Comment("创建时间")
    private LocalDateTime createTime;
    @Comment("创建者id")
    private Long creatorTid;
    @Comment("更新时间")
    private LocalDateTime updateTime;
    @Comment("更新人主键")
    private Long updaterTid;
    @Comment("是否被删除")
    private Boolean isDeleted;
    @Comment("版本号(乐观锁)")
    private Integer version;
}
