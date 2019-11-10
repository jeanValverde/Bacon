/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IPagarOrdenDao;
import com.restaurante.bacon.dao.ProcedureQueryListarPagoCliente;
import com.restaurante.bacon.dao.ProcedureQueryOrden;
import com.restaurante.bacon.dto.Orden;
import org.springframework.stereotype.Controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe
 */
@Service
public class PagarOrdenService {

    @Autowired
    IPagarOrdenDao pagarOrdenDao;

    @Autowired
    ProcedureQueryOrden procedureQueryOrden;
    
    @Autowired
    ProcedureQueryListarPagoCliente procedureQueryPagoCliente;

    public List<Orden> listarOrdenes(BigDecimal idCliente) {
        return this.procedureQueryOrden.listarOrdenes(idCliente);
    }

    public Orden retornarOrdenById(Integer idOrden) {
        Optional<Orden> optinalEntity = pagarOrdenDao.findById(idOrden);
        Orden orden = optinalEntity.get();
        return orden;
    }
    
    public Integer retornarPago (Integer idCliente){
        return this.procedureQueryPagoCliente.ListarPagoCliente(idCliente);
    }
    
    
}
