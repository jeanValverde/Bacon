/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.RecetaService;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jean
 */
@RequestMapping("/administrador/recetaInsumos")
@Controller
public class PersonalMantenedorReceta {

    //Acceder a metodos CRUB y más de presonal 
    @Autowired
    PersonalService personalService;

    @Autowired
    RecetaService recetaService;

    @Autowired
    InsumoService insumoService;

    @RequestMapping("/filtroDisponibilidad")
    public String filtroDisponibilidad(Model modelo, @RequestParam("disponibilidad") Integer disponibilidad) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");

        modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByDisponibilidad(disponibilidad));

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }

    @RequestMapping("/filtroCategoria")
    public String filtroCategoria(Model modelo, @RequestParam("idCategoriaReceta") Integer idCategoriaReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");

        if (idCategoriaReceta == -1) {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByBar());
        } else {
            modelo.addAttribute("recetas", this.recetaService.filtrarRecetasByCategoriaCocina(idCategoriaReceta));
        }

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }

    @PostMapping("/editarReceta")
    public String editarReceta(Model modelo,
            @RequestParam("idReceta") Integer idReceta,
            @RequestParam("nombreReceta") String nombreReceta,
            @RequestParam("descripcionReceta") String descripcionReceta,
            @RequestParam("duracionReceta") Integer duracionReceta,
            @RequestParam("precioReceta") Integer precioReceta,
            @RequestParam("cantidadReceta") Integer cantidadReceta,
            @RequestParam("tipoReceta") String tipoReceta,
            @RequestParam("categoriaReceta") String categoriaReceta,
            @RequestParam("imagenReceta") MultipartFile[] file) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        receta.setNombreReceta(nombreReceta);
        receta.setDescripcionReceta(descripcionReceta);
        receta.setDuracionPreparacion(BigInteger.valueOf(duracionReceta));
        receta.setPrecioReceta(BigInteger.valueOf(precioReceta));
        receta.setCantidadPrepDiariaReceta(BigInteger.valueOf(cantidadReceta));
        receta.setTipoReceta(tipoReceta);
        CategoriaReceta categoria = new CategoriaReceta();
        categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(categoriaReceta)));
        receta.setIdCategoriaReceta(categoria);

        if (file[0].getOriginalFilename() != receta.getFoto()) {
            //falta borrar imagen
            String nombre = this.personalService.subirImagen(file);
            if (nombre == null) {
                nombre = "foto";
            } else {
                receta.setFoto(nombre);
            }
        }

        this.recetaService.update(receta);

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("recetas", this.recetaService.listar());

        modelo.addAttribute("tipoForm", "agregar");

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }

    @RequestMapping("/ingredientesReceta")
    public String ingredientesReceta(Model modelo, @RequestParam("idReceta") Integer idReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        
        //buscar los insumos para la receta 
        
        modelo.addAttribute("ingredientes", this.recetaService.listarIngredientesByIdReceta(idReceta));
        
        modelo.addAttribute("receta", receta);
        
        modelo.addAttribute("tipo", "misInsumos");

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/matenedor_receta_insumo";
    }

    @RequestMapping("/verInsumos")
    public String verInsumos(Model modelo, @RequestParam("idReceta") Integer idReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        modelo.addAttribute("insumos", this.recetaService.listarInsumosAgregarReceta(idReceta)); 

        modelo.addAttribute("tipo", "agregar");

        modelo.addAttribute("receta", receta);

        //buscar los insumos para la receta 
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/matenedor_receta_insumo";
    }

    @RequestMapping("/addRecetaInsumo")
    public String addRecetaInsumo(Model modelo,
            @RequestParam("idReceta") Integer idReceta,
            @RequestParam("idInsumo") Integer idInsumo,
            @RequestParam("cantidad") double cantidad) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        Insumo insumo = this.insumoService.retornarInsumoById(idInsumo);

        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setIdReceta(receta);
        ingrediente.setIdInsumo(insumo);
        ingrediente.setCantidad(cantidad);

        this.recetaService.addIngrediente(ingrediente);

        //cargar insumos
        modelo.addAttribute("ingredientes", this.recetaService.listarIngredientesByIdReceta(idReceta));

        modelo.addAttribute("tipo", "misInsumos");

        modelo.addAttribute("receta", receta);

        //buscar los insumos para la receta 
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/matenedor_receta_insumo";
    }

}