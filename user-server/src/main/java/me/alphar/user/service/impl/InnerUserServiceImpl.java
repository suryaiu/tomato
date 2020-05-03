package me.alphar.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.alphar.user.core.BusinessException;
import me.alphar.user.core.PagePara;
import me.alphar.user.core.PageResult;
import me.alphar.user.entity.InnerUser;
import me.alphar.user.mapper.InnerUserMapper;
import me.alphar.user.service.CommonService;
import me.alphar.user.service.IInnerUserService;
import me.alphar.user.vo.InnerUserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InnerUserServiceImpl extends ServiceImpl<InnerUserMapper, InnerUser>
        implements IInnerUserService, CommonService<InnerUser, InnerUserVO> {

    @Resource
    private InnerUserMapper innerUserMapper;

    @Override
    public int insert(InnerUser user) {
        return innerUserMapper.insert(user);
    }

    @Override
    public int update(InnerUser user) {
        return innerUserMapper.updateById(user);
    }

    @Override
    public PageResult<InnerUserVO> getUserPage(PagePara pagePara) {
        if (pagePara == null || pagePara.getPageSize() == null || pagePara.getCurrPage() == null) {
            throw new BusinessException("分页参数有误");
        }
        PageResult<InnerUserVO> result = new PageResult<>();
        QueryWrapper<InnerUser> queryWrapper = new QueryWrapper<>();
        // 调用分页查询方法
        List<InnerUser> pageList = getPageList(queryWrapper, pagePara, innerUserMapper, result);
        List<InnerUserVO> voList = InnerUserVO.transferVoList(pageList);
        result.setList(voList);
        return result;
    }

    @Override
    public InnerUserVO getByTid(Long tid) {
        if (tid == null) {
            throw new BusinessException("住键不能为空");
        }
        InnerUser innerUser = innerUserMapper.selectById(tid);
        return InnerUserVO.transferVO(innerUser);
    }
}
