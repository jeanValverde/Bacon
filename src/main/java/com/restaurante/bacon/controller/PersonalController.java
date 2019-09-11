/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.ControlCaja;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
        modelo.addAttribute("Personal", this.personalService.getAllUsuario());
        //fin desarrollo 
        //despachos 
        
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }

    @RequestMapping("/mantenedor_personal")
    public String mantenedor_personal(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarollo 
        modelo.addAttribute("Personal", personal);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("PersonalSesion", personal);

        modelo.addAttribute("PersonalSesion", this.personalService.getPersonalSesion(user.getUsername()));

        return "users/administrador/mantenedor_personal";
    }

    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL **CAMBIAR A PORST**
    @RequestMapping("/addPersonal")
    public String addPersonal(Model modelo,
            @RequestParam("rutPersonal") String rutPersonal,
            @RequestParam("nombresPersonal") String nombresPersonal,
            @RequestParam("apePaternoPersonal") String apePaternoPersonal,
            @RequestParam("apeaternoPersonal") String apeMaternoPersonal,
            @RequestParam("fechaNacimientoPersonal") Date fechaNacimientoPersonal,
            @RequestParam("celularPersonal") String celularPersonal,
            @RequestParam("correoPersonal") String correoPersonal,
            @RequestParam("contrasenaPersonal") String contrasenaPersonal,
            @RequestParam("estadoPersonal") BigInteger estadoPersonal,
            @RequestParam("controlCajaCollection") Collection<ControlCaja> controlCajaCollection,
            @RequestParam("idRol") Rol idRol) {

//            this.PersonalService.addPersonal(personal);
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        modelo.addAttribute("PersonalSesion", personal);

        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarollo 
        modelo.addAttribute("Personal", personal);
        modelo.addAttribute("agregar", true);

        return "users/administrador/mantenedor_personal";
    }

     @RequestMapping("/eliminar_personal")
     public String eliminar_personal(Model modelo, @RequestParam("idPersonal")BigDecimal idPersonal ){
     //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
     //sesion
     this.procedureQuery.DeletePersonalById(idPersonal);
         List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        modelo.addAttribute("Personal", personal); 
        
  
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
         
         return "users/administrador/mantenedor_personal";
     }
   
      @RequestMapping("/loadEditarProveedor")
     public String loadEditarProveedor(Model modelo, @RequestParam("idPersonal") BigDecimal idPersonal ){}

     
     return "users/administrador/mantenedor_personal";
}
//       modelo.addAttribute("PersonalSesion", this.personalService.getPersonalSesion(user.getUsername()));

//        Personal person = new Personal();
//
//        //person.setIdPersonal(BigDecimal.valueOf(5));
//        person.setRutPersonal("17.347.762-6");
//        person.setNombresPersonal("Diego Alejandro");
//        person.setApePaternoPersonal("León");
//        person.setApeMaternoPersonal("Plaza");
//        person.setFechaNacimientoPersonal(new Date());
//        person.setCelularPersonal("954714587");
//        person.setCorreoPersonal("diego.leon@gmail.com");
//        person.setContrasenaPersonal(encoder.encode("123"));
//        person.setEstadoPersonal(BigInteger.valueOf(1));
//
//        Rol rol = new Rol();
//        rol.setIdRol(BigDecimal.valueOf(2));
//
//        person.setIdRol(rol);
//
//        this.personalService.addPersonal(person);
//        
//        //fin desarrollo 
//        //despacho 
//        
//        //fin despacacho 
//        //siempre despachar esto por la sesion 
//        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
