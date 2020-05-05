//package me.alphar.auth.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import me.alphar.auth.dao.UserDAO;
//import me.alphar.auth.entity.User;
//import me.alphar.auth.service.IUserService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class UserServiceImpl implements IUserService {
//
//    @Resource
//    private UserDAO userDAO;
//
//    @Override
//    public User getUserByUsername(String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", username);
//        return userDAO.selectOne(queryWrapper);
//    }
//}
