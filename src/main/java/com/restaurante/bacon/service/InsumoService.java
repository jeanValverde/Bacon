/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoDao;
import com.restaurante.bacon.dto.Insumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean
 */
@Service
public class InsumoService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IInsumoDao insumoDao;
    
    //se implementan los metodos declarados en la interfaz 
   
    public Insumo addInsumo(Insumo insumo) {
        return this.insumoDao.save(insumo);
    }
    public List<Insumo> listarInsumos(){
        return this.insumoDao.findAll();
    }
   
    
}
