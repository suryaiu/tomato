package me.alphar.user.controller;

import me.alphar.user.core.Res;
import me.alphar.user.entity.InnerUser;
import me.alphar.user.mapper.InnerUserMapper;
import me.alphar.user.service.IInnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("innerUser")
public class InnerUserController {

    @Resource
    private IInnerUserService innerUserService;

    @RequestMapping("/v1/list")
    public Res getList() {

        return null;
    }

    @PostMapping("v1")
    public Res addUser(@RequestBody InnerUser user) {
        int num = innerUserService.insert(user);
//        if (num != 1) {
//            return null;
//        }
        return null;
    }
}
