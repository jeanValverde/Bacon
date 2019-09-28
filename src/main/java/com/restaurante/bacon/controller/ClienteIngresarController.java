/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente")
@Controller
public class ClienteIngresarController {
    
    
    
    
    
    @PostMapping("/ingresar")
    public ModelAndView prueba(Model modelo, HttpSession sesion) {
      
        //aca se registra
       
        
        sesion.setAttribute("sesionCliente", "idCliente");
        
       
        
        //
        
        //
        return new ModelAndView("redirect:/cliente/pedirOrden/?tipo=2");
    }
    
    
    
    
    
    
}
