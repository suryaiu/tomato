package me.alphar.auth.feign;

import me.alphar.core.Res;
import me.alphar.core.entity.InnerUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name = "userserver")
//public interface InnerUserService {
//
//    @GetMapping("/innerUser/api/getByLoginName/{loginName}")
//    Res<InnerUser> getByLoginName(@PathVariable String loginName);
//}
