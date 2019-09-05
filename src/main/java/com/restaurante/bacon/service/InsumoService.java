/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoDao;
import com.restaurante.bacon.dto.Insumo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
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
   
    public Insumo ingresarInsumo(Insumo insumo) {
        return this.insumoDao.save(insumo);
    }
    
    public Insumo retornarInsumoById(Integer  idInsumo){
        Optional<Insumo> optinalEntity =  insumoDao.findById(idInsumo);
        Insumo insumo = optinalEntity.get();
        return insumo;
    }
    public List<Insumo> listarInsumos(){
        return this.insumoDao.findAll();
    }
   
    
}
