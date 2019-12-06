/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IClienteDao;
import com.restaurante.bacon.dao.ProcedureQueryCliente;
import com.restaurante.bacon.dao.ProcedureQueryPagarOnlineCliente;
import com.restaurante.bacon.dto.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro
 */
@Service
public class ClienteService {
    
    @Autowired
    IClienteDao clienteDao;
    
    @Autowired
    ProcedureQueryCliente procedureQuery;
    
    @Autowired
    ProcedureQueryPagarOnlineCliente procedureQueryPagarOnlineCliente;
    
    public Cliente add(Cliente cliente){
        return this.clienteDao.save(cliente);
    }
    
    public Cliente findClienteById(Integer idCliente){
        return this.clienteDao.findByIdCliente(idCliente);
    }
    
    public Integer pagarOnline(Integer idCliente){
        return this.procedureQueryPagarOnlineCliente.pagarOnline(idCliente);
    }
    
}
