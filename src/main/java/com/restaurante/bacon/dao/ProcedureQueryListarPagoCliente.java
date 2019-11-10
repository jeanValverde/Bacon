/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public class ProcedureQueryListarPagoCliente {
    @Autowired
    private EntityManager em;
    
    public Integer ListarPagoCliente (Integer idCliente){
        
         try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_CLIENTE.PR_TOTAL_A_PAGAR_CLIENTE");
            
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ID_CLIENTE", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("V_TOTAL", Integer.class,  ParameterMode.OUT);
            
             query.setParameter("P_ID_CLIENTE", idCliente);
             
             query.execute();
             
             Integer resultado = (Integer) query.getOutputParameterValue("V_TOTAL");
            // Integer resultadoNumero = Integer.parseInt(resultado) ;
             
             return resultado;
         }catch(Exception ex){
             
             return -1;
         }
         
    }
}
