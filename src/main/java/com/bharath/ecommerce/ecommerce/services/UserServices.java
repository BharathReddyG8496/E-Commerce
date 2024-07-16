package com.bharath.ecommerce.ecommerce.services;

import com.bharath.ecommerce.ecommerce.DAO.LocalUserJpaRepository;
import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import com.bharath.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    LocalUserJpaRepository userJpaRepository;

    private static final PasswordEncoder encoder=new BCryptPasswordEncoder();
    public LocalUser registerUser(LocalUser user) {
        if(userJpaRepository.existsByEmailIgnoreCase(user.getEmail())||userJpaRepository.existsByUsernameIgnoreCase(user.getUsername())) {
                return null;
            }
            else{
                    user.setRoles(List.of("USER"));
                    user.setPassword(encoder.encode(user.getPassword()));
                    return userJpaRepository.save(user);
                }
    }
    public List<LocalUser> returnUsers(){
        List<LocalUser> all = userJpaRepository.findAll();
        if(all.isEmpty())
            return null;
        else
            return all;
    }
    public LocalUser addNewAdmin(LocalUser newAdmin){
        try{
            newAdmin.setPassword(encoder.encode(newAdmin.getPassword()));
            newAdmin.setRoles(Arrays.asList("USER","ADMIN"));
            return userJpaRepository.save(newAdmin);
        }catch (Exception e){
            return null;
        }
    }

    public LocalUser findUserByUsername(String username){
       return  userJpaRepository.findByUsername(username);
    }

    public boolean DeleteUserByUsername(String username){
        long l = userJpaRepository.deleteByUsername(username);
        return l >= 1;
    }



}
