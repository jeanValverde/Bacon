/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.InsumoService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
    @Autowired
    InsumoService insumoService;
    @Autowired
    ProcedureQuery procedureQuery;
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

    @RequestMapping("/mantenedor_insumos")
    public String mantenedor_insumos(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

    @RequestMapping("/ingresar_insumo")
    public String ingresar_insumo(Model modelo, @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("unidadMedida") String unidadMedida,
            @RequestParam("stock") Integer stock,
            @RequestParam("stockMinimo") Integer stockMinimo,
            @RequestParam("stockMaximo") Integer stockMaximo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        this.procedureQuery.InsertInsumo(nombre, descripcion, stock, stockMinimo, stockMaximo, unidadMedida);
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("insumos", insumos);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }
    @RequestMapping("/modificar_insumo")
    public String modificar_insumo(Model modelo, @RequestParam("id") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("unidadMedida") String unidadMedida,
            @RequestParam("stock") Integer stock,
            @RequestParam("stockMinimo") Integer stockMinimo,
            @RequestParam("stockMaximo") Integer stockMaximo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        this.procedureQuery.UpdateInsumo(id,nombre, descripcion, stock, stockMinimo, stockMaximo, unidadMedida);
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("insumos", insumos);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }
    @RequestMapping("/eliminar_insumo")
    public String eliminar_insumo(Model modelo, @RequestParam("idInsumo") Integer idInsumo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        this.procedureQuery.DeleteInsumo(idInsumo);
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        modelo.addAttribute("insumos", insumos);
        //desarrollo aca 

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

    @RequestMapping("/cargar_insumo")
    public String cargar_insumo(Model modelo, @RequestParam("idInsumo") Integer idInsumo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        modelo.addAttribute("insumos", insumos);
        //desarrollo aca 
        Insumo insumo = this.insumoService.retornarInsumoById(idInsumo);
        modelo.addAttribute("modificar",true);
        modelo.addAttribute("insumo",insumo);
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
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
