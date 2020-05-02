package me.alphar.user.core;

import lombok.Data;

@Data
public class PagePara {

    public Long pageSize;

    public Long currPage;

    public Long pageCount;

    public Long dataCount;

}
