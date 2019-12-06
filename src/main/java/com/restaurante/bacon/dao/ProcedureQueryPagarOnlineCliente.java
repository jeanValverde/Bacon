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
 * @author jean
 */
@Repository
public class ProcedureQueryPagarOnlineCliente {
    @Autowired
    private EntityManager em;
    
     public Integer pagarOnline(Integer idCliente){
        
         try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_BOLETA.PR_INSERT_BOLETA_PAGAR_CLIENTE");
            
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ID_CLIENTE", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("V_EXITO", Integer.class,  ParameterMode.OUT);
            
             query.setParameter("P_ID_CLIENTE", idCliente);
             
             query.execute();
             
             Integer resultado = (Integer) query.getOutputParameterValue("V_EXITO");
            // Integer resultadoNumero = Integer.parseInt(resultado) ;
             
             return resultado;
         }catch(Exception ex){
             
             return -1;
         }
         
    }
}
