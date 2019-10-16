/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.ProcedureQueryPersonal;
import com.restaurante.bacon.dto.ControlCaja;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jean
 *
 * No crear metodos propios en los controladores para eso esta el servicio
 *
 *
 */
@RequestMapping("/administrador_personal")
@Controller
public class PersonalControlador {

    //acceder a CRUB y mÃ¡s del personal 
    @Autowired
    PersonalService personalService;

    @Autowired
    ProcedureQueryPersonal procedureQuery;

    //para ingresar una contraseÃ±a encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;

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
    
    
//        @RequestMapping("/personal")
//    public String personal(Model modelo) {
//        //sesion 
//        UserRol user = new UserRol();
//        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
//        //sesion 
//        //desarrollo aca 
//
//        modelo.addAttribute("rol", this.personalService.listarRol());
//
//        modelo.addAttribute("tipoForm", "agregar");
//
//        modelo.addAttribute("personal", this.personalService.getAllUsuario());
//
//        //fin desarrollo 
//        //despachos 
//        //fin despacho 
//        //siempre despachar esto por la sesion 
//        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
//        //
//        return "users/administrador/mantenedor_personal";
//    }

    @RequestMapping("/mantenedor_personal")
    public String mantenedor_personal(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarrollo aca 
        modelo.addAttribute("rolpersonal", this.personalService.listarRol());
        
        modelo.addAttribute("personales", personales);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("modificar", true);

        modelo.addAttribute("personalSesion", personal);

        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_personal";

    }

    @PostMapping("/addPersonal")
    public String addPersonal(Model modelo,
            @RequestParam("rutPersonal") String rutPersonal,
            @RequestParam("nombresPersonal") String nombresPersonal,
            @RequestParam("apePaternoPersonal") String apePaternoPersonal,
            @RequestParam("apeMaternoPersonal") String apeMaternoPersonal,
            @RequestParam("fechaNacimientoPersonal") String fechaNacimientoPersonal,
            @RequestParam("celularPersonal") String celularPersonal,
            @RequestParam("correoPersonal") String correoPersonal,
            @RequestParam("contrasenaPersonal") String contrasenaPersonal,
            @RequestParam("rolpersonal") Integer rolpersonal) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        String edad = this.personalService.getEdad(personal.getFechaNacimientoPersonal());

        Date fechaNacimiento = PersonalService.ParseFecha(fechaNacimientoPersonal);

        Personal personal1 = this.personalService.findByRut(rutPersonal);

        Personal perso = new Personal();

        
        String contra = encoder.encode(contrasenaPersonal);
        perso.setContrasenaPersonal(contra);
        
        
        BigInteger estado = BigInteger.valueOf(1);
        
       perso.setEstadoPersonal(estado);
        
        
//        perso.setEstadoPersonal(BigInteger.valueOf(Integer.parseInt(estado.toString())));
        
//      this.procedureQuery.updateContrasenaPersonal(Integer.valueOf(perso.getIdPersonal().intValue()),(contra));
        
//        personal1.setIdRol(rolpersonal);
        
        
                 
        
        try {
            this.procedureQuery.InsertPersonal(rutPersonal, nombresPersonal, apePaternoPersonal, apeMaternoPersonal, fechaNacimiento, celularPersonal, correoPersonal, contra,estado,rolpersonal);
//            this.procedureQuery.InsertPersonal(rutPersonal, nombresPersonal, apePaternoPersonal, apeMaternoPersonal, fechaNacimiento, celularPersonal, correoPersonal, contrasenaPersonal, rol);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //desarollo
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
        //fin despacho 
        //siempre despachar esto por la sesion 

        modelo.addAttribute("personalSesion", personal);

        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarrollo aca 
        modelo.addAttribute("personales", personales);
        modelo.addAttribute("agregar", true);

        //
        //cargar el html nombre
        return "users/administrador/mantenedor_personal";

    }

    @RequestMapping("/cargar_personal")
    public String cargarPersonal(Model modelo, @RequestParam("rutPersonal") String rut) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        modelo.addAttribute("roles", this.personalService.listarRol());
        
        Personal perso = this.personalService.findByRut(rut);
        
        List <Personal> personales = this.personalService.getAllUsuario();
        
        modelo.addAttribute("personales",personales);
        
        modelo.addAttribute("persoEdit",perso);
        
        modelo.addAttribute("tipoForm", "editar");

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "administrador/mantenedor_personal";
    }

    @RequestMapping("/eliminar_personal")
    public String eliminarPersonal(Model modelo, @RequestParam("idPersonal") BigInteger idPersonal) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        
        if (this.procedureQuery.DeletePersonalById(idPersonal)) {
            modelo.addAttribute("tipoRespuesta", "eliminar");
                modelo.addAttribute("respuesta", 1);
        }else{
            modelo.addAttribute("tipoRespuesta", "eliminar");
                modelo.addAttribute("respuesta", 0);
        }
        
        
        
        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        modelo.addAttribute("personales", personales);
        //desarrollo aca 

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantedor_personal";
//                
    }

    @PostMapping("/modificarPersonal")
    public String modificarPersonal(Model modelo, 
            @RequestParam("rutPersonal") String rutPersonal,
            @RequestParam("nombresPersonal") String nombresPersonal,
            @RequestParam("apePaternoPersonal") String apePaternoPersonal,
            @RequestParam("apeMaternoPersonal") String apeMaternoPersonal,
            @RequestParam("fechaNacimientoPersonal") Date fechaNacimientoPersonal,
            @RequestParam("celularPersonal") String celularPersonal,
            @RequestParam("correoPersonal") String correoPersonal,
            @RequestParam("estadoPersonal") Integer estadoPersonal
            ) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());

        Personal personal1 = this.personalService.findByRut(rutPersonal);

       
        personal1.setRutPersonal(rutPersonal);
        personal1.setNombresPersonal(nombresPersonal);
        personal1.setApePaternoPersonal(apePaternoPersonal);
        personal1.setApeMaternoPersonal(apeMaternoPersonal);
        personal1.setFechaNacimientoPersonal(fechaNacimientoPersonal);
        personal1.setCelularPersonal(celularPersonal);
        personal1.setCorreoPersonal(correoPersonal);
        
        if (estadoPersonal==1) {
            
        personal1.setEstadoPersonal(BigInteger.valueOf(1));
        
        }else  {
        
        personal1.setEstadoPersonal(BigInteger.valueOf(0));
        
        }
             
        this.personalService.updatePersonalDao(personal1);


        List<Personal> persosnales = new ArrayList<Personal>();
        persosnales = this.personalService.getAllUsuario();
        Personal personal2 = this.personalService.findByRut(rutPersonal.toString());
        
        
        
        modelo.addAttribute("personal", personal1);
        modelo.addAttribute("editPersonal",personal2);
        modelo.addAttribute("personales", persosnales);
        modelo.addAttribute("modificar", true);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_personal";
    }

    
    
        @RequestMapping("/buscar_por_filtro")
    public String buscar_por_filtro(Model modelo, @RequestParam("tipoBusqueda") String tipoBusqueda, @RequestParam("filtro") String filtro) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        List<Personal> personales = new ArrayList<Personal>();
        switch (tipoBusqueda) {
            case "nombre":
//                personals = this.proveedorService.filtrarProveedoresByNombre(filtro);
                break;
            case "rut":
                personales= this.personalService.filtrarPersonalByRut(filtro);
                break;
             default:
                personales = this.personalService.getAllUsuario();
        }
        
           modelo.addAttribute("personales", personales);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", personal);

        //
        //cargar el html nombre
        return "users/administrador/mantenedor_personal";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
