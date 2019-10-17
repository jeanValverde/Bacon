/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import java.util.Date;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejandro
 */
@Repository
public class ProcedureQueryCliente {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;

    //SuppressWarnings suprime las abvertencias de tipo unchecked
    
    @SuppressWarnings("unchecked")
    public boolean InsertCliente(String nombre, Integer idMesa) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("InsertCliente")
                    .setParameter("P_NOMBRE", nombre)
                    .setParameter("P_ID_MESA", idMesa);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
