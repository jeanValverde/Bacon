/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.ProcedureQueryPersonal;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.service.PersonalService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 * 
 * No crear metodos propios en los controladores para eso esta el servicio 
 * 
 */
@Controller
public class IndexController {

    //Acceder a metodos CRUB y más de presonal 
    @Autowired
    PersonalService personalService;

    //Aceeder a todos los procedimientos almacenados 
    @Autowired
    ProcedureQueryPersonal procedureQuery;

    //Para ingresar una contraseña encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;

    //Redirecciona al controlador adecuado según el rol del usuario despues de iniciar sesión 
    @RequestMapping("/")
    public ModelAndView index(Model modelo) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarollo 

        String page;

        switch (personal.getIdRol().getDescripcionRol()) {
            case "ADMIN":
                page = "/administrador/index";
                break;
            case "BODEGA":
                page = "/bodega/index";
                break;
            case "COCINA":
                page = "/cocina/index";
                break;
            case "BAR":
                page = "/bar/index";
                break;
            case "FINANZAS":
                page = "/finanzas/index";
                break;
            case "GARZON":
                page = "/garzon/index";
                break;
            default:
                page = "/login";
        }
        
        //fin desarrollo 
        
        //despacho 
        return new ModelAndView("redirect:" + page);
    }

    @RequestMapping("/login")
    public String login(Model modelo) {
        return "login";
    }

    @RequestMapping("/perfil")
    public String perfil(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarrollo 
        String edad = this.personalService.getEdad(personal.getFechaNacimientoPersonal());
        
        
        //despacho 
        modelo.addAttribute("edad", edad);
        //fin desoacho 
        //siempre despchar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        //cargar el html *nombre*
        return "perfil";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(Model modelo, @RequestParam("contrasena") String contrasena) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarrollo 
        String edad = this.personalService.getEdad(personal.getFechaNacimientoPersonal());

        this.procedureQuery.updateContrasenaPersonal(Integer.valueOf(personal.getIdPersonal().intValue()), encoder.encode(contrasena));

      
        //fin desarollo 
        //despacho 
        modelo.addAttribute("edad", edad);
        //fin despacho
        //siempre despchar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        //cargar el html *nombre*
        return "perfil";
    }

    @PostMapping("/updatePerfil")
    public String updatePerfil(Model modelo,
            @RequestParam("nombre") String nombre,
            @RequestParam("apePaterno") String paterno,
            @RequestParam("apeMaterno") String materno,
            @RequestParam("fechaNacimiento") String nacimiento,
            @RequestParam("celular") String celular,
            @RequestParam("correo") String correo) {
        
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        //desarollo
        
        String edad = this.personalService.getEdad(personal.getFechaNacimientoPersonal());
        
        Date FechaNacimiento = PersonalService.ParseFecha(nacimiento);
        
        this.procedureQuery.updatePerfilPersonal(personal.getIdPersonal(), nombre , paterno, materno, FechaNacimiento, celular, correo);
        
        
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
         modelo.addAttribute("edad", edad);
        //fin despacho 
        
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        //cargar el html *nombre*
        return "perfil";
    }

}
