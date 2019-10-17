/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoPedidoDao;
import com.restaurante.bacon.dao.IInsumoPedidoProveedorDao;
import com.restaurante.bacon.dao.IInsumoProveedorDao;
import com.restaurante.bacon.dao.IPedidoDao;
import com.restaurante.bacon.dao.InsumoPedidoProveedorDAO;
import com.restaurante.bacon.dao.ProcedureQueryPedido;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Pedido;
import com.restaurante.bacon.dto.Proveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.bacon.service.InsumoService;
import java.util.Date;

/**
 *
 * @author jean
 */
@Service
public class PedidoService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IInsumoProveedorDao IInsumoProveedorDao;
    @Autowired
    IInsumoPedidoProveedorDao IInsumoPedidoProveedorDao;
    @Autowired
    IPedidoDao IPedidoDao;
    @Autowired
    PedidoService insumoProveedor;
    @Autowired
    ProcedureQueryPedido procedureQueryPedido;
    
    //se implementan los metodos declarados en la interfaz 
    public Integer insertarPedido( Integer valor_pedido,Integer iva_pedido,Integer total_pedido,String detalle_pedido,Integer revisado_finanza,String motivo_rechazo,Integer id_estado_pedido,Integer id_proveedor){
        return this.procedureQueryPedido.InsertPedido( valor_pedido, iva_pedido, total_pedido, detalle_pedido, revisado_finanza, motivo_rechazo, id_estado_pedido, id_proveedor);
    }
    public Pedido retornarPedidoPorId(Integer idPedido){
        return this.procedureQueryPedido.retornarPedido(idPedido);
    }
    
    
    
   
   
    
}
