package me.alphar.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 自定义 实现jdbc方式查询客户端 并重写原生获取客户端方式使其支持缓存    extends JdbcClientDetailsService
//        clients.withClientDetails();
        clients.inMemory()
                .withClient("client")
                .secret("tomato")
                .authorizedGrantTypes("password")
                .scopes("server")
                .redirectUris("http://alphar.me");
    }
}
