package me.alphar.auth.config;

import me.alphar.core.service.TmUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权服务配置
 * https://github.com/spring-projects/spring-security/blob/master/samples/boot/oauth2authorizationserver/src/main/java/sample/AuthorizationServerConfiguration.java
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private RedisConnectionFactory redisConnectionFactory;

    private TmUserDetailsService tmUserDetailsService;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public AuthorizationServerConfiguration(AuthenticationConfiguration authenticationConfiguration,
                                            RedisConnectionFactory redisConnectionFactory,
                                            TmUserDetailsService tmUserDetailsService) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.redisConnectionFactory = redisConnectionFactory;
        this.tmUserDetailsService = tmUserDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("tomato_web")
//                .secret("{noop}tomato")
//                .scopes("server")
//                .authorizedGrantTypes("authorization_code,password")
//                .redirectUris("http://alphar.me")
//                .accessTokenValiditySeconds(600_000_000)
//                .and()
//                .withClient("tomato_app")
//                .secret("{noop}tomato")
//                .scopes("app")
//                .accessTokenValiditySeconds(600_000_000);

        clients
                .inMemory()
                .withClient("admin-client")
                // 还需要为 secret 加密
//                .secret(passwordEncoder.encode("secret"))
                .secret("{noop}tomato")
                .authorizedGrantTypes("authorization_code", "password")
                .scopes("server")
                .redirectUris("http://alphar.me");

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(tmUserDetailsService)
                .tokenEnhancer(tokenEnhancer());
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("tomato:oauth");
        return redisTokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            // Object principal = authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>(1);
//            additionalInfo.put("user_id", user.getUserId());
//            additionalInfo.put("name", user.getName());
//            additionalInfo.put("user_name", user.getUsername());
//            additionalInfo.put("work_num", user.getWorkNum());
//            additionalInfo.put("mobile", user.getMobile());
//            additionalInfo.put("email", user.getEmail());
//            additionalInfo.put("org_code", user.getOrgCode());
//            additionalInfo.put("is_company_admin", user.getIsCompanyAdmin());
//            additionalInfo.put("from", appId + "_user");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
