package me.alphar.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.alphar.user.core.PagePara;
import me.alphar.user.core.PageRes;
import me.alphar.user.core.PageResult;

import java.util.List;

/**
 * 分页查询方法封装
 * @param <E> 实体类
 * @param <V> 返回给嵌套的VO类
 */
public interface CommonService<E, V> {

    /**
     *
     * @param queryWrapper 查询条件
     * @param pagePara 分页参数
     * @param mapper mapper
     * @param result 分页结果
     * @return list
     */
    default List<E> getPageList(QueryWrapper<E> queryWrapper, PagePara pagePara,
                                BaseMapper mapper, PageResult<V> result) {
        Page dbPage;
        List<E> list;
        if (pagePara != null && pagePara.getCurrPage()!=null && pagePara.getPageSize()!=null) {
            dbPage = new Page(pagePara.getCurrPage(), pagePara.getPageSize());
            IPage<V> iPage = mapper.selectPage(dbPage, queryWrapper);
            list = (List<E>) iPage.getRecords();
            PageRes pageRes = new PageRes(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages());
            result.setPage(pageRes);
        } else {
            list = mapper.selectList(queryWrapper);
        }
        return list;
    }
}
