package me.alphar.common.core;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    /* 主键 */
    private Long tid;
    /* 创建时间 */
    private LocalDateTime createTime;
    /* 创建者 id */
    private Long creatorTid;
    /* 更新时间 */
    private LocalDateTime updateTime;
    /* 更新人主键 */
    private Long updaterTid;
    /* 是否被删除 */
    private Boolean isDeleted;
}
