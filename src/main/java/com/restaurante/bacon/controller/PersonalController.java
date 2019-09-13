/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.ControlCaja;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        modelo.addAttribute("Personal", this.personalService.getAllUsuario());
        //fin desarrollo 
        //despachos 
        
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/index";
    }

<<<<<<< HEAD
    @RequestMapping("/mantenedor_personal")
    public String mantenedor_personal(Model modelo) {
=======
    @RequestMapping("/receta")
    public String receta(Model modelo) {
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

        modelo.addAttribute("recetas", recetas);

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
    public String deleteReceta(Model modelo, @RequestParam("idReceta") Integer idReceta) {
>>>>>>> Felipe
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarollo 
        modelo.addAttribute("Personal", personal);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("PersonalSesion", personal);

<<<<<<< HEAD
        modelo.addAttribute("PersonalSesion", this.personalService.getPersonalSesion(user.getUsername()));

        return "users/administrador/mantenedor_personal";
=======
        receta.setTipoReceta(tipoReceta);
        CategoriaReceta categoria = new CategoriaReceta();
        categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(categoriaReceta)));
        receta.setIdCategoriaReceta(categoria);

        String nombre = this.personalService.subirImagen(file);

        if (nombre == null) {
            nombre = "foto";
        } else {
            receta.setFoto(nombre);
        }

        this.recetaService.add(receta);

        //desarollo
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", personal);
        //
        //cargar el html nombre
        return "perfil";


>>>>>>> Felipe
    }

    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL **CAMBIAR A PORST**
    @RequestMapping("/addPersonal")
    public String addPersonal(Model modelo,
            @RequestParam("rutPersonal") String rutPersonal,
            @RequestParam("nombresPersonal") String nombresPersonal,
            @RequestParam("apePaternoPersonal") String apePaternoPersonal,
            @RequestParam("apeaternoPersonal") String apeMaternoPersonal,
            @RequestParam("fechaNacimientoPersonal") Date fechaNacimientoPersonal,
            @RequestParam("celularPersonal") String celularPersonal,
            @RequestParam("correoPersonal") String correoPersonal,
            @RequestParam("contrasenaPersonal") String contrasenaPersonal,
            @RequestParam("estadoPersonal") BigInteger estadoPersonal,
            @RequestParam("controlCajaCollection") Collection<ControlCaja> controlCajaCollection,
            @RequestParam("idRol") Rol idRol) {

//            this.PersonalService.addPersonal(personal);
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        modelo.addAttribute("PersonalSesion", personal);

        List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        //desarollo 
        modelo.addAttribute("Personal", personal);
        modelo.addAttribute("agregar", true);
<<<<<<< HEAD

        return "users/administrador/mantenedor_personal";
    }

     @RequestMapping("/eliminar_personal")
     public String eliminar_personal(Model modelo, @RequestParam("idPersonal")BigDecimal idPersonal ){
     //sesion
=======
        modelo.addAttribute("insumos", insumos);
        //fin desarrollo 
        //despachos 
        return "users/administrador/mantenedor_insumos";

    }



//    @RequestMapping("/ingresar_insumo")
//    public String ingresar_insumo(Model modelo, @RequestParam("nombre") String nombre,
//            @RequestParam("descripcion") String descripcion,
//            @RequestParam("unidadMedida") String unidadMedida,
//            @RequestParam("stock") Integer stock,
//            @RequestParam("stockMinimo") Integer stockMinimo,
//            @RequestParam("stockMaximo") Integer stockMaximo,
//            @RequestParam("imagenInsumo") MultipartFile[] file) {
//        //sesion 
//        UserRol user = new UserRol();
//        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
//        //sesion 
//        String nombreImagen = this.personalService.subirImagen(file);
//        Insumo insumo = new Insumo();
//        if (nombreImagen == null) {
//            nombreImagen = "260x162.png";
//        } else {
//            
//            insumo.setFotoInsumo(nombreImagen);
//        }
//        insumo.setNombreInsumo(nombre);
//        insumo.setDescripcionInsumo(descripcion);
//        insumo.setStockInsumo(stock);
//        insumo.setMinimoStockInsumo(stockMinimo);
//        insumo.setMaximoStockInsumo(stockMaximo);
//        insumo.setUnidadMedidaInsumo(unidadMedida);
//        
//        if(this.insumoService.ingresarInsumo(insumo)){
//            
//        }else{
//            
//        }
//        
//        List<Insumo> insumos = new ArrayList<Insumo>();
//        insumos = this.insumoService.listarInsumos();
//        //desarrollo aca 
//        modelo.addAttribute("agregar", true);
//        modelo.addAttribute("insumos", insumos);
//        //fin desarrollo 
//        //despachos 
//
//        //fin despacho 
//        //siempre despachar esto por la sesion 
//        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
//        //
//        return "users/administrador/mantenedor_insumos";
//    }

//    @RequestMapping("/modificar_insumo")
//    public String modificar_insumo(Model modelo, @RequestParam("id") Integer id,
//            @RequestParam("nombre") String nombre,
//            @RequestParam("descripcion") String descripcion,
//            @RequestParam("unidadMedida") String unidadMedida,
//            @RequestParam("stock") Integer stock,
//            @RequestParam("stockMinimo") Integer stockMinimo,
//            @RequestParam("stockMaximo") Integer stockMaximo,
//            @RequestParam("imagenInsumo") MultipartFile[] file) {
//        //sesion 
//        UserRol user = new UserRol();
//        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
//        String nombreImagen = this.personalService.subirImagen(file);
//        Insumo insumo = new Insumo();
//        if (nombreImagen == null) {
//            nombreImagen = "260x162.png";
//        } else {
//            
//            insumo.setFotoInsumo(nombreImagen);
//        }
//        insumo.setIdInsumo(id);
//        insumo.setNombreInsumo(nombre);
//        insumo.setDescripcionInsumo(descripcion);
//        insumo.setStockInsumo(stock);
//        insumo.setMinimoStockInsumo(stockMinimo);
//        insumo.setMaximoStockInsumo(stockMaximo);
//        insumo.setUnidadMedidaInsumo(unidadMedida);
//        
//        if(this.insumoService.modificarInsumo(insumo)){
//            
//        }else{
//            
//        }
//        List<Insumo> insumos = new ArrayList<Insumo>();
//        insumos = this.insumoService.listarInsumos();
//        //desarrollo aca 
//        modelo.addAttribute("agregar", true);
//        modelo.addAttribute("insumos", insumos);
//        //fin desarrollo 
//        //despachos 
//
//        //fin despacho 
//        //siempre despachar esto por la sesion 
//        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
//        //
//        return "users/administrador/mantenedor_insumos";
//    }



    @RequestMapping("/modificar_insumo")
    public String modificar_insumo(Model modelo, @RequestParam("id") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("unidadMedida") String unidadMedida,
            @RequestParam("stock") Integer stock,
            @RequestParam("stockMinimo") Integer stockMinimo,
            @RequestParam("stockMaximo") Integer stockMaximo) {
        //sesion 
>>>>>>> Felipe
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
     //sesion
     this.procedureQuery.DeletePersonalById(idPersonal);
         List<Personal> personales = new ArrayList<Personal>();
        personales = this.personalService.getAllUsuario();
        modelo.addAttribute("Personal", personal); 
        
  
        modelo.addAttribute("agregar", true);
<<<<<<< HEAD
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
         
         return "users/administrador/mantenedor_personal";
     }
   
      @RequestMapping("/loadEditarProveedor")
     public String loadEditarProveedor(Model modelo, @RequestParam("idPersonal") BigDecimal idPersonal ){}

     
     return "users/administrador/mantenedor_personal";
}
//       modelo.addAttribute("PersonalSesion", this.personalService.getPersonalSesion(user.getUsername()));

//        Personal person = new Personal();
//
//        //person.setIdPersonal(BigDecimal.valueOf(5));
//        person.setRutPersonal("17.347.762-6");
//        person.setNombresPersonal("Diego Alejandro");
//        person.setApePaternoPersonal("León");
//        person.setApeMaternoPersonal("Plaza");
//        person.setFechaNacimientoPersonal(new Date());
//        person.setCelularPersonal("954714587");
//        person.setCorreoPersonal("diego.leon@gmail.com");
//        person.setContrasenaPersonal(encoder.encode("123"));
//        person.setEstadoPersonal(BigInteger.valueOf(1));
//
//        Rol rol = new Rol();
//        rol.setIdRol(BigDecimal.valueOf(2));
//
//        person.setIdRol(rol);
//
//        this.personalService.addPersonal(person);
//        
//        //fin desarrollo 
//        //despacho 
//        
//        //fin despacacho 
//        //siempre despachar esto por la sesion 
//        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
=======
        modelo.addAttribute("insumos", insumos);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }


    @RequestMapping("/eliminar_insumo")
    public String eliminar_insumo(Model modelo, @RequestParam("idInsumo") Integer idInsumo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        this.procedureQuery.DeleteInsumo(idInsumo);
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        modelo.addAttribute("insumos", insumos);
        //desarrollo aca 

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

    @RequestMapping("/cargar_insumo")
    public String cargar_insumo(Model modelo, @RequestParam("idInsumo") Integer idInsumo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        Insumo insumo = this.insumoService.retornarInsumoById(idInsumo);
        modelo.addAttribute("modificar", true);
        modelo.addAttribute("insumo", insumo);
        modelo.addAttribute("insumos", insumos);

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL CAMBIAR A PORST
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
>>>>>>> Felipe
