package me.alphar.user.api;

import me.alphar.core.Res;
import me.alphar.core.entity.InnerUser;
import me.alphar.user.service.IInnerUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

;

@RestController
@RequestMapping("/innerUser/api")
public class InnerUserApi {

    @Resource
    private IInnerUserService innerUserService;

    @GetMapping("/login/getByLoginName/{loginName}")
    public Res<InnerUser> getByLoginName(@PathVariable String loginName) {
        InnerUser innerUser = innerUserService.getByLoginName(loginName);
        return Res.success(innerUser);
    }

}
