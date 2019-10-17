/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;
import com.restaurante.bacon.dao.IInsumoPedidoProveedorDao;
import com.restaurante.bacon.dao.ProcedureQueryInsumoPedidoProveedor;
import com.restaurante.bacon.dao.ProcedureQueryPedido;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoPedidoProveedor;
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

/**
 *
 * @author jean
 */
@Service
public class InsumoPedidoProveedorService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    
    @Autowired
    IInsumoPedidoProveedorDao insumoPedidoProveedorDao;
    @Autowired
    InsumoPedidoProveedorService insumoProveedor;
    @Autowired
    ProcedureQueryInsumoPedidoProveedor procedureQueryInsumoPedidoProveedor;
    
    //se implementan los metodos declarados en la interfaz 
    public Integer insertarInsumoPedidoProveedor(Integer precio,Integer total,Integer iva,Integer idPedidoInsumo,Integer idProveedor){
        return this.procedureQueryInsumoPedidoProveedor.InsertInsumoPedidoProveedor(precio, total, iva, idPedidoInsumo, idProveedor);
    }
    public InsumoPedidoProveedor retornarInsumoPedidoProveedorPorId(Integer idInsumoPedidoProveedor){
        return this.procedureQueryInsumoPedidoProveedor.retornarInsumoPedidoProveedorPorId(idInsumoPedidoProveedor);
    }
    
    
    
   
   
    
}
