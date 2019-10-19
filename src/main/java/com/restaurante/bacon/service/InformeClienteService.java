/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dto.InformeCliente;
import com.restaurante.bacon.dto.procedure.ProcedureQueryInformeCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro
 */
@Service
public class InformeClienteService {
    
    @Autowired
    ProcedureQueryInformeCliente procedureQuery;
    
    public List<InformeCliente> listarInformeCliente() {
        return this.procedureQuery.listarInformeCliente();
    }
    
}
