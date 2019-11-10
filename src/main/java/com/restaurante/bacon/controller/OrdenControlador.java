/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.service.OrdenService;
import com.restaurante.bacon.service.PersonalService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Andresiero
 */
@RequestMapping("/administrador_orden")
@Controller
public class OrdenControlador {
    @Autowired
    OrdenService ordenDao;
    
     @Autowired
    PersonalService personalService;
     
     
    @RequestMapping("/index")
    public String prueba(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
         modelo.addAttribute("rolpersonal", this.personalService.listarRol());   
        //desarrollo aca 
        modelo.addAttribute("Personal", this.personalService.getAllUsuario());
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }
    
       @RequestMapping("/mantenedor_orden")
    public String mantenedor_orden(Model modelo,
            @RequestParam("idOrden") BigDecimal idOrden) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        Orden ordenes = this.ordenDao.bucarId(idOrden);
               
     
                       
        modelo.addAttribute("ordenes", ordenes);
       

        modelo.addAttribute("personalSesion", personal);

        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/resumen_orden";

    }
    
    
    
}
