/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.aws.s3.AmazonClient;
import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.DatosProcecureCredenciales;
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
import java.util.ArrayList;
import java.util.List;
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
    
    private AmazonClient amazonClient;

    @Autowired
    DatosProcecureCredenciales datosProcecureCredenciales;
     
    @Autowired
    PersonalMantenedorReceta(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @RequestMapping("/filtroDisponibilidad")
    public String filtroDisponibilidad(Model modelo, @RequestParam("disponibilidad") Integer disponibilidad) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");

        
        boolean resultado = false;
        List<Receta> filtro = this.recetaService.filtrarRecetasByDisponibilidad(disponibilidad);
        if(filtro.size()>= 1){
            resultado = true;
        }
        modelo.addAttribute("recetas", filtro);
        modelo.addAttribute("resultados", resultado);
        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 0);
        //fin mensajes 

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
        boolean resultado = false;
        List<Receta> filtro = new ArrayList<Receta>();
        if (idCategoriaReceta == -1) {
            filtro = this.recetaService.filtrarRecetasByBar();
            if(filtro.size() > 0){
                resultado = true;
            }
            
        } else {
            
            filtro = this.recetaService.filtrarRecetasByCategoriaCocina(idCategoriaReceta);
            if(filtro.size() > 0){
                resultado = true;
            }
        }
        modelo.addAttribute("recetas", filtro );
        modelo.addAttribute("resultados", resultado);
        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 0);
        //fin mensajes 

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
        Integer cate = Integer.parseInt(categoriaReceta);
        
        if ( cate != 0 ) {
            CategoriaReceta categoria = new CategoriaReceta();
            categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(categoriaReceta)));
            receta.setIdCategoriaReceta(categoria);
        }

        if (!file[0].isEmpty()) {
            if (file[0].getOriginalFilename() != receta.getFoto()) {
                //falta borrar imagen
                String nombre = this.personalService.subirImagen(file);
                if (nombre == null) {
                    nombre = "foto";
                } else {
                    receta.setFoto(nombre);
                    this.amazonClient.uploadFile(file[0], nombre);
                }
            }
        }

        this.recetaService.update(receta);

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("recetas", this.recetaService.listar());
        modelo.addAttribute("resultados", true);
        modelo.addAttribute("tipoForm", "agregar");

        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 1);
        modelo.addAttribute("nombreMensaje", "Información");
        modelo.addAttribute("mensaje", "Receta Editada Correctamente");
        //puede ser success - info - danger - warning
        modelo.addAttribute("tipoMensaje", "success");
        //fin mensajes 

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
        List<Ingrediente> ingredientes = this.recetaService.listarIngredientesByIdReceta(idReceta);
        modelo.addAttribute("ingredientes", ingredientes);
        boolean res = false;
        if(ingredientes.size() >= 1){
            res = true;
        }
        modelo.addAttribute("receta", receta);
        modelo.addAttribute("vistaAgrega", res);
        modelo.addAttribute("resultados", true);
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
        List<Insumo> insumos = this.recetaService.listarInsumosAgregarReceta(idReceta);
        modelo.addAttribute("insumos", insumos);
        boolean res = false;
        modelo.addAttribute("tipo", "agregar");
        if(insumos.size() >= 1){
            res = true;
        }
        modelo.addAttribute("receta", receta);
        modelo.addAttribute("vistaAgrega", true);
        modelo.addAttribute("resultados", res);
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
        List<Ingrediente> ingredientes = this.recetaService.listarIngredientesByIdReceta(idReceta);
        //cargar insumos
        modelo.addAttribute("ingredientes", ingredientes );
        boolean res = false;
        if(ingredientes.size() >= 1 ){
            res = true;
        }
        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("vistaAgrega", true);
        modelo.addAttribute("resultados", res);
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

    @RequestMapping("/insumoName")
    public String buscarInsumos(Model modelo, @RequestParam("idReceta") Integer idReceta, @RequestParam("nombre") String nombre) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);
        List<Insumo> insumos = this.insumoService.filtrarInsumosByNombre(nombre);
        modelo.addAttribute("insumos", insumos);
        boolean res = false;
        if(insumos.size() >= 1){
            res = true;
        }
        modelo.addAttribute("tipo", "agregar");
        modelo.addAttribute("vistaAgrega", true);
        modelo.addAttribute("resultados", res);
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
    
    //editarIngrediente
    @RequestMapping("/editarIngrediente")
    public String editarIngrediente(Model modelo,
            @RequestParam("idReceta") Integer idReceta,
            @RequestParam("idIngrediente") Integer idIngrediente,
            @RequestParam("cantidad") double cantidad, 
            @RequestParam("idInsumo") Integer idInsumo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        Ingrediente ingrediente = new Ingrediente();
        
        ingrediente.setIdIngrediente(idIngrediente); 
        ingrediente.setCantidad(cantidad); 
        ingrediente.setIdReceta(receta); 
        
        Insumo insumo = this.insumoService.retornarInsumoById(idInsumo);
        
        ingrediente.setIdInsumo(insumo);

        this.recetaService.editarIngrediente(ingrediente);
        List<Ingrediente> ingredientes = this.recetaService.listarIngredientesByIdReceta(idReceta);
        //cargar insumos
        modelo.addAttribute("ingredientes", ingredientes);
        boolean res = false;
        if(ingredientes.size() >= 1 ){
            res = true;
        }
        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("vistaAgrega", true);
        modelo.addAttribute("resultados", res);
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
    
    
     @RequestMapping("/deleteIngrediente")
    public String deleteIngrediente(Model modelo, @RequestParam("idReceta") Integer idReceta, 
            @RequestParam("idIngrediente") Integer idIngrediente) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        //borrar ingrediente
        
        this.recetaService.deleteIngrediente(idIngrediente); 
        List<Ingrediente> ingredientes = this.recetaService.listarIngredientesByIdReceta(idReceta);
        //buscar los insumos para la receta 
        modelo.addAttribute("ingredientes", ingredientes);
        boolean res = false;
        if(ingredientes.size() >= 1){
            res = true;
        }
        modelo.addAttribute("receta", receta);
        modelo.addAttribute("vistaAgrega", res);
        modelo.addAttribute("resultados", true);
        modelo.addAttribute("tipo", "misInsumos");

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/matenedor_receta_insumo";
    }
    
     @RequestMapping("/Recetafiltro")
    public String filtro(Model modelo, @RequestParam("nombreReceta") String nombreReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        List<Receta> recetas = this.recetaService.filtrarRecetasByNombre(nombreReceta);

        boolean resultado = false;
        if(recetas.size()>= 1){
            resultado = true;
        }
        modelo.addAttribute("resultados", resultado);
        
        modelo.addAttribute("recetas", recetas);

        modelo.addAttribute("tipoForm", "agregar");
        
        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 0 );
        //fin mensajes 
        
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }
    
}
