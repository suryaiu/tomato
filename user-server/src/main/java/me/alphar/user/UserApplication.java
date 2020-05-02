package me.alphar.user;

import me.alphar.user.config.AlpharResourceServerConfigurerAdapter;
import me.alphar.user.config.PermitAllUrlProperties;
import me.alphar.user.config.ResourceServerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
@MapperScan("me.alphar.user.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerConfig.class, PermitAllUrlProperties.class, AlpharResourceServerConfigurerAdapter.class})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        System.out.println("user服务启动成功");
    }
}
