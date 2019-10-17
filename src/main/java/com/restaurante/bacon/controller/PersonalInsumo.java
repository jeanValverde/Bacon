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

    private AmazonClient amazonClient;

    @Autowired
    PersonalInsumo(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    
    @RequestMapping("/inicio")
    public String inicio(Model modelo) {
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
                if(!filtro.equals("")){
                insumos = this.insumoService.filtrarInsumosByStock(BigInteger.valueOf(Integer.parseInt(filtro)));
                }else{
                    insumos = this.insumoService.listarInsumos();
                }
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
            @RequestParam("stock") Integer stock,
            @RequestParam("stockMinimo") Integer stockMinimo,
            @RequestParam("stockMaximo") Integer stockMaximo,
            @RequestParam("imagenInsumo") MultipartFile[] file) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        BigInteger stockValida,stockMinValida,stockMaxValida;
        stockValida = BigInteger.valueOf(Math.round(Math.abs(stock)));
        stockMinValida = BigInteger.valueOf(Math.round(Math.abs(stockMinimo)));
        stockMaxValida = BigInteger.valueOf(Math.round(Math.abs(stockMaximo)));
        
        Insumo insumo = new Insumo();
        
        insumo.setNombreInsumo(nombre);
        insumo.setDescripcionInsumo(descripcion);
        insumo.setStockInsumo(stockValida);
        insumo.setMinimoStockInsumo(stockMinValida);
        insumo.setMaximoStockInsumo(stockMaxValida);
        insumo.setUnidadMedidaInsumo(unidadMedida);
        String nombreImagen = this.personalService.subirImagen(file);
      
        if (nombreImagen != null){

            insumo.setFotoInsumo(nombreImagen);
            this.amazonClient.uploadFile(file[0], nombre);
            if (this.insumoService.ingresarInsumo(insumo)) {
                //envia al javascrit la respuesta del agregar
                modelo.addAttribute("tipoRespuesta", "agregar");
                modelo.addAttribute("respuesta", 1);
            } else {
               modelo.addAttribute("tipoRespuesta", "agregar");
               modelo.addAttribute("respuesta", 0);
            }
        }

        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        modelo.addAttribute("agregar", true);
        modelo.addAttribute("insumos", insumos);
         
       
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
        
        Insumo insumo = new Insumo();
       
        insumo.setIdInsumo(id);
        insumo.setNombreInsumo(nombre);
        insumo.setDescripcionInsumo(descripcion);
        insumo.setStockInsumo(stock);
        insumo.setMinimoStockInsumo(stockMinimo);
        insumo.setMaximoStockInsumo(stockMaximo);
        insumo.setUnidadMedidaInsumo(unidadMedida);
        //contiene los datos del insumo antes de modificar
        Insumo insu = new Insumo();
        insu = this.insumoService.retornarInsumoById(id);
        
        if(file[0].isEmpty()){
            insumo.setFotoInsumo(insu.getFotoInsumo().toString());
        }else{
            String nombreImagen = this.personalService.subirImagen(file);
            insumo.setFotoInsumo(nombreImagen);
            this.amazonClient.uploadFile(file[0], nombreImagen);
            this.personalService.eliminarImagen(insu.getFotoInsumo());
        }
        
        
        if (this.insumoService.modificarInsumo(insumo)) {
            modelo.addAttribute("tipoRespuesta", "modificar");
            modelo.addAttribute("respuesta", 1);
        } else {
            modelo.addAttribute("tipoRespuesta", "modificar");
            modelo.addAttribute("respuesta", 0);
        }
        
        List<Insumo> insumos = new ArrayList<Insumo>();
        insumos = this.insumoService.listarInsumos();
        //desarrollo aca 
        
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("agregar", true);
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
            modelo.addAttribute("tipoRespuesta", "eliminar");
            modelo.addAttribute("respuesta", 1);
        }else{
            modelo.addAttribute("tipoRespuesta", "eliminar");
            modelo.addAttribute("respuesta", 0);
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
