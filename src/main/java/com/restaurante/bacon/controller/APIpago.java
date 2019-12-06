/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.service.ClienteService;
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
@RequestMapping("/API")
@Controller
public class APIpago {
     @Autowired
    ClienteService clienteService;
    
    @PostMapping("/pago")
    public ResponseEntity pagarOrdenes(Model modelo, @RequestParam("order_id") Integer order_id, @RequestParam("transaction_id") Integer transaction_id, 
            @RequestParam("status") Integer status, @RequestParam("verification_key") String verification_key) {
        
        System.out.println(order_id);
        System.out.println(transaction_id);
        System.out.println(status);
        System.out.println(verification_key);
        
        if(status == 1){
             Integer result = this.clienteService.pagarOnline(order_id);
        }
        return new ResponseEntity(true, HttpStatus.OK);
    }
    
    @RequestMapping("/prueba")
    public ResponseEntity prueba(Model modelo, @RequestParam("order_id") Integer order_id, @RequestParam("transaction_id") Integer transaction_id, 
            @RequestParam("status") Integer status, @RequestParam("verification_key") String verification_key) {
        
        if(status == 1){
             Integer result = this.clienteService.pagarOnline(order_id);
        }
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
