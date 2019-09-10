/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.Mesa;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.service.MesaService;
import com.restaurante.bacon.service.PersonalService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alejandro
 */
@RequestMapping("/administrador/mesa")
@Controller
public class MesaController {

    //acceder a CRUB y más del personal 
    @Autowired
    MesaService mesaService;
    
    @Autowired
    PersonalService personalService;
    private Object modelo;
    
    @PostMapping("/agregar")
    public String addMesa(Model model,
            @RequestParam("numeroMesa") Integer numeroMesa,
            @RequestParam("cantidadAsientosMesa") Integer cantidadAsientosMesa,
            @RequestParam("estadoMesa") Integer estadoMesa) throws IOException{
        
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        Mesa mesa = new Mesa();
        
        mesa.setNumeroMesa(numeroMesa);
        mesa.setCantidadAsientosMesa(cantidadAsientosMesa);
        mesa.setEstadoMesa(estadoMesa);
        
        //this.mesaService.add(mesa);
        
        model.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedorMesa";
        
    }
    
    @RequestMapping("/mantenedorMesas")
    public String mantenedorMesa(Model model){
        
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
          

        
        //this.mesaService.add(mesa);
        
        model.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        
        //Retornar a la pagina
        return "users/administrador/mantenedorMesa";
        
    }
    
   
    
}
