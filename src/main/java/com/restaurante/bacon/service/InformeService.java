/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dto.Informe;
import com.restaurante.bacon.dto.procedure.ProcedureQueryInforme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro
 */
@Service
public class InformeService {
    
    @Autowired
    ProcedureQueryInforme procedureQuery;
    
    public List<Informe> listarInforme() {
        return this.procedureQuery.listarInforme();
    }
    
}
