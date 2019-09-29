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
@RequestMapping("/administrador/insumo")
@Controller
public class PersonalInsumo {

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

    @RequestMapping("/inicio")
    public String inicio(Model modelo) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        if(this.personalService.comprobarImagen("adfbfd87-379f-4760-8771-643c689a9537.jpg")){
            System.out.println("existe la imagen qla");
        }
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("agrego", 1);
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/mantenedor_insumos";

    }

    @RequestMapping("/buscar_por_filtro")
    public String buscar_por_filtro(Model modelo, @RequestParam("tipoBusqueda") String tipoBusqueda, @RequestParam("filtro") String filtro) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        List<Insumo> insumos = new ArrayList<Insumo>();
        switch (tipoBusqueda) {
            case "nombre":
                insumos = this.insumoService.filtrarInsumosByNombre(filtro);
                break;
            case "stock":
                insumos = this.insumoService.filtrarInsumosByStock(BigInteger.valueOf(Integer.parseInt(filtro)));
                break;
            case "unidad":
                insumos = this.insumoService.filtrarInsumosByUnidadMedida(filtro);
                break;
            default:
                insumos = this.insumoService.listarInsumos();
        }

        //desarrollo aca 
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);
       
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/mantenedor_insumos";

    }

    @RequestMapping("/agregar_insumo")
    public String agregar_insumo(Model modelo, @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("unidadMedida") String unidadMedida,
            @RequestParam("stock") BigInteger stock,
            @RequestParam("stockMinimo") BigInteger stockMinimo,
            @RequestParam("stockMaximo") BigInteger stockMaximo,
            @RequestParam("imagenInsumo") MultipartFile[] file) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Insumo insumo = new Insumo();

        insumo.setNombreInsumo(nombre);
        insumo.setDescripcionInsumo(descripcion);
        insumo.setStockInsumo(stock);
        insumo.setMinimoStockInsumo(stockMinimo);
        insumo.setMaximoStockInsumo(stockMaximo);
        insumo.setUnidadMedidaInsumo(unidadMedida);
        String nombreImagen = this.personalService.subirImagen(file);
      
        if (nombreImagen != null){

            insumo.setFotoInsumo(nombreImagen);
            if (this.insumoService.ingresarInsumo(insumo)) {
                modelo.addAttribute("agrego", "1");
            } else {
               modelo.addAttribute("agrego", "0");
            }
        }

        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("modifico", false);
         
       
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

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
        boolean x = false;
        if (this.insumoService.modificarInsumo(insumo)) {
            x = true;
        } else {
            x = false;
        }
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agrego", false);
        modelo.addAttribute("modifico", x);
        //fin desarrollo 
        //despachos 

        //fin despacho 
        //siempre despachar esto por la sesion 
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_insumos";
    }

    @RequestMapping("/eliminar_insumo")
    public String eliminar_insumo(Model modelo, @RequestParam("idInsumo") Integer idInsumo,@RequestParam("nombreFoto") String nombreFoto) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        if(this.insumoService.eliminarInsumo(idInsumo)){
            this.personalService.eliminarImagen(nombreFoto);
        }
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);
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

}
