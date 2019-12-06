/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrdenada;
import com.restaurante.bacon.dto.RecetaOrdenadaByOrden;
import com.restaurante.bacon.service.ClienteService;
import com.restaurante.bacon.service.PagarOrdenService;
import com.restaurante.bacon.service.RecetaOrdenadaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente/pagarOnline")
@Controller
public class PagarOnlineController {
    
    @Autowired
    PagarOrdenService pagarOrdenService;
    
    @Autowired
    RecetaOrdenadaService recetaService;
    
    @Autowired
    ClienteService clienteService;
    
    @PostMapping("/")
    public ResponseEntity pagarOrdenes(Model modelo, @RequestParam("order_id") Integer order_id, @RequestParam("transaction_id") Integer transaction_id, 
            @RequestParam("status") Integer status, @RequestParam("verification_key") String verification_key) {
        
        System.out.println(order_id);
        System.out.println(transaction_id);
        System.out.println(status);
        System.out.println(verification_key);
        
        if(status == 1){
             Integer result = this.clienteService.pagarOnline(order_id);
        }
        return new ResponseEntity("", HttpStatus.OK);
    }
    
    @RequestMapping("/exito")
    public String exitoPago(Model modelo, HttpSession sesion, Integer idOrden) {
        //sesion 
        Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        List<Orden> ordenes = new ArrayList<Orden>();
        ordenes = this.pagarOrdenService.listarOrdenes((BigDecimal.valueOf(cliente.getIdCliente())));

        Integer totalOrden;
        totalOrden = this.pagarOrdenService.retornarPago(cliente.getIdCliente());
        modelo.addAttribute("precioTotal", totalOrden);

        List<Receta> recetas = new ArrayList<Receta>();

        List<RecetaOrdenada> recetaOrdenada = new ArrayList<RecetaOrdenada>();
        //recetaOrdenada = this.recetaService.listarRecetasDeOrdenes(3);

        List<RecetaOrdenadaByOrden> recetaByOrden = new ArrayList<RecetaOrdenadaByOrden>();

        for (Orden orden : ordenes) {
            RecetaOrdenadaByOrden recetasByOrden = new RecetaOrdenadaByOrden();

            recetasByOrden.setOrden(orden);

            List<RecetaOrdenada> recetasOrdenadas = new ArrayList<RecetaOrdenada>();

            recetasOrdenadas = this.recetaService.listarRecetasDeOrdenes(orden.getIdOrden());

            recetasByOrden.setRecetaOrdenada(recetasOrdenadas);

            recetaByOrden.add(recetasByOrden);
        }
        //modelo.addAttribute("ordenes", ordenes);
        modelo.addAttribute("recetaByOrden", recetaByOrden);

        //fin desarrollo 
        return "users/cliente/pagoExito";
    }
    
     
}
