/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Mesa;
import com.restaurante.bacon.service.ClienteService;
import com.restaurante.bacon.service.MesaService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente/sesion")
@Controller
public class ClienteSessionController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    MesaService mesaService;

    @RequestMapping("/detalle")
    public ResponseEntity detalle(Model modelo, HttpSession sesion) {

        Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        
        Cliente clienteSesion = this.clienteService.findClienteById(cliente.getIdCliente());
        
        Cliente d = new Cliente();
        d.setNombre(clienteSesion.getNombre()); 
        d.setFechaIngreso(clienteSesion.getFechaIngreso()); 
        d.setIdCliente(clienteSesion.getIdCliente()); 
        Mesa mesa = new Mesa();
        mesa.setIdMesa(clienteSesion.getIdMesa().getIdMesa()); 
        mesa.setNumeroMesa(clienteSesion.getIdMesa().getNumeroMesa()); 
        d.setIdMesa(mesa);
        
        return new ResponseEntity(d, HttpStatus.OK);
    }

}
