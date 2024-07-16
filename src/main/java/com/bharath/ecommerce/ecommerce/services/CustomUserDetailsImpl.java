package com.bharath.ecommerce.ecommerce.services;

import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser verifiedUser = userServices.findUserByUsername(username);
        if (verifiedUser == null) {
            throw new UsernameNotFoundException("User not found with username " + username);
        } else {
            List<GrantedAuthority> authorities = verifiedUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
//            authorities.forEach(authority -> System.out.println("Granted Authority: " + authority.getAuthority()));
            return new User(verifiedUser.getUsername(), verifiedUser.getPassword(), authorities);

        }
    }
}

