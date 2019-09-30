/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IPagarOrdenDao;
import com.restaurante.bacon.dto.Orden;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Felipe
 */

public class PagarOrdenService {
    
    
    @Autowired
    IPagarOrdenDao pagarOrdenDao;
    
        public List<Orden> listarOrdenes(){
        return this.pagarOrdenDao.findAll();
    }
        
            public Orden retornarOrdenById(BigDecimal  idOrden){
        Optional<Orden> optinalEntity =  pagarOrdenDao.findById(idOrden);
        Orden orden = optinalEntity.get();
        return orden;
    }
}
