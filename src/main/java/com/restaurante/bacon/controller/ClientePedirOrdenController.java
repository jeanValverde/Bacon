/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.service.RecetaService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente/pedirOrden")
@Controller
public class ClientePedirOrdenController {
    
    @Autowired
    RecetaService recetaService;
    
    @RequestMapping("/")
    public String index(Model modelo, HttpSession sesion, @RequestParam("tipo") Integer idCategoriaReceta){
        
        modelo.addAttribute("cliente", sesion.getAttribute("sesionCliente"));
        
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipo", idCategoriaReceta );

                       
        if (idCategoriaReceta == -1) {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByBar());
        } else {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByCategoriaCocina(idCategoriaReceta));
        }
       
        
        return "users/cliente/index";
    }
    
}
