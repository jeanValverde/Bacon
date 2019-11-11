/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrdenada;
import com.restaurante.bacon.dto.RecetaOrdenadaByOrden;
import com.restaurante.bacon.service.PagarOrdenService;
import com.restaurante.bacon.service.RecetaOrdenadaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Felipe
 */
@RequestMapping("/cliente/pagar")
@Controller
public class PagarOrdenController {
    
    @Autowired
    PagarOrdenService pagarOrdenService;
    
    @Autowired
    RecetaOrdenadaService recetaService;
    
    
    
  
     
    @RequestMapping("/pagarOrdenes")
    public String pagarOrdenes(Model modelo,HttpSession sesion, Integer idOrden) {
     
        //sesion 
        Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        List<Orden> ordenes = new ArrayList<Orden>();
        ordenes = this.pagarOrdenService.listarOrdenes((BigDecimal.valueOf(cliente.getIdCliente())));
        
        Integer totalOrden;
        totalOrden = this.pagarOrdenService.retornarPago(cliente.getIdCliente());
        modelo.addAttribute("precioTotal",totalOrden);
        
        List<Receta> recetas = new ArrayList<Receta>();
        
        
        List<RecetaOrdenada> recetaOrdenada = new ArrayList<RecetaOrdenada>();
        //recetaOrdenada = this.recetaService.listarRecetasDeOrdenes(3);
        
        List<RecetaOrdenadaByOrden> recetaByOrden = new ArrayList<RecetaOrdenadaByOrden>();
        
       for(Orden orden: ordenes){
            System.out.println("Orden N° "+orden.getIdOrden());
           RecetaOrdenadaByOrden recetasByOrden = new RecetaOrdenadaByOrden();
           
           recetasByOrden.setOrden(orden);
          
           
            List<RecetaOrdenada> recetasOrdenadas = new ArrayList<RecetaOrdenada>();
           
               
             
           
             recetasOrdenadas = this.recetaService.listarRecetasDeOrdenes(orden.getIdOrden());
             
             recetasByOrden.setRecetaOrdenada(recetasOrdenadas);
             
           recetaByOrden.add(recetasByOrden);
           
       }
       
        
         //modelo.addAttribute("ordenes", ordenes);
         modelo.addAttribute("recetaByOrden", recetaByOrden);
        
         
         
       
        
   
   
        
   
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
   
        //
        return "users/cliente/pagarOrdenes";
    }
    
   

    
     
//    public ResponseEntity listarRecetasByOrden(Model modelo, HttpSession sesion, @RequestParam("idOrden") Integer idOrden) {
//
//        List<RecetaOrdenada> recetaByOrden = this.recetaService.listarRecetasDeOrdenes(idOrden);
//
//        return new ResponseEntity(recetaByOrden, HttpStatus.OK);
//    }
    
    
    
}