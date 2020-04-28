package me.alphar.auth.service.impl;

import com.google.common.collect.Lists;
import me.alphar.auth.service.AlpharUserDetailsService;
import me.alphar.auth.service.IUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AlpharUserDetailServiceImpl implements AlpharUserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 1. 根据用户获取用户
        me.alphar.auth.entity.User dbUser = userService.getUserByUsername(s);
        if (dbUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 2. 将用户数据封装到 UserDetail 中，由 SpringSecurity 判断
//        User.UserBuilder user = User.withUsername(s);
        return new User(dbUser.getName(), "{noop}" + dbUser.getPassword(), Lists.newArrayList());
    }
}
