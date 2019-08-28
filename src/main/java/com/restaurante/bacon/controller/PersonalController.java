/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jean
 * 
 * No crear metodos propios en los controladores para eso esta el servicio 
 * 
 * 
 */
@RequestMapping("/administrador")
@Controller
public class PersonalController {

    //acceder a CRUB y más del personal 
    @Autowired
    PersonalService personalService;

    //para ingresar una contraseña encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;


    @RequestMapping("/index")
    public String prueba(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarrollo aca 
        
        
        
   
        //fin desarrollo 
        //despachos 
        
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }
    @RequestMapping("/mantenedor")
    public String mantenedor(Model modelo,@RequestParam("pagina") String pagina) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarrollo aca 
        
        
        
   
        //fin desarrollo 
        //despachos 
        
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/"+pagina;
    }
    
    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL **CAMBIAR A PORST**
    @RequestMapping("/add")
    public String add(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarollo 
        Personal person = new Personal();

        //person.setIdPersonal(BigDecimal.valueOf(5));
        person.setRutPersonal("17.347.762-6");
        person.setNombresPersonal("Diego Alejandro");
        person.setApePaternoPersonal("León");
        person.setApeMaternoPersonal("Plaza");
        person.setFechaNacimientoPersonal(new Date());
        person.setCelularPersonal("954714587");
        person.setCorreoPersonal("diego.leon@gmail.com");
        person.setContrasenaPersonal(encoder.encode("123"));
        person.setEstadoPersonal(BigInteger.valueOf(1));

        Rol rol = new Rol();
        rol.setIdRol(BigDecimal.valueOf(2));

        person.setIdRol(rol);

        this.personalService.addPersonal(person);
        
        //fin desarrollo 
        //despacho 
        
        //fin despacacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }
    
    
   

}
