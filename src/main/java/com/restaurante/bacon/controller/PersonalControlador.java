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
        //desarrollo aca 

        modelo.addAttribute("personales", personales);
        modelo.addAttribute("agregar", true);

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
            @RequestParam("estadoPersonal") Integer estadoPersonal,
            @RequestParam("idRol") Integer idRol) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        String edad = this.personalService.getEdad(personal.getFechaNacimientoPersonal());

        Date fechaNacimiento = PersonalService.ParseFecha(fechaNacimientoPersonal);

        Personal personal1 = this.personalService.findByRut(rutPersonal);

        Personal perso = new Personal();

        perso.setRutPersonal(rutPersonal);
        perso.setNombresPersonal(nombresPersonal);
        perso.setApePaternoPersonal(apePaternoPersonal);
        perso.setApeMaternoPersonal(apeMaternoPersonal);
        perso.setFechaNacimientoPersonal(new Date());
        perso.setCelularPersonal(celularPersonal);
        perso.setCorreoPersonal(correoPersonal);
        String contra = encoder.encode(contrasenaPersonal);
        perso.setContrasenaPersonal(contra);
        perso.setEstadoPersonal(estadoPersonal);
        
        
        
        Rol rol = new Rol();
        rol.setIdRol(idRol);
        perso.setIdRol(rol);
//        personal1.setIdRol(rolpersonal);
        this.personalService.addPersonal(perso);
//        try {
//
//            
//            if (this.personalService.addPersonal(perso)) {
//                modelo.addAttribute("tipoRespuesta", "registroAgregar");
//                modelo.addAttribute("respuesta", 1);
//            } else {
//                modelo.addAttribute("tipoRespuesta", "registroAgregar");
//                modelo.addAttribute("respuesta", 0);
//            }
//            
            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
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
    public String cargar_personal(Model modelo, @RequestParam("idpersonal") BigDecimal idPersonal) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Personal> persosnales = new ArrayList<Personal>();
        persosnales = this.personalService.getAllUsuario();
        Personal personal1 = this.personalService.findByRut(idPersonal.toString());
        modelo.addAttribute("modificar", true);
        modelo.addAttribute("personal", personal1);
        modelo.addAttribute("personales", persosnales);
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_personal";
    }

    @RequestMapping("/eliminar_personal")
    public String eliminar_personal(Model modelo, @RequestParam("idPersonal") BigInteger idPersonal) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        if (this.procedureQuery.DeletePersonalById(idPersonal)) {
            modelo.addAttribute("tipoRespuesta", "eliminar");
            modelo.addAttribute("respuesta", 1);
        } else {
            modelo.addAttribute("tipoRespuesta", "eliminar");
            modelo.addAttribute("respuesta", 0);
        }

        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        modelo.addAttribute("proveedores", personales);
        //desarrollo aca 

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador";
//                + "/mantenedor_proveedor";
    }

    @RequestMapping("/modificar_personal")
    public String modificar_personal(Model modelo, @RequestParam("idPersonal") BigDecimal idPersonal,
            @RequestParam("rutPersonal") String rutPersonal,
            @RequestParam("nombresPersonal") String nombresPersonal,
            @RequestParam("apepaterno") String apepaterno,
            @RequestParam("apematerno") String apematerno,
            @RequestParam("fechaNacimiento") Date fechaNacimiento,
            @RequestParam("telefonoPersonal") String telefonoPersonal,
            @RequestParam("correopersonal") String correopersonal,
            @RequestParam("contraseña") String contraseña,
            @RequestParam("estadopersonal") Integer estadopersonal,
            @RequestParam("rolpersonal") Integer rolpersonal) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());

        Personal personal1 = new Personal();

        personal1.setIdPersonal(idPersonal);
        personal1.setRutPersonal(rutPersonal);
        personal1.setNombresPersonal(nombresPersonal);
        personal1.setApePaternoPersonal(apepaterno);
        personal1.setApeMaternoPersonal(apematerno);
        personal1.setFechaNacimientoPersonal(fechaNacimiento);
        personal1.setCelularPersonal(telefonoPersonal);
        personal1.setCorreoPersonal(correopersonal);
        personal1.setContrasenaPersonal(contraseña);
        personal1.setEstadoPersonal(estadopersonal);
        Rol rol = new Rol();
        rol.setIdRol(rolpersonal);
        personal.setIdRol(rol);

        if (this.personalService.ModificarPersonal(personal1)) {

            modelo.addAttribute("tipoRespuesta", "modificar");
            modelo.addAttribute("respuesta", 1);
        } else {
            modelo.addAttribute("tipoRespuesta", "modificar");
            modelo.addAttribute("respuesta", 0);
        }

        List<Personal> persosnales = new ArrayList<Personal>();
        persosnales = this.personalService.getAllUsuario();
        Personal personal2 = this.personalService.findByRut(idPersonal.toString());
        modelo.addAttribute("modificar", true);
        modelo.addAttribute("personal", personal2);
        modelo.addAttribute("personales", persosnales);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador";
    }

}
