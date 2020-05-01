package me.alphar.user.controller;

import com.google.common.collect.Lists;
import me.alphar.common.core.Res;
import me.alphar.user.dto.MenuDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuMangerController {

    @GetMapping
    public Res<List<MenuDTO>> menuList() {
        List<MenuDTO> menuDTOList = Lists.newArrayList();
        List<MenuDTO> userSubMenu = Lists.newArrayList();
        userSubMenu.add(new MenuDTO("用户列表", 11, 11, "userList", null));
        menuDTOList.add(new MenuDTO("用户管理", 1, 1, "users", userSubMenu));

        menuDTOList.add(new MenuDTO("权限管理", 2, 2, "roles",
                Lists.newArrayList(new MenuDTO("角色列表", 21, 1, "roleList", null),
                        new MenuDTO("权限列表", 22, 2, "permissionList", null))));

        return new Res<>(0, "suc", menuDTOList);
    }

}
