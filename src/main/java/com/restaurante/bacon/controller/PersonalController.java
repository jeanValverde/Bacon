/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.RecetaService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.sound.midi.Patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jean
 *
 * No crear metodos propios en los controladores para eso esta el servicio
 *
 *
 */
@RequestMapping("/administrador")
@Controller
public class PersonalController {

    //acceder a CRUB y más del personal 
    @Autowired
    PersonalService personalService;

    @Autowired
    RecetaService recetaService;
    
    public static String UPLOAD_DIR_IMAGEN = System.getProperty("user.dir")+ "/src/main/resources/static/uploads";

    
    //para ingresar una contraseña encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/index")
    public String prueba(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        //desarrollo aca 
        modelo.addAttribute("funcionarios", this.personalService.getAllUsuario());

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }

    @RequestMapping("/receta")
    public String addReceta(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 
        
        

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");
        
        modelo.addAttribute("recetas", this.recetaService.listar());
        
        
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }
    
    
    @RequestMapping("/filtro")
    public String filtro(Model modelo, @RequestParam("nombreReceta") String nombreReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        
        List<Receta> recetas = this.recetaService.filtrarRecetasByNombre(nombreReceta);
        
        modelo.addAttribute("recetas", recetas );
        
        modelo.addAttribute("tipoForm", "agregar");
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }
    
     @RequestMapping("/deleteReceta")
    public String eliminarReceta(Model modelo, @RequestParam("idReceta") Integer idReceta ) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        this.recetaService.deleteRecetaById(idReceta); 
        
        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        
        List<Receta> recetas = this.recetaService.listar();
        
        modelo.addAttribute("recetas", recetas );
        
        modelo.addAttribute("tipoForm", "agregar");
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }
    
     @RequestMapping("/loadEditarReceta")
    public String cargarEditarReceta(Model modelo, @RequestParam("idReceta") Integer idReceta ) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        Receta receta = this.recetaService.buscarRecetaById(idReceta);
        
            
        List<Receta> recetas = this.recetaService.listar();
        
        modelo.addAttribute("recetas", recetas );
        
        modelo.addAttribute("recetaEdit", receta);
        
        modelo.addAttribute("tipoForm", "editar");
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }
    

    @PostMapping("/addReceta")
    public String updatePerfil(Model modelo,
            @RequestParam("nombreReceta") String nombreReceta,
            @RequestParam("descripcionReceta") String descripcionReceta,
            @RequestParam("duracionReceta") Integer duracionReceta,
            @RequestParam("precioReceta") Integer precioReceta,
            @RequestParam("cantidadReceta") Integer cantidadReceta,
            @RequestParam("tipoReceta") String tipoReceta,
            @RequestParam("categoriaReceta") String categoriaReceta, 
            @RequestParam("imagenReceta") MultipartFile[] file ) throws IOException {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Receta receta = new Receta();
        
        receta.setNombreReceta(nombreReceta);
        receta.setDescripcionReceta(descripcionReceta);
        receta.setDuracionPreparacion(BigInteger.valueOf(duracionReceta));  
        receta.setDisponibilidadReceta(BigInteger.valueOf(1)); 
        receta.setPrecioReceta(BigInteger.valueOf(precioReceta));  
        receta.setCantidadPrepDiariaReceta(BigInteger.valueOf(cantidadReceta)); 
        
        receta.setTipoReceta(tipoReceta); 
        CategoriaReceta categoria = new CategoriaReceta();
        categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(categoriaReceta)));  
        receta.setIdCategoriaReceta(categoria);
        
        String nombre = this.personalService.subirImagen(file);
        
        if(nombre == null){
            nombre = "foto";
        }else{
             receta.setFoto(nombre);
        }
                
        this.recetaService.add(receta);
      
        
        //desarollo
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion",personal);
        //
        //cargar el html *nombre*
        return "perfil";
    }

    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL **CAMBIAR A PORST**
    @RequestMapping("/add")
    public String add(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarollo 
        Personal person = new Personal();

        //person.setIdPersonal(BigDecimal.valueOf(5));
        person.setRutPersonal("17.347.762-6");
        person.setNombresPersonal("Diego Alejandro");
        person.setApePaternoPersonal("León");
        person.setApeMaternoPersonal("Plaza");
        person.setFechaNacimientoPersonal(new Date());
        person.setCelularPersonal("954714587");
        person.setCorreoPersonal("diego.leon@gmail.com");
        person.setContrasenaPersonal(encoder.encode("123"));
        person.setEstadoPersonal(BigInteger.valueOf(1));

        Rol rol = new Rol();
        rol.setIdRol(BigDecimal.valueOf(2));

        person.setIdRol(rol);

        this.personalService.addPersonal(person);

        //fin desarrollo 
        //despacho 
        //fin despacacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }

}
