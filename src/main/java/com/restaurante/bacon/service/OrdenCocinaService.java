/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IOrdenCocina;
import com.restaurante.bacon.dao.IRecetaOrdenadaCocina;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.RecetaOrdenada;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean
 */
@Service
public class OrdenCocinaService {
    
    @Autowired
    IOrdenCocina ordenCocina;
    
    @Autowired
    IRecetaOrdenadaCocina recetaOrdenadaCocina;
    
    public Orden crearOrden(Orden orden){
        return ordenCocina.save(orden);
    }
    
    public List<RecetaOrdenada> crearRecetasOrdenadas(List<RecetaOrdenada> recetasOrdenadas){
        
        List<RecetaOrdenada> result = new ArrayList<RecetaOrdenada>();
        
        for (RecetaOrdenada recetasOrdenada : recetasOrdenadas) {
            
            result.add(this.recetaOrdenadaCocina.save(recetasOrdenada));
            
        }
        return result;
    }
    
    
    
}
