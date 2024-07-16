package com.bharath.ecommerce.ecommerce.services;

import com.bharath.ecommerce.ecommerce.DAO.LocalUserJpaRepository;
import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import com.bharath.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    LocalUserJpaRepository userJpaRepository;
    public LocalUser registerUser(LocalUser user) throws UserAlreadyExistsException {
        try {
            if(userJpaRepository.existsByEmailIgnoreCase(user.getEmail())||userJpaRepository.existsByUsernameIgnoreCase(user.getUsername())) {
                throw new UserAlreadyExistsException();
            }
            return userJpaRepository.save(user);
        }
        catch (Exception e){
            return null;
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
            newAdmin.setRoles(Arrays.asList("USER","ADMIN"));
            return userJpaRepository.save(newAdmin);
        }catch (Exception e){
            return null;
        }
    }



}
