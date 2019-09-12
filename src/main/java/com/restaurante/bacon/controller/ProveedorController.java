/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.RecetaService;
import java.io.IOException;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.ProveedorService;
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
 */

@RequestMapping("/administrador_proveedor")
@Controller
public class ProveedorController {

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

    @Autowired
    ProveedorService proveedorService;

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
    
    @RequestMapping("/mantenedor_proveedor")
    public String mantenedor_proveedor(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        //desarrollo aca 
        modelo.addAttribute("proveedores", proveedores);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", personal);
        
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_proveedor";

    }

    @PostMapping("/addProveedor")
    public String addProveedor(Model modelo,
            @RequestParam("rutProveedor") String rutProveedor,
            @RequestParam("nombreProveedor") String nombreProveedor,
            @RequestParam("direccionProveedor") String direccionProveedor,
            @RequestParam("telefonoProveedor") String telefonoProveedor,
            @RequestParam("contactoVenta") String contactoVenta,
            @RequestParam("tipoProveedor") String tipoProveedor,
            @RequestParam("correoProveedor") String correoProveedor,
            @RequestParam("celularProveedor") Integer celularProveedor,
            @RequestParam("categoriaProveedor") String categoriaProveedor) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Proveedor proveedor = new Proveedor();

        proveedor.setRutProveedor(rutProveedor);
        proveedor.setNombreProveedor(nombreProveedor);
        proveedor.setDireccionProveedor(direccionProveedor);
        proveedor.setTelefonoProveedor(telefonoProveedor);
        proveedor.setContactoVenta(contactoVenta);
        proveedor.setTipoProveedor(tipoProveedor);
        proveedor.setCorreoProveedor(correoProveedor);
        proveedor.setCelularProveedor(celularProveedor);
        proveedor.setCategoriaProveedor(categoriaProveedor);

        this.proveedorService.addProveedor(proveedor);

        //desarollo
        //fin desarrollo 
        //despacho  modelo.addAttribute(nombreDespacho, objetoAdespachar)
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", personal);
        
         List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        //desarrollo aca 
        modelo.addAttribute("proveedores", proveedores);
        modelo.addAttribute("agregar", true);
        
        
        //
        //cargar el html nombre
        return "users/administrador/mantenedor_proveedor";

    }
    
     @RequestMapping("/eliminar_proveedor")
    public String eliminar_proveedor(Model modelo, @RequestParam("idProveedor") Integer idProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        this.procedureQuery.DeleteProveedorById(idProveedor);
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        //desarrollo aca 

        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_proveedor";
    }
    
    @RequestMapping("/loadEditarProveedor")
    public String loadEditarProveedor(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        

        
        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);

        List<Proveedor> proveedores = this.proveedorService.listarProveedores();

        modelo.addAttribute("provedores", proveedores);
        modelo.addAttribute("modificar",true);

        modelo.addAttribute("tipoForm", "editar");

        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //

        return "users/administrador/mantenedorReceta";
    }
    @RequestMapping("/cargar_proveedor")
    public String cargar_proveedor(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        Proveedor proveedor = this.proveedorService.retornarInsumoById(idProveedor);
        modelo.addAttribute("modificar", true);
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("proveedores", proveedores);
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_proveedor";
    }
    
     @RequestMapping("/modificar_proveedor")
    public String modificar_proveedor(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor,
             @RequestParam("rutProveedor") String rutProveedor,
            @RequestParam("nombreProveedor") String nombreProveedor,
            @RequestParam("direccionProveedor") String direccionProveedor,
            @RequestParam("telefonoProveedor") String telefonoProveedor,
            @RequestParam("contactoVenta") String contactoVenta,
            @RequestParam("tipoProveedor") String tipoProveedor,
            @RequestParam("correoProveedor") String correoProveedor,
            @RequestParam("celularProveedor") Integer celularProveedor,
            @RequestParam("categoriaProveedor") String categoriaProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        
      
        
        
        if(this.proveedorService.modificarProveedor(idProveedor,rutProveedor, nombreProveedor, direccionProveedor, telefonoProveedor, contactoVenta, tipoProveedor, correoProveedor, celularProveedor, categoriaProveedor)){
        
            
        }else{
            
        }
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("proveedores", proveedores);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_proveedor";
    }
    
     @RequestMapping("/filtro_proveedor")

    

    public String filtro(Model modelo, @RequestParam("nombreProveedor") String nombreProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        //desarrollo aca 

        
        modelo.addAttribute("proveedores", this.proveedorService.listarProveedores());

        List<Proveedor> proveedores = this.proveedorService.filtrarProveedoresByNombre(nombreProveedor);

        modelo.addAttribute("proveedores", proveedores);
        

        modelo.addAttribute("tipoForm", "agregar");
        //fin desarrollo 
        //despachos 
        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_proveedor";
    }
}
