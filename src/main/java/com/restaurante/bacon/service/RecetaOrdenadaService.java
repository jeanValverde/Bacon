/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IRecetaOrdenadaDao;
import com.restaurante.bacon.dao.ProcedureQueryOrden;
import com.restaurante.bacon.dao.ProcedureQueryRecetaOrdenada;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.RecetaOrdenada;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe
 */
@Service
public class RecetaOrdenadaService {
    
    @Autowired
    IRecetaOrdenadaDao recetaOrdenadaDao;
    
        @Autowired
    ProcedureQueryRecetaOrdenada procedureQueryRecetaOrdenada;
        
       public List<RecetaOrdenada> listarRecetasDeOrdenes(Integer idOrden){
        return this.procedureQueryRecetaOrdenada.listarRecetaByOrden(idOrden);
    }
}
