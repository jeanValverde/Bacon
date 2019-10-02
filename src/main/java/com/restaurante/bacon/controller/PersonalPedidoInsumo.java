/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.InsumoPedidoProveedorDAO;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoPedidoProveedor;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.InsumoPedidoService;
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
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 *
 * No crear metodos propios en los controladores para eso esta el servicio
 *
 *
 */
@RequestMapping("/administrador/pedidoInsumo")
@Controller
public class PersonalPedidoInsumo {

    //acceder a CRUB y más del personal 
    @Autowired
    PersonalService personalService;

    @Autowired
    RecetaService recetaService;
    @Autowired
    InsumoPedidoService insumoPedidoService;
    public static String UPLOAD_DIR_IMAGEN = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    InsumoService insumoService;
    
    @Autowired
    ProveedorService proveedorService;
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
       
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        List<InsumoPedidoProveedorDAO>insumos_pedidos_proveedor = new ArrayList<InsumoPedidoProveedorDAO>();
        insumos_pedidos_proveedor = this.insumoPedidoService.listarInsumosPedidos();
        modelo.addAttribute("proveedores", proveedores);
        //desarrollo aca 
        modelo.addAttribute("insumos_pedidos", insumos_pedidos_proveedor);
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/pedido_proveedor";

    }
    
    @RequestMapping("/cargarPrecio")
    public ResponseEntity cargarPrecio(Model modelo, HttpSession sesion, @RequestParam("idProveedor") BigInteger idProveedor, @RequestParam("idInsumo") Integer idInsumo) {
        
        InsumoProveedor insumoPedidoProveedor = new InsumoProveedor();
        insumoPedidoProveedor = this.insumoPedidoService.retornarInsumoProveedor(idInsumo, idProveedor);
        
        System.out.println(insumoPedidoProveedor.getIdInsumoProveedor());
        
        //BigInteger precio = insumoPedidoProveedor.getPrecio();
      
        
        return new ResponseEntity(insumoPedidoProveedor.getPrecio(), HttpStatus.OK);
    }
    @RequestMapping("/cargarInsumoPedidoProveedor")
    public ResponseEntity cargarInsumoPedidoProveedor(Model modelo, HttpSession sesion,@RequestParam("idInsumoPedido") Integer idInsumoPedido, @RequestParam("idProveedor") BigInteger idProveedor, @RequestParam("idInsumo") Integer idInsumo) {
        
        Map<InsumoPedidoProveedor, Integer> ordenesInsumos = (Map<InsumoPedidoProveedor, Integer>) sesion.getAttribute("insumosPedidoProveedor");
        InsumoProveedor insumoProveedor = new InsumoProveedor();
        InsumoPedido insumoPedido = new InsumoPedido();
        InsumoPedidoProveedor insumoPedidoProveedor = new InsumoPedidoProveedor();
        
        insumoProveedor = this.insumoPedidoService.retornarInsumoProveedor(idInsumo, idProveedor);
        insumoPedido = this.insumoPedidoService.retornarInsumoPedido(idInsumo);
        insumoPedidoProveedor.setIdInsumoPedido(insumoPedido);
        Integer precio = Integer.parseInt(""+insumoProveedor.getPrecio());
        Integer cantidad = Integer.parseInt(""+insumoPedido.getCantidadInsumo());
        Integer precioPedido = precio*cantidad;
        insumoPedidoProveedor.setPrecioPedidoProveedor(BigInteger.valueOf(precioPedido));
        Double precioTotal = Integer.parseInt(""+insumoPedidoProveedor.getPrecioPedidoProveedor())*0.19;
        long precioTotalpaso =Math.round(precioTotal);
        insumoPedidoProveedor.setIvaInsumoPedido(BigInteger.valueOf(precioTotalpaso));
        Integer total = Integer.parseInt(""+insumoPedidoProveedor.getIvaInsumoPedido())+Integer.parseInt(""+insumoPedidoProveedor.getPrecioPedidoProveedor());
        insumoPedidoProveedor.setPrecioTotalInsumoPedido(BigInteger.valueOf(total));
        
        ordenesInsumos.put(insumoPedidoProveedor, 0);
        
        sesion.setAttribute("ordenesPedidos", ordenesInsumos);
        //BigInteger precio = insumoPedidoProveedor.getPrecio();
      
        
        return new ResponseEntity("", HttpStatus.OK);
    }
    @RequestMapping("/buscar_por_filtro")
    public String buscar_insumos_proveedor(Model modelo, @RequestParam("proveedor") Integer idProveedor) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
       

        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorService.listarProveedores();
        
        
        
        //desarrollo aca 
        modelo.addAttribute("proveedores", proveedores);
        modelo.addAttribute("personalSesion", personal);
       
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/mantenedor_insumos";

    }

    

   

    

    

}
