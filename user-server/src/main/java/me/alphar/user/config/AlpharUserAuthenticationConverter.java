//package me.alphar.user.config;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.Map;
//
//public class AlpharUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
//
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public Authentication extractAuthentication(Map<String, ?> map) {
//        if (map.containsKey(USERNAME)) {
//            Object principal = map.get(USERNAME);
//            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
//            if (userDetailsService != null) {
//                UserDetails user = userDetailsService.loadUserByUsername((String) map.get(USERNAME));
//                authorities = user.getAuthorities();
//                principal = user;
//            }
//            System.out.println(authorities);
//            if (principal instanceof String && map.containsKey("user_id")) {
////                UserDetails user = new UserDetails(
////                        Convert.toLong(map.get("user_id"))
////                        , (String) map.get("user_name")
////                        , ""
////                        , (String) map.get("name")
////                        , (String) map.get("mobile")
////                        , (String) map.get("work_num")
////                        , (String) map.get("email")
////                        , (String) map.get("org_code")
////                        , (Boolean) map.get("is_company_admin")
////                        , true
////                        , true
////                        , true
////                        , true
////                        , authorities);
//
////                principal = user;
//            }
//            return new UsernamePasswordAuthenticationToken(principal, "N/A", getAuthorities(map));
//        }
//        return null;
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
//
//        Object authorities = map.get("authorities");
//        if (authorities instanceof String) {
//            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
//        }
//        if (authorities instanceof Collection) {
//            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
//                    .collectionToCommaDelimitedString((Collection<?>) authorities));
//        }
//        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
//    }
//
//    @Override
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//}
