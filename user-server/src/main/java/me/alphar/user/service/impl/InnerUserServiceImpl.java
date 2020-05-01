package me.alphar.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.alphar.user.entity.InnerUser;
import me.alphar.user.mapper.InnerUserMapper;
import me.alphar.user.service.IInnerUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InnerUserServiceImpl extends ServiceImpl<InnerUserMapper, InnerUser> implements IInnerUserService {

    @Resource
    private InnerUserMapper innerUserMapper;

    @Override
    public int insert(InnerUser user) {
        return innerUserMapper.insert(user);
    }
}
