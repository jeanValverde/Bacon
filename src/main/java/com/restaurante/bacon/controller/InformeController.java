/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.Informe;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.service.InformeService;
import com.restaurante.bacon.service.PersonalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alejandro
 */
@RequestMapping("/informe")
@Controller
public class InformeController {
    //acceder a CRUB y más del personal 
    @Autowired
    InformeService informeService;

    @Autowired
    PersonalService personalService;
    
   @RequestMapping("/verInformesReceta")
    public String verInformes(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 
        
        
        modelo.addAttribute("informe", this.informeService.listarInforme());
        
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
                
        return "informe/informe";

        
    }
    
    @RequestMapping("/graficoReceta")
    public ResponseEntity graficoReceta(Model modelo) {
        
        List<Informe> informes = this.informeService.listarInforme();
        
        return new ResponseEntity(informes, HttpStatus.OK);
    }
}
