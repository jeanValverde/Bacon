/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Datos;
import java.util.ArrayList;
import java.util.List;
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
public class DatosProcecureCredenciales {
    
    
    @Autowired
    private EntityManager em;

    
    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public boolean updateDatos(Integer id, String clave) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("insertDatos")
                    .setParameter("P_ID", id)
                    .setParameter("P_CLAVE", clave).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Datos>  getDatos() {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PR_SELECT_DATOS");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("CURSOR_DATOS", Class.class, ParameterMode.REF_CURSOR);


            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Datos> datos = new ArrayList<Datos>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                
                Datos dato = new Datos();
                
                dato.setTipo(result[0].toString()); 
                dato.setClave(result[1].toString());
                dato.setLlave(result[2].toString());
              
                datos.add(dato);
            }
            
            return datos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    
}
