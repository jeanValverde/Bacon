/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.InsumoPedidoInsumoProveedorDAO;
import com.restaurante.bacon.dao.InsumoPedidoProveedorDAO;
import com.restaurante.bacon.dao.ListaInsumosPedidosProveedores;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoPedidoProveedor;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.service.EstadoPedidoService;
import com.restaurante.bacon.service.InsumoPedidoProveedorService;
import com.restaurante.bacon.service.InsumoPedidoService;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.PedidoService;
import com.restaurante.bacon.service.ProveedorService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/administrador/pedidoInsumo")
@Controller
public class PersonalPedidoInsumo {

    //acceder a CRUB y más del personal 
    @Autowired
    PersonalService personalService;

    @Autowired
    EstadoPedidoService estadoPedidoService;

    public static String UPLOAD_DIR_IMAGEN = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    InsumoService insumoService;
    @Autowired
    InsumoPedidoService insumoPedidoService;
    @Autowired
    InsumoPedidoProveedorService insumoPedidoProveedorService;
    @Autowired
    PedidoService pedidoService;
    @Autowired
    ProveedorService proveedorService;

    //para ingresar una contraseña encriptada 
    @Autowired
    private BCryptPasswordEncoder encoder;

    static ArrayList<Proveedor> gp;
    static ArrayList<InsumoPedidoInsumoProveedorDAO> insumos;
    @RequestMapping("/pedido")
    public String inicio(Model modelo, HttpSession sesion) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        sesion.setAttribute("ordenesPedidos", null);
        sesion.setAttribute("proveedores", null);

        List<InsumoPedidoProveedorDAO> insumos_pedidos_proveedor = new ArrayList<InsumoPedidoProveedorDAO>();
        insumos_pedidos_proveedor = this.insumoPedidoService.listarInsumosPedidos();
        gp = new ArrayList<Proveedor>();
        insumos = new ArrayList<InsumoPedidoInsumoProveedorDAO>();
        //desarrollo aca 
        modelo.addAttribute("insumos_pedidos", insumos_pedidos_proveedor);
        modelo.addAttribute("personalSesion", personal);
        personal = null;
        insumos_pedidos_proveedor = null;

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

    @RequestMapping("/cargarInsumosProveedor")
    public ResponseEntity cargarInsumosProveedor(Model modelo, HttpSession sesion, @RequestParam("idProveedor") BigInteger idProveedor, @RequestParam("idInsumo") Integer idInsumo) {

        InsumoProveedor insumoPedidoProveedor = new InsumoProveedor();
        insumoPedidoProveedor = this.insumoPedidoService.retornarInsumoProveedor(idInsumo, idProveedor);

        //BigInteger precio = insumoPedidoProveedor.getPrecio();
        return new ResponseEntity(insumoPedidoProveedor.getPrecio(), HttpStatus.OK);
    }

    @RequestMapping("/agregarInsumoPedidoProveedor")
    public ResponseEntity agregarInsumoPedidoProveedor(Model modelo, HttpSession sesion, @RequestParam("idInsumoPedido") Integer idInsumoPedido, @RequestParam("idProveedor") BigDecimal idProveedor, @RequestParam("idInsumo") Integer idInsumo) {
        
        InsumoPedidoInsumoProveedorDAO insumoPedidoInsumoProveedor = new InsumoPedidoInsumoProveedorDAO();
        ListaInsumosPedidosProveedores lipp = new ListaInsumosPedidosProveedores();

        
        
        Proveedor grupoProveedorPaso = new Proveedor();
        Proveedor grupoProveedor = new Proveedor();
        grupoProveedorPaso = this.proveedorService.retornarProveedorPorId(Integer.parseInt(""+idProveedor));
        
        //guarda los proveedors de forma agrupada, para despues agrupar los insumos a cada proveedor
        boolean proveedorExiste = false;
        if (gp.size() == 0) {
            grupoProveedor.setIdProveedor(idProveedor);
            grupoProveedor.setNombreProveedor(grupoProveedorPaso.getNombreProveedor());
            grupoProveedorPaso = null;
            //grupoProveedores.add(grupoProveedor);
            gp.add(grupoProveedor);
        } else {
            for (int i = 0; i < gp.size(); i++) {
                String x = "" + idProveedor;
                String y = "" + gp.get(i).getIdProveedor();
                System.out.println("idProveedor: "+idProveedor);
                System.out.println("idProveedor 2: "+gp.get(i).getIdProveedor());
                if (y.equals(x)) {
                    proveedorExiste = true;
                }
            }
            if(!proveedorExiste){
                grupoProveedor.setIdProveedor(idProveedor);
                grupoProveedor.setNombreProveedor(grupoProveedorPaso.getNombreProveedor());
                gp.add(grupoProveedor);
            }
            
        }
        
        
        for(int i=0;i<insumos.size();i++){
            System.out.println("df: ");
        }
        

        boolean insumoExiste = false;
        
            for (int i = 0; i < insumos.size(); i++) {
                System.out.println(insumos.get(i).getInsumoPedido().getIdInsumoPedido());
                if (Integer.parseInt("" + insumos.get(i).getInsumoPedido().getIdInsumoPedido()) == Integer.parseInt("" + idInsumoPedido)) {
                    insumoExiste = true;
                    System.out.println("si existe");
                    break;
                }
            }
        
        if (!insumoExiste) {
            Insumo insumo = new Insumo();
            insumo = this.insumoService.retornarInsumoById(idInsumo);
            insumoPedidoInsumoProveedor.setInsumo(insumo);

            InsumoPedido insumoPedido = new InsumoPedido();
            InsumoPedido insumoPedidoPaso = new InsumoPedido();
            insumoPedidoPaso = this.insumoPedidoService.retornarInsumoPedido(idInsumoPedido);
            insumoPedido.setCantidadInsumo(insumoPedidoPaso.getCantidadInsumo());
            insumoPedido.setEstadoInsumoPedido(insumoPedidoPaso.getEstadoInsumoPedido());
            insumoPedido.setIdInsumoPedido(insumoPedidoPaso.getIdInsumoPedido());
            insumoPedidoPaso = null;
            insumoPedidoInsumoProveedor.setInsumoPedido(insumoPedido);
            insumoPedido = null;

            InsumoProveedor insumoProveedor = new InsumoProveedor();
            InsumoProveedor insumoProveedorPaso = new InsumoProveedor();
            insumoProveedorPaso = this.insumoPedidoService.retornarInsumoProveedor(idInsumo, BigInteger.valueOf(Integer.parseInt("" + idProveedor)));

            insumoProveedor.setIdInsumoProveedor(insumoProveedorPaso.getIdInsumoProveedor());
            insumoProveedor.setPrecio(insumoProveedorPaso.getPrecio());
            insumoProveedorPaso = null;
            insumoPedidoInsumoProveedor.setInsumoProveedor(insumoProveedor);
            insumoProveedor = null;

            Proveedor proveedor = new Proveedor();
            Proveedor proveedorPaso = new Proveedor();
            double idProveedorPaso = Double.parseDouble("" + idProveedor);
            proveedorPaso = this.proveedorService.retornarProveedorById(BigDecimal.valueOf(idProveedorPaso));
            proveedor.setNombreProveedor(proveedorPaso.getNombreProveedor());
            proveedor.setIdProveedor(proveedorPaso.getIdProveedor());
            proveedorPaso = null;
            insumoPedidoInsumoProveedor.setProveedor(proveedor);

            insumos.add(insumoPedidoInsumoProveedor);
            insumoPedidoInsumoProveedor = null;
        } else {

        }
        System.out.println("tamaño arreglo: "+gp.size());
        lipp.setInsumos(insumos);

        lipp.setGrupoProveedores(gp);

        sesion.setAttribute("ordenesPedidos", insumos);
        sesion.setAttribute("proveedores", gp);

        return new ResponseEntity(lipp, HttpStatus.OK);
    }

    @RequestMapping("/eliminarInsumoPedidoProveedor")
    public ResponseEntity eliminarInsumoPedidoProveedor(Model modelo, HttpSession sesion, @RequestParam("idInsumoPedido") Integer idInsumoPedido, @RequestParam("idProveedor") BigInteger idProveedor, @RequestParam("idInsumo") Integer idInsumo) {

        ListaInsumosPedidosProveedores lipp = new ListaInsumosPedidosProveedores();

       

        if (insumos.size()!=0) {
            insumos = (ArrayList) sesion.getAttribute("ordenesPedidos");
            for (int i = 0; i < insumos.size(); i++) {
                if (Integer.parseInt("" + insumos.get(i).getInsumoPedido().getIdInsumoPedido()) == idInsumoPedido) {
                    insumos.remove(i);
                }
            }
        } 

        for (int i = 0; i < gp.size(); i++) {
                boolean extiste = false;
                for (int x = 0; x < insumos.size(); x++) {
                    if (Integer.parseInt("" + gp.get(i).getIdProveedor()) == Integer.parseInt("" + insumos.get(x).getProveedor().getIdProveedor())) {
                        extiste = true;
                        break;
                    }
                }
                if (!extiste) {
                    gp.remove(i);
                }

        }


        lipp.setInsumos(insumos);
        lipp.setGrupoProveedores(gp);
        System.out.println("Tañano arreglo proveedores: " + gp.size());
        System.out.println("Tañano arreglo insumos: " + insumos.size());

        sesion.setAttribute("ordenesPedidos", insumos);
        sesion.setAttribute("proveedores", gp);

        return new ResponseEntity(lipp, HttpStatus.OK);
    }

    @RequestMapping("/agregarPedido")
    public String agregarPedido(Model modelo, HttpSession sesion) {
        //sesion 
        UserRol user = new UserRol();
        Personal personal = this.personalService.getPersonalSesion(user.getUsername());
        //sesion 
        
        ArrayList<InsumoPedidoProveedor> insumosPedidosProveedor;

        if (insumos.size()!=0) {
            
            

            for (int i = 0; i < gp.size(); i++) {
                System.out.println("---- Pedido N° " + i + " ----");
                Integer totalPedido = 0;
                Integer valorPedido = 0;
                for (int x = 0; x < insumos.size(); x++) {
                    if (Integer.parseInt(""+ insumos.get(x).getProveedor().getIdProveedor()) == Integer.parseInt(""+gp.get(i).getIdProveedor())) {
                        Integer precio = Integer.parseInt("" + insumos.get(x).getInsumoProveedor().getPrecio());
                        Integer cantidad = Integer.parseInt("" + insumos.get(x).getInsumoPedido().getCantidadInsumo());
                        Integer total = precio * cantidad;
                        long ivaPasoInsumo = Math.round(total * 0.19);
                        Integer ivaInsumo = Integer.parseInt("" + ivaPasoInsumo);
                        valorPedido = valorPedido + total;
                    }
                }
                long ivaPaso = Math.round(valorPedido * 0.19);
                Integer iva = Integer.parseInt("" + ivaPaso);
                totalPedido = valorPedido;
                 System.out.println("valorPedido: "+valorPedido);
                System.out.println("Id Proveedor: " + gp.get(i).getIdProveedor());

                Integer idPedido = this.pedidoService.insertarPedido(valorPedido, iva, totalPedido, " ", 0, " ", 1, Integer.parseInt("" + gp.get(i).getIdProveedor()));
                
                System.out.println("idPedido: "+idPedido);
                for (int x = 0; x < insumos.size(); x++) {
                    if (Integer.parseInt(""+ insumos.get(x).getProveedor().getIdProveedor()) == Integer.parseInt(""+gp.get(i).getIdProveedor())) {
                        Integer precio = Integer.parseInt("" + insumos.get(x).getInsumoProveedor().getPrecio());
                        Integer cantidad = Integer.parseInt("" + insumos.get(x).getInsumoPedido().getCantidadInsumo());
                        Integer total = precio * cantidad;
                        cantidad = null;
                        long ivaPasoInsumo = Math.round(total * 0.19);
                        Integer ivaInsumo = Integer.parseInt("" + ivaPasoInsumo);
                        Integer idInsumoPedidoProveedor = this.insumoPedidoProveedorService.insertarInsumoPedidoProveedor(precio, total, ivaInsumo,Integer.parseInt(""+insumos.get(x).getInsumoPedido().getIdInsumoPedido()) ,idPedido );
                        System.out.println("idInsumoPedidoProveedor: "+idInsumoPedidoProveedor);
                    }
                }
            }
            

        } else {

        }
        gp = new ArrayList<Proveedor>();
        insumos = new ArrayList<InsumoPedidoInsumoProveedorDAO>();
        List<InsumoPedidoProveedorDAO> insumos_pedidos_proveedor = new ArrayList<InsumoPedidoProveedorDAO>();
        insumos_pedidos_proveedor = this.insumoPedidoService.listarInsumosPedidos();
        //desarrollo aca 
        modelo.addAttribute("insumos_pedidos", insumos_pedidos_proveedor);
        insumos_pedidos_proveedor = null;
         modelo.addAttribute("respuesta", true);
        modelo.addAttribute("personalSesion", personal);
        return "users/administrador/pedido_proveedor";

    }

}
