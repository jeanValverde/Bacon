/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejandro
 */
@Repository
public class ProcedureQueryMesa {
    
    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    
   @SuppressWarnings("unchecked")
   public boolean InsertMesa(Integer numero, Integer cantidadAsientos, Integer estado){
       try {
           //si no se realiza el procedimiento adecuadamente cae en una exeption 
           em.createNamedStoredProcedureQuery("InsertMesa")
                   .setParameter("P_NUMERO_MESA", numero)
                   .setParameter("P_CANTIDAD_ASIENTOS_MESA", cantidadAsientos)
                   .setParameter("P_ESTADO_MESA", estado).execute();
           return true;
       } catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
   }
}
