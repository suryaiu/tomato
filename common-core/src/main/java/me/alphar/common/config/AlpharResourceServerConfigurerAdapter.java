package me.alphar.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;

@RefreshScope
public class AlpharResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private AuthenticationEntryPoint point;
//    @Autowired
//    private AccessDeniedHandler handler;
    @Autowired
    private RemoteTokenServices remoteTokenServices;
    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;
    @Value("${spring.application.name}")
    private String resourceId;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId).stateless(true);
//        resources.authenticationEntryPoint(point).accessDeniedHandler(handler);
        getUserInfo(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Since we want the protected resources to be accessible in the UI as well we need
        // session creation to be allowed (it's disabled by default in 2.0.6)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().antMatchers(permitAllUrlProperties.getPermitAllUrls().toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


    /**
     * 上下文中获取用户全部信息
     *
     * @param resources
     */
    private void getUserInfo(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        AlpharUserAuthenticationConverter userTokenConverter = new AlpharUserAuthenticationConverter();

        // 设置userDetailService
//        userTokenConverter.setUserDetailsService(userDetailsService);

//        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        remoteTokenServices.setRestTemplate(restTemplate);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.tokenServices(remoteTokenServices);
    }}
