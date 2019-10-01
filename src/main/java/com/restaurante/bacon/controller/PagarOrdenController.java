/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.service.PagarOrdenService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Felipe
 */
@RequestMapping("/cliente/pagar")
@Controller
public class PagarOrdenController {
    
    @Autowired
    PagarOrdenService pagarOrdenService;
    
  
     
    @RequestMapping("/pagar_ordenes")
    public String pagarOrdenes(Model modelo,HttpSession sesion) {
     
        //sesion 
       // Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        
        List<Orden> ordenes = new ArrayList<Orden>();
        ordenes = this.pagarOrdenService.listarOrdenes((BigDecimal.valueOf(1)));
   
   
        
   
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
   
        //
        return "users/cliente/pagarOrdenes";
    }
    
}
