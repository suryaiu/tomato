package me.alphar.user.controller;

import me.alphar.user.core.PagePara;
import me.alphar.user.core.PageResult;
import me.alphar.user.core.Res;
import me.alphar.user.entity.InnerUser;
import me.alphar.user.service.IInnerUserService;
import me.alphar.user.vo.InnerUserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("innerUser")
public class InnerUserController {

    @Resource
    private IInnerUserService innerUserService;

    @RequestMapping("/v1/page")
    public Res<PageResult<InnerUserVO>> getList(PagePara pagePara) {
        PageResult<InnerUserVO> voList = innerUserService.getUserPage(pagePara);
        return Res.success(voList);
    }

    @PostMapping("v1")
    public Res<String> addUser(@RequestBody InnerUser user) {
        int num = innerUserService.insert(user);
        if (num != 1) {
            return Res.error("新增用户失败");
        }
        return Res.success();
    }
}
