/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IRolDao;
import com.restaurante.bacon.dto.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean
 */
@Service
class RolService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IRolDao rolService;
    
    //se implementan los metodos declarados en la interfaz 
    public List<Rol> getAllUsuario() {
        return this.rolService.findAll();
    }
    
}
