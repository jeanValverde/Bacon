/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.aws.s3.AmazonClient;
import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.RecetaService;
import java.io.IOException;
import com.restaurante.bacon.service.InsumoService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static String UPLOAD_DIR_IMAGEN = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    InsumoService insumoService;
    @Autowired
    ProcedureQuery procedureQuery;

    //para ingresar una contraseña encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;

    private AmazonClient amazonClient;

    @Autowired
    PersonalController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

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
    public String receta(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");

        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 0);
        //fin mensajes 

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

    @RequestMapping("/deleteReceta")
    public String deleteReceta(Model modelo, @RequestParam("idReceta") Integer idReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        this.recetaService.deleteRecetaById(idReceta);

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        List<Receta> recetas = this.recetaService.listar();

        modelo.addAttribute("recetas", recetas);

        modelo.addAttribute("tipoForm", "agregar");
        //fin desarrollo 
        //despachos 
        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 1);
        modelo.addAttribute("nombreMensaje", "Información");
        modelo.addAttribute("mensaje", "Receta Eliminada");
        //puede ser success - info - danger - warning
        modelo.addAttribute("tipoMensaje", "success");
        //fin mensajes 
        //desarrollo aca 
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedorReceta";
    }

    @RequestMapping("/loadEditarReceta")
    public String loadEditarReceta(Model modelo, @RequestParam("idReceta") Integer idReceta) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        Receta receta = this.recetaService.buscarRecetaById(idReceta);

        List<Receta> recetas = this.recetaService.listar();

        modelo.addAttribute("recetas", recetas);

        modelo.addAttribute("recetaEdit", receta);

        modelo.addAttribute("tipoForm", "editar");

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

    @RequestMapping("/mantenedor_insumos")
    public String mantenedor_insumos(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/mantenedor_insumos";

    }

    @PostMapping("/addReceta")
    public String addReceta(Model modelo,
            @RequestParam("nombreReceta") String nombreReceta,
            @RequestParam("descripcionReceta") String descripcionReceta,
            @RequestParam("duracionReceta") Integer duracionReceta,
            @RequestParam("precioReceta") Integer precioReceta,
            @RequestParam("cantidadReceta") Integer cantidadReceta,
            @RequestParam("tipoReceta") String tipoReceta,
            @RequestParam("categoriaReceta") String categoriaReceta,
            @RequestParam("imagenReceta") MultipartFile[] file) throws IOException {

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

        if (nombre == null) {
            nombre = "foto";
        } else {
            receta.setFoto(nombre);
            this.amazonClient.uploadFile(file[0], nombre);
        }

        this.recetaService.add(receta);

        ///mensajes 1 = si mensaje / 0 = no mensaje
        modelo.addAttribute("isMensaje", 1);
        modelo.addAttribute("nombreMensaje", "Información");
        modelo.addAttribute("mensaje", "Receta agregada");
        //puede ser success - info - danger - warning
        modelo.addAttribute("tipoMensaje", "success");
        //fin mensajes 

        modelo.addAttribute("categoriasReceta", this.recetaService.listarCategoria());

        modelo.addAttribute("tipoForm", "agregar");

        modelo.addAttribute("recetas", this.recetaService.listar());

        //desarollo
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", personal);
        //
        //cargar el html nombre
        return "users/administrador/mantenedorReceta";

    }
//
//    @RequestMapping("/ingresar_insumo")
//    public String ingresar_insumo(Model modelo, @RequestParam("nombre") String nombre,
//            @RequestParam("descripcion") String descripcion,
//            @RequestParam("unidadMedida") String unidadMedida,
//            @RequestParam("stock") BigInteger stock,
//            @RequestParam("stockMinimo") BigInteger stockMinimo,
//            @RequestParam("stockMaximo") BigInteger stockMaximo,
//            @RequestParam("imagenInsumo") MultipartFile[] file) {
//        //sesion 
//        UserRol user = new UserRol();
//        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
//        //sesion 
//
//
//        this.procedureQuery.InsertInsumo(nombre, descripcion, stock, stockMinimo, stockMaximo, unidadMedida);
//
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
//
//        List<Insumo> insumos = new ArrayList<Insumo>();
//        insumos = this.insumoService.listarInsumos();
//        //desarrollo aca 
//        modelo.addAttribute("agregar", true);
//        modelo.addAttribute("insumos", insumos);
//        //fin desarrollo 
//        //despachos 
//        return "users/administrador/mantenedor_insumos";
//
//    }

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
            @RequestParam("stock") BigInteger stock,
            @RequestParam("stockMinimo") BigInteger stockMinimo,
            @RequestParam("stockMaximo") BigInteger stockMaximo,
            @RequestParam("imagenInsumo") MultipartFile[] file) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //String nombreImagen = this.personalService.subirImagen(file);
        Insumo insumo = new Insumo();
        /*if (nombreImagen == null) {
            nombreImagen = "260x162.png";
        } else {
            
            insumo.setFotoInsumo(nombreImagen);
        }*/

        insumo.setIdInsumo(id);
        insumo.setNombreInsumo(nombre);
        insumo.setDescripcionInsumo(descripcion);
        insumo.setStockInsumo(stock);
        insumo.setMinimoStockInsumo(stockMinimo);
        insumo.setMaximoStockInsumo(stockMaximo);
        insumo.setUnidadMedidaInsumo(unidadMedida);
        insumo.setFotoInsumo("adfbfd87-379f-4760-8771-643c689a9537.jpg");

        if (this.insumoService.modificarInsumo(insumo)) {

        } else {
            System.out.println("no modifico");
            System.out.println("stock: " + stockMinimo);
        }
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
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
    //ESTE ES UN EJEMPLO PARA AGREGAR UN PERSONAL *CAMBIAR A PORST*
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
