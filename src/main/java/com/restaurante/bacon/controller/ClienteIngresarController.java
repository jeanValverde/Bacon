/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Mesa;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.service.ClienteService;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente")
@Controller
public class ClienteIngresarController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/ingresar")
    public ModelAndView prueba(Model modelo, HttpSession sesion,
            @RequestParam("nombre") String nombre,
            @RequestParam("idMesa") Integer idMesa,
            @RequestParam("numeroMesa") Integer numeroMesa) {

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);

        Mesa mesa = new Mesa();

        mesa.setIdMesa(idMesa);
        mesa.setNumeroMesa(BigInteger.valueOf(numeroMesa));

        this.clienteService.add(cliente);

        //aca se registra
        sesion.setAttribute("sesionCliente", "ic");

        //esto es para las ordenes NO TOCAR PORFIS 
        Map<Receta, Integer> recetasCocina = new HashMap();
        Map<Receta, Integer> recetasBar = new HashMap();

        sesion.setAttribute("ordenesCocina", recetasCocina);

        sesion.setAttribute("orden", false);

        sesion.setAttribute("ordenesBar", recetasBar);

        //hasta aca 
        //
        modelo.addAttribute("agregarCliente", true);
        return new ModelAndView("redirect:/cliente/pedirOrden/?tipo=2");
    }

}
