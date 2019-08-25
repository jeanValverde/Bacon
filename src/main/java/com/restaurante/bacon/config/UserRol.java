/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.config;

import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.service.PersonalService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jean
 */
public class UserRol {
    
    @Autowired
    PersonalService personalService;

    private String username = "";
    private String rol = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UserRol() {
        getUser();
    }
   
    //obtiene el usuario de la sesion activa 
    private void getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        
        String userName = userDetails.getUsername();

        Collection<? extends GrantedAuthority> rol = userDetails.getAuthorities();
        
        this.username = userName;
        this.rol = rol.toString();
        
    }
    
    
   
    
}
