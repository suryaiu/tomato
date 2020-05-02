package me.alphar.user.core;

import lombok.Data;

@Data
public class PageRes extends PagePara {

    public PageRes(long currPage, long pageSize, long dataCount, long pageCount) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.dataCount = dataCount;
        this.pageCount = pageCount;
    }

}
