/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IOrdenCocina;
import com.restaurante.bacon.dao.IRecetaOrdenadaCocina;
import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.EstadoOrden;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrden;
import com.restaurante.bacon.dto.RecetaOrdenada;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    
    public List<RecetaOrdenada> calcularAndCrearRecetasOrdenadas(Map<Receta, Integer> recetasPedidas, Orden orden){
        
        List<RecetaOrdenada> crear = new ArrayList<RecetaOrdenada>();
        
        for (Map.Entry<Receta, Integer> ordenes : recetasPedidas.entrySet()) {

            RecetaOrdenada recetaOrden = new RecetaOrdenada();
            
            recetaOrden.setIdOrden(orden);
            recetaOrden.setCantidad(ordenes.getValue()); 
            recetaOrden.setIdReceta(ordenes.getKey()); 
            Integer precio = Integer.parseInt(ordenes.getKey().getPrecioReceta().toString());
            recetaOrden.setValor(precio * ordenes.getValue()); 
               
            crear.add(recetaOrden);
        }
        
        return crearRecetasOrdenadas(crear); 
    }
    
    
    public Orden crearOrdenByTipo(Map<Receta, Integer> recetasPedidas, Short tipo, Cliente cliente, String descripcion){
        
        
        Orden orden = new Orden();
        
        //
        EstadoOrden estado = new EstadoOrden();
        estado.setIdEstadoOrden(1); 
        orden.setIdEstadoOrden(estado); 
        
        //
        Integer totalOrden = calcularTotal(recetasPedidas); 
        orden.setTotalOrden(totalOrden);
        
        Double result = Double.parseDouble(totalOrden.toString()) * 0.19;
        
        //
        Integer subTotal = result.intValue();
        orden.setSubTotal(subTotal);
        
        //
        Integer tiempoPreparacion = calcularTotalTiempo(recetasPedidas); 
        orden.setTiempoPreparacion(tiempoPreparacion);
        
        
        //
        orden.setDescripcion(descripcion); 
        
        //
        orden.setMotivoAnulacion("N/A"); 
        
        //
        orden.setIdCliente(cliente); 
        
        //
        orden.setTipoOrden(tipo); 
        
        //
        orden.setIva(19); 
        
        return this.crearOrden(orden);
        
    }
    
    public Integer calcularTotal(Map<Receta, Integer> busqueda){
        
        Integer salida = 0;
        
        for (Map.Entry<Receta, Integer> ordenes : busqueda.entrySet()) {

           salida = salida + Integer.parseInt(ordenes.getKey().getPrecioReceta().toString()) * ordenes.getValue(); 
           
        }
        
        return salida;
    }
    
    
    public Integer calcularTotalTiempo(Map<Receta, Integer> busqueda){
        
        Integer salida = 0;
        
        Integer count = 1;
        
        for (Map.Entry<Receta, Integer> ordenes : busqueda.entrySet()) {
           count++;
           salida = salida + Integer.parseInt(ordenes.getKey().getDuracionPreparacion().toString()); 
           
        }
        
        Integer total = Integer.parseInt(String.valueOf(salida/count));  
        
        return total ;
    }
    
    
}
