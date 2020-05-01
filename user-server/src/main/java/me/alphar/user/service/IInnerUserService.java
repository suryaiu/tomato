package me.alphar.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import me.alphar.user.entity.InnerUser;

public interface IInnerUserService extends IService<InnerUser> {

    int insert(InnerUser user);
}
