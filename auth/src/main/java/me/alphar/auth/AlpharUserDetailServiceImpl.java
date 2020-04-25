package me.alphar.auth;

import com.google.common.collect.Lists;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AlpharUserDetailServiceImpl implements AlpharUserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User.UserBuilder user = User.withUsername(s);
        return new User(s, "{noop}123456", Lists.newArrayList());
    }
}
