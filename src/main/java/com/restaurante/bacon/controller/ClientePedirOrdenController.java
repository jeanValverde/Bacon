/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrden;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.RecetaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente/pedirOrden")
@Controller
public class ClientePedirOrdenController {
    
    @Autowired
    RecetaService recetaService;
    
    @Autowired
    InsumoService insumoService;
    
    @RequestMapping("/")
    public String index(Model modelo, HttpSession sesion, @RequestParam("tipo") Integer idCategoriaReceta) {
        
        modelo.addAttribute("cliente", sesion.getAttribute("sesionCliente"));
        
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());
        
        modelo.addAttribute("tipo", idCategoriaReceta);
        
        modelo.addAttribute("orden", sesion.getAttribute("orden"));
        
        if (idCategoriaReceta == -1) {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByBar());
        } else {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByCategoriaCocina(idCategoriaReceta));
        }
        
        return "users/cliente/index";
    }
    
    @RequestMapping("/detalleReceta")
    public ResponseEntity detalle(Model modelo, HttpSession sesion, @RequestParam("idReceta") Integer idReceta) {
        
        List<Ingrediente> ingredientes = this.recetaService.listarIngredientesByIdReceta(idReceta);
        
        return new ResponseEntity(ingredientes, HttpStatus.OK);
    }
    
    @RequestMapping("/agregarRecetaOrden")
    public ModelAndView cargarRecetaOrden(Model modelo, HttpSession sesion, @RequestParam("idReceta") Integer idReceta, @RequestParam("cantidad") Integer cantidad) {
        
        Receta receta = this.recetaService.buscarRecetaById(idReceta);
        
        Map<Receta, Integer> recetasCocina = (Map<Receta, Integer>) sesion.getAttribute("ordenesCocina");
        Map<Receta, Integer> recetasBar = (Map<Receta, Integer>) sesion.getAttribute("ordenesBar");
        
        Integer tipo = Integer.parseInt(receta.getTipoReceta());

        //cocina = 0 ; bar = 1
        if (tipo == 0) {
            recetasCocina.put(receta, cantidad);
        } else {
            recetasBar.put(receta, cantidad);
        }
        
        sesion.setAttribute("orden", true);
        
        sesion.setAttribute("ordenesCocina", recetasCocina);
        
        sesion.setAttribute("ordenesBar", recetasBar);
        
        return new ModelAndView("redirect:/cliente/pedirOrden/?tipo=2");
    }
    
    @RequestMapping("/finalizar")
    public ResponseEntity finalizar(Model modelo, HttpSession sesion, @RequestParam("tipo") Integer tipo) {
        
        Map<Receta, Integer> recetasCocina = (Map<Receta, Integer>) sesion.getAttribute("ordenesCocina");
        Map<Receta, Integer> recetasBar = (Map<Receta, Integer>) sesion.getAttribute("ordenesBar");
        
        List<RecetaOrden> recetasPedidas = new ArrayList<RecetaOrden>();

        //cocina = 0 ; bar = 1
        if (tipo == 0) {
            for (Map.Entry<Receta, Integer> ordenes : recetasCocina.entrySet()) {
                
                RecetaOrden recetaOrden = new RecetaOrden();
                
                recetaOrden.setCantidad(ordenes.getValue());
                
                Receta receta = new Receta();
                
                receta.setIdReceta(ordenes.getKey().getIdReceta());
                receta.setNombreReceta(ordenes.getKey().getNombreReceta());
                receta.setFoto(ordenes.getKey().getFoto());
                receta.setPrecioReceta(ordenes.getKey().getPrecioReceta());
                receta.setCantidadPrepDiariaReceta(ordenes.getKey().getCantidadPrepDiariaReceta());
                CategoriaReceta categoriaReceta = new CategoriaReceta();
                
                categoriaReceta.setDescripcionCategoriaReceta(ordenes.getKey().getIdCategoriaReceta().getDescripcionCategoriaReceta());
                
                receta.setIdCategoriaReceta(categoriaReceta);
                
                recetaOrden.setReceta(receta);
                recetaOrden.setTipo(tipo); 
                
                Integer precio = ordenes.getValue() * Integer.parseInt(ordenes.getKey().getPrecioReceta().toString());
                recetaOrden.setPrecioTotal(precio);
                recetasPedidas.add(recetaOrden);
            }
        } else {
            for (Map.Entry<Receta, Integer> ordenes : recetasBar.entrySet()) {
                
                RecetaOrden recetaOrden = new RecetaOrden();
                
                recetaOrden.setCantidad(ordenes.getValue());
                
                Receta receta = new Receta();
                
                receta.setIdReceta(ordenes.getKey().getIdReceta());
                receta.setNombreReceta(ordenes.getKey().getNombreReceta());
                receta.setFoto(ordenes.getKey().getFoto());
                receta.setPrecioReceta(ordenes.getKey().getPrecioReceta());
                receta.setCantidadPrepDiariaReceta(ordenes.getKey().getCantidadPrepDiariaReceta());
                CategoriaReceta categoriaReceta = new CategoriaReceta();
                
                categoriaReceta.setDescripcionCategoriaReceta(ordenes.getKey().getIdCategoriaReceta().getDescripcionCategoriaReceta());
                
                receta.setIdCategoriaReceta(categoriaReceta);
                
                recetaOrden.setReceta(receta);
                recetaOrden.setTipo(tipo); 
                
                Integer precio = ordenes.getValue() * Integer.parseInt(ordenes.getKey().getPrecioReceta().toString());
                recetaOrden.setPrecioTotal(precio);
                recetasPedidas.add(recetaOrden);
            }
        }
        
        return new ResponseEntity(recetasPedidas, HttpStatus.OK);
    }
    
}
