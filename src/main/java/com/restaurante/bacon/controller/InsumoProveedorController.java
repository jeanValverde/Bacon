/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.service.InsumoProveedorService;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.ProveedorService;
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

/**
 *
 * @author estebantoledo
 */
@RequestMapping("/administrador/insumoProveedor")
@Controller
public class InsumoProveedorController {

    //Acceder a metodos CRUD y más de presonal
    @Autowired
    InsumoProveedorService insumoproveedor;

    //acceder a CRUB y más del personal
    @Autowired
    PersonalService personalService;
    @Autowired
    ProveedorService proveedorService;
    @Autowired
    InsumoService insumoservice;

    //Aceeder a todos los procedimientos almacenados
    @Autowired
    ProcedureQuery procedureQuery;

    @RequestMapping("/mantenedor_Proveedor_Insumo")
    public String mantenedor_Proveedor_Insumo(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
//        //sesion
        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);

        // modelo.addAttribute("insumos", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        //modelo.addAttribute("tipo", "misInsumos");
        //modelo.addAttribute("insumosProveedor", this.insumoproveedor.listarInsumoProveedor());
        // modelo.addAttribute("agregar", true);
        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("insumoProveedor", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_Proveedor_Insumo";
    }

    @RequestMapping("/insumosProveedor")
    public String insumosProveedor(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion
        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);

        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("insumoProveedor", this.insumoproveedor.insumosByIdProveedor(idProveedor));

        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_Proveedor_Insumo";
    }

    @RequestMapping("/verInsumos")
    public String verInsumos(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion
        //desarrollo aca
        Proveedor ins = this.proveedorService.buscarProveedorById(idProveedor);

        modelo.addAttribute("insumos", this.insumoproveedor.listarInsumosAgregarProveedor(idProveedor));
        modelo.addAttribute("tipo", "agregar");
        modelo.addAttribute("proveedor", ins);

        //buscar los insumos para el provedor
        //fin desarrollo
        //despachos
        //fin despacho
        //siempre despachar esto por la sesion
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        //
        return "users/administrador/mantenedor_Proveedor_Insumo";

    }

    @PostMapping("/addInsumoProveedor")
    public String addInsumoProveedor(Model model,
            @RequestParam("precio") BigInteger Precio,
            @RequestParam("idInsumo") Integer idInsumo,
            @RequestParam("idProveedor") BigDecimal idProveedor) {
        //sesion
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion
        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);
        InsumoProveedor insumoProveedor = new InsumoProveedor();

        insumoProveedor.setPrecio(Precio);
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(idInsumo);
        insumoProveedor.setIdInsumo(insumo);
        Proveedor proveedores = new Proveedor();
        proveedores.setIdProveedor(idProveedor);
        insumoProveedor.setIdProveedor(proveedores);

        this.insumoproveedor.ingresarInsumoProveedor(insumoProveedor);

        model.addAttribute("tipo", "misInsumos");
        model.addAttribute("insumoProveedor", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_Proveedor_Insumo";

    }

    @RequestMapping("/deleteInsumo")
    public String deleteInsumo(Model modelo, @RequestParam("idProveedor") BigDecimal idProveedor,
            @RequestParam("idInsumoProveedor") Integer idInsumoProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);
        this.insumoproveedor.eliminarInsumoProveedor(idInsumoProveedor);

        // modelo.addAttribute("insumos", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        //modelo.addAttribute("tipo", "misInsumos");
        //modelo.addAttribute("insumosProveedor", this.insumoproveedor.listarInsumoProveedor());
        // modelo.addAttribute("agregar", true);
        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("insumoProveedor", this.insumoproveedor.insumosByIdProveedor(idProveedor));

        List<InsumoProveedor> proveedores = new ArrayList<InsumoProveedor>();
        proveedores = this.insumoproveedor.listarInsumoProveedor();

        modelo.addAttribute("modificar", true);
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_Proveedor_Insumo";
    }

    @RequestMapping("/editarInsumoProveedor")
    public String editarInsumoProveedor(Model modelo,
            @RequestParam("idInsumoProveedor") Integer idInsumoProveedor,
            @RequestParam("precio") BigInteger precio,
            @RequestParam("idProveedor") BigDecimal idProveedor
    ) {

        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 

        InsumoProveedor insumoProveedor = new InsumoProveedor();
        insumoProveedor.setIdInsumoProveedor(idInsumoProveedor);

        insumoProveedor.setPrecio(precio);

        this.insumoproveedor.update(insumoProveedor);

        Proveedor proveedor = this.proveedorService.buscarProveedorById(idProveedor);

        // modelo.addAttribute("insumos", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        //modelo.addAttribute("tipo", "misInsumos");
        //modelo.addAttribute("insumosProveedor", this.insumoproveedor.listarInsumoProveedor());
        // modelo.addAttribute("agregar", true);
        modelo.addAttribute("tipo", "misInsumos");
        modelo.addAttribute("insumoProveedor", this.insumoproveedor.insumosByIdProveedor(idProveedor));
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("personalSesion", this.personalService.getPersonalSesion(user.getUsername()));
        return "users/administrador/mantenedor_Proveedor_Insumo";
    }

}
