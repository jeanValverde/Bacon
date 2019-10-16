/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoPedidoDao;
import com.restaurante.bacon.dao.InsumoPedidoProveedorDAO;
import com.restaurante.bacon.dao.ProcedureQueryPedido;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.bacon.service.InsumoService;

/**
 *
 * @author jean
 */
@Service
public class InsumoPedidoService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IInsumoPedidoDao insumoPedidoDao;
    @Autowired
    InsumoService insumoService;
    @Autowired
    ProcedureQueryPedido procedureQueryPedido;
    
    //se implementan los metodos declarados en la interfaz 
   
    
    public List<InsumoPedidoProveedorDAO> listarInsumosPedidos(){
        ArrayList<InsumoPedidoProveedorDAO> insumos_pedidos_proveedor = new ArrayList<InsumoPedidoProveedorDAO>();
        List<InsumoPedido> insumos_pedidos = this.procedureQueryPedido.listarInsumosPedidos();
        
        for (int i = 0; i< insumos_pedidos.size();i++){
            InsumoPedidoProveedorDAO ins = new InsumoPedidoProveedorDAO();
            ins.setInsumoPedido(insumos_pedidos.get(i));
            Insumo insumo = new Insumo();
            insumo = insumoService.retornarInsumoById(insumos_pedidos.get(i).getIdInsumo().getIdInsumo());
            ins.setInsumo(insumo);
            ins.setProveedores(this.procedureQueryPedido.listarProveedoresConInsumoPedido(BigInteger.valueOf(insumo.getIdInsumo())));
            insumos_pedidos_proveedor.add(ins);
        }
        return insumos_pedidos_proveedor;
        //return this.insumoPedidoDao.findAll();
    }
    public InsumoProveedor retornarInsumoProveedor(Integer idInsumo,BigInteger idProveedor){
        InsumoProveedor insumoProveedor = new InsumoProveedor();
        insumoProveedor = this.procedureQueryPedido.retornarInsumoProveedor(idInsumo,idProveedor);
        return insumoProveedor;
    }
    public InsumoPedido retornarInsumoPedido(Integer id){
        InsumoPedido insumoPedido = new InsumoPedido();
        return insumoPedido = this.procedureQueryPedido.retornarInsumoPedido(id);
        
    }
    
   
   
    
}
