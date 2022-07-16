package com.example.emailmanager.EmailManager.Service;

import com.example.emailmanager.Model.Role;
import com.example.emailmanager.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User with username " + username + " doesn't exist");
        }
        List<GrantedAuthority> authorites = getUserAuthorities(user.getRoles());
        return generateUserDetails(user, authorites);
    }


    private List<GrantedAuthority> getUserAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    private UserDetails generateUserDetails(User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnable(),
                true,
                true,
                true,
                authorities);
    }//TODO add accountNonExpired, CredentialsNonExpired, AccountNonLocked to entity and DB
}
