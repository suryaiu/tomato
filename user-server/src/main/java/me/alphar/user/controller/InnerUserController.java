package me.alphar.user.controller;

import me.alphar.user.core.PagePara;
import me.alphar.user.core.PageResult;
import me.alphar.core.Res;
import me.alphar.core.entity.InnerUser;;
import me.alphar.user.service.IInnerUserService;
import me.alphar.user.vo.InnerUserVO;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/v1/{tid}")
    public Res<InnerUserVO> getById(@PathVariable("tid") Long tid) {
        InnerUserVO vo = innerUserService.getByTid(tid);
        return Res.success(vo);
    }

    /**
     * 更新用户
     * @param user 用户信息
     * @return Res
     */
    @PutMapping("v1")
    public Res<String> updateUser(@RequestBody InnerUser user) {
        int num = innerUserService.update(user);
        if (num != 1) {
            return Res.error("更新数据失败");
        }
        return Res.success();
    }
}
