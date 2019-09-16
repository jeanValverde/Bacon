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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
            @RequestParam("estadoMesa") Integer estadoMesa) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Mesa mesa = new Mesa();

        mesa.setNumeroMesa(BigInteger.valueOf(numeroMesa));
        mesa.setCantidadAsientosMesa(BigInteger.valueOf(cantidadAsientosMesa));
        mesa.setEstadoMesa(BigInteger.valueOf(estadoMesa));

        if (this.mesaService.add(mesa) != null) {
            model.addAttribute("registroAgregar", 1);
        } else {
            model.addAttribute("registroAgregar", 0);
        }

        List<Mesa> mesas = new ArrayList<Mesa>();
        mesas = this.mesaService.listarMesa();

        model.addAttribute("personalSesion", personal);
        model.addAttribute("agregarMesa", true);
        model.addAttribute("mesas", mesas);

        return "users/administrador/mantenedorMesa";

    }

    @PostMapping("/modificar")
    public String modificarMesa(Model model,
            @RequestParam("idMesa") Integer idMesa,
            @RequestParam("numeroMesa") BigInteger numeroMesa,
            @RequestParam("cantidadAsientosMesa") BigInteger cantidadAsientosMesa,
            @RequestParam("estadoMesa") BigInteger estadoMesa) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Mesa mesa = new Mesa();

        mesa.setIdMesa(idMesa);
        mesa.setNumeroMesa(numeroMesa);
        mesa.setCantidadAsientosMesa(cantidadAsientosMesa);
        mesa.setEstadoMesa(estadoMesa);

        this.mesaService.editarMesa(mesa);

        List<Mesa> mesas = new ArrayList<Mesa>();
        mesas = this.mesaService.listarMesa();

        model.addAttribute("personalSesion", personal);
        model.addAttribute("agregarMesa", true);
        model.addAttribute("mesas", mesas);

        return "users/administrador/mantenedorMesa";

    }

    @RequestMapping("/eliminar")
    public String eliminarMesa(Model model,
            @RequestParam("idMesa") Integer idMesa) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        this.mesaService.eliminarMesa(idMesa);

        List<Mesa> mesas = new ArrayList<Mesa>();
        mesas = this.mesaService.listarMesa();

        model.addAttribute("personalSesion", personal);
        model.addAttribute("agregarMesa", true);
        model.addAttribute("mesas", mesas);

        return "users/administrador/mantenedorMesa";

    }

    //funciona cuando apretar el boton editar de las tarjetas
    @RequestMapping("/cargar_mesa")
    public String cargar_mesa(Model model, @RequestParam("idMesa") Integer idMesa) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Mesa> mesas = new ArrayList<Mesa>();
        mesas = this.mesaService.listarMesa();
        model.addAttribute("mesas", mesas);
        //desarrollo aca 
        Mesa mesa = this.mesaService.retornarMesaById(idMesa);
        model.addAttribute("modificarMesa", true);
        model.addAttribute("mesa", mesa);
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        model.addAttribute("personalSesion", personal);
        //
        return "users/administrador/mantenedorMesa";
    }

    @RequestMapping("/buscar_por_filtro")
    public String buscar_por_filtro(Model model, @RequestParam("tipoBusqueda") String tipoBusqueda, @RequestParam("filtro") String filtro) {
        //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        List<Mesa> mesas = new ArrayList<Mesa>();
        switch (tipoBusqueda) {
            case "numero":
                mesas = this.mesaService.filtrarRecetaByNumero(BigInteger.valueOf(Integer.parseInt(filtro)));
                break;
            case "asientos":
                mesas = this.mesaService.filtrarRecetaByAsientos(BigInteger.valueOf(Integer.parseInt(filtro)));
                break;
            case "habilitada":
                mesas = this.mesaService.filtrarRecetaByEstado(BigInteger.valueOf(1));
                break;
            case "deshabilitada":
                mesas = this.mesaService.filtrarRecetaByEstado(BigInteger.valueOf(2));
                break;

            //    mesas = this.mesaService.filtrarRecetaByEstado(BigInteger.valueOf(Integer.parseInt(filtro)));
            default:
                mesas = this.mesaService.listarMesa();
        }

        //desarrollo aca
        model.addAttribute("mesas", mesas);
        model.addAttribute("agregarMesa", true);

        model.addAttribute("personalSesion", personal);
        return "users/administrador/mantenedorMesa";
    }

    //Primer controller que se ejecuta desde el sidebar
    @RequestMapping("/mesas")
    public String mesas(Model model) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Mesa> mesas = new ArrayList<Mesa>();
        mesas = this.mesaService.listarMesa();

        //this.mesaService.add(mesa);
        model.addAttribute("agregarMesa", true);
        model.addAttribute("mesas", mesas);
        model.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));

        //Retornar a la pagina
        return "users/administrador/mantenedorMesa";

    }

}
