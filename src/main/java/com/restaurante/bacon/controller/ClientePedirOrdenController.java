/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.EstadoOrden;
import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrden;
import com.restaurante.bacon.dto.RecetaOrdenada;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.OrdenCocinaService;
import com.restaurante.bacon.service.RecetaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    @Autowired
    OrdenCocinaService ordenCocinaSerice;

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

    @RequestMapping("/editarCantidadSesion")
    public ResponseEntity cambiarCantidadRecetaSesion(Model modelo, HttpSession sesion, @RequestParam("idReceta") Integer idReceta, @RequestParam("cantidad") Integer cantidad) {

        Map<Receta, Integer> recetasCocina = (Map<Receta, Integer>) sesion.getAttribute("ordenesCocina");
        Map<Receta, Integer> recetasBar = (Map<Receta, Integer>) sesion.getAttribute("ordenesBar");

        boolean exito = false;

        for (Map.Entry<Receta, Integer> ordenes : recetasCocina.entrySet()) {

            if (ordenes.getKey().getIdReceta() == idReceta) {
                ordenes.setValue(cantidad);
                exito = true;
            }
        }

        for (Map.Entry<Receta, Integer> ordenes : recetasBar.entrySet()) {

            if (ordenes.getKey().getIdReceta() == idReceta) {
                ordenes.setValue(cantidad);
                exito = true;
            }
        }

        return new ResponseEntity(exito, HttpStatus.OK);
    }

    @RequestMapping("/eliminarReceraSesion")
    public ResponseEntity eliminarRecetaSesion(Model modelo, HttpSession sesion, @RequestParam("idReceta") Integer idReceta) {

        Map<Receta, Integer> recetasCocina = (Map<Receta, Integer>) sesion.getAttribute("ordenesCocina");
        Map<Receta, Integer> recetasBar = (Map<Receta, Integer>) sesion.getAttribute("ordenesBar");

        boolean exito = false;

        Map<Receta, Integer> recetasCocinaNuevo = new HashMap();
        Map<Receta, Integer> recetasBarNuevo = new HashMap();

        for (Map.Entry<Receta, Integer> ordenes : recetasCocina.entrySet()) {

            if (ordenes.getKey().getIdReceta() == idReceta) {
                exito = true;
            } else {
                recetasCocinaNuevo.put(ordenes.getKey(), ordenes.getValue());
            }
        }

        for (Map.Entry<Receta, Integer> ordenes : recetasBar.entrySet()) {
            if (ordenes.getKey().getIdReceta() == idReceta) {
                exito = true;
            } else {
                recetasBarNuevo.put(ordenes.getKey(), ordenes.getValue());
            }
        }

        sesion.setAttribute("ordenesCocina", recetasCocinaNuevo);

        sesion.setAttribute("ordenesBar", recetasBarNuevo);

        return new ResponseEntity(exito, HttpStatus.OK);
    }

    @RequestMapping("/confirmarOrden")
    public ModelAndView confirmar(Model model, HttpSession sesion,  @RequestParam("descripcion") String descripcion) {

        
        Map<Receta, Integer> recetasCocinaPedias = (Map<Receta, Integer>) sesion.getAttribute("ordenesCocina");
        Map<Receta, Integer> recetasBarPedidas = (Map<Receta, Integer>) sesion.getAttribute("ordenesBar");

        Orden ordenCocina = new Orden();
        Orden ordenBar = new Orden();
        
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(new BigDecimal("25")); 
        
        Short tipo = Short.valueOf("0");//0 cocina
        
        if(descripcion == null || descripcion == ""){
            descripcion = "Sin descripción";
        }
        
        ordenCocina = this.ordenCocinaSerice.crearOrdenByTipo(recetasCocinaPedias, tipo , cliente, descripcion);
        
        List<RecetaOrdenada> recetasCocina = this.ordenCocinaSerice.calcularAndCrearRecetasOrdenadas(recetasCocinaPedias, ordenCocina);
        
        tipo = Short.valueOf("1");//0 cocina
        ordenBar = this.ordenCocinaSerice.crearOrdenByTipo(recetasBarPedidas, tipo , cliente, descripcion);
        
        List<RecetaOrdenada> recetasBar = this.ordenCocinaSerice.calcularAndCrearRecetasOrdenadas(recetasBarPedidas, ordenBar);
        
        Map<Receta, Integer> recetasCocinaOtra = new HashMap();
        Map<Receta, Integer> recetasBarOtra = new HashMap();

        sesion.setAttribute("ordenesCocina", recetasCocinaOtra);

        sesion.setAttribute("orden", false);

        sesion.setAttribute("ordenesBar", recetasBarOtra);

        return new ModelAndView("redirect:/cliente/pedirOrden/?tipo=2");
    }
    
    
    public Integer calcularTotal(Map<Receta, Integer> busqueda){
        
        Integer salida = 0;
        
        for (Map.Entry<Receta, Integer> ordenes : busqueda.entrySet()) {

           salida = salida + Integer.parseInt(ordenes.getKey().getPrecioReceta().toString()) * ordenes.getValue(); 
           
        }
        
        return salida;
    }

}
