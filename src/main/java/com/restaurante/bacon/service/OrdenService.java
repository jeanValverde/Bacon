/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IOrdenDao;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Personal;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andresiero
 */
@Service
public class OrdenService implements UserDetailsService{

    @Autowired
    IOrdenDao ordenDao;
    
    
    
    public void buscarPorId(BigDecimal id){
      
        this.ordenDao.findById(id);
    
    }
    
    
    
    public List<Orden> listarOrden(){
    
    return this.ordenDao.findAll();
    
    }
    
    
        
    
    public Orden bucarId (BigDecimal id){
    Optional <Orden>optinalEntity= ordenDao.findById(id);
    Orden orden =optinalEntity.get();
    return orden;
    
    }
    
    

    
    
//            public List<Rol> listarRol() {
//        return this.rol.findAll();
//    }
    
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
