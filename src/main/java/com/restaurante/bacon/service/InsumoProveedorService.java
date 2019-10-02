/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoPedidoDao;
import com.restaurante.bacon.dao.IInsumoProveedorDao;
import com.restaurante.bacon.dao.InsumoPedidoProveedorDAO;
import com.restaurante.bacon.dao.ProcedureQueryInsumoPedido;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
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
public class InsumoProveedorService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    IInsumoProveedorDao IInsumoProveedorDao;
    @Autowired
    InsumoProveedorService insumoProveedor;
    @Autowired
    ProcedureQueryInsumoPedido procedureQueryInsumoPedido;
    
    //se implementan los metodos declarados en la interfaz 
   
    
    public InsumoProveedor retornarInsumoProveedor(){
        
       InsumoProveedor insumoProveedor = new InsumoProveedor();
       
       return insumoProveedor;
        //return this.insumoPedidoDao.findAll();
    }
    
   
   
    
}
