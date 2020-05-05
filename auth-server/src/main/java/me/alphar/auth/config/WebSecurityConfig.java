package me.alphar.auth.config;

import me.alphar.core.service.AuthenticationProvider;
import me.alphar.core.service.TmUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private TmUserDetailsService tmUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/token/**", "/actuator/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tmAuthenticationProvider());
    }

    @Bean
    AuthenticationProvider tmAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.setUserDetailsService(tmUserDetailsService);
        return authenticationProvider;
    }

//    @Resource
//    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        // 设置默认的加密方式
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser(
////                User.withUsername("tomato")
////                .password("{noop}666666")
////                .roles("USER")
////        );
//        // 在内存中创建用户并为密码加密
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}123456").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}123456").roles("ADMIN");
//
//    }
//
//    /**
//     * spring Boot 2 配置，这里要bean 注入
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}
