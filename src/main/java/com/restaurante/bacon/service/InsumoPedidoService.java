/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoPedidoDao;
import com.restaurante.bacon.dao.ProcedureQueryInsumoPedido;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ProcedureQueryInsumoPedido procedureQueryInsumoPedido;
    
    //se implementan los metodos declarados en la interfaz 
   
    
    public List<InsumoPedido> listarInsumosPedidos(){
        return this.procedureQueryInsumoPedido.listarInsumosPedidos();
        //return this.insumoPedidoDao.findAll();
    }
    
   
   
    
}
