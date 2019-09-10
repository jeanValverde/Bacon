/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IMesaDao;
import com.restaurante.bacon.dao.ProcedureQueryMesa;
import com.restaurante.bacon.dto.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro
 */
@Service
public class MesaService {
    
    @Autowired
    IMesaDao mesaDao;
    
    @Autowired
    ProcedureQueryMesa procedureQuery;
    
//    public boolean add(Integer numero, Integer cantidadAsientos, Integer estado){
//        return this.procedureQuery.InsertMesa(numero, cantidadAsientos, estado);
//    }
    
    
    public Mesa add(Mesa mesa){
        return this.mesaDao.save(mesa);
    }
    
    
    
}
