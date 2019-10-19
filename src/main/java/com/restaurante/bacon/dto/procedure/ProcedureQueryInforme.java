/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import com.restaurante.bacon.dto.Informe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejandro
 */
@Repository
public class ProcedureQueryInforme {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    
    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public List<Informe> listarInforme(){
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INFORMES.PR_LISTAR_RECETAS_VENDIDAS");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_RECETAS_VENDIDAS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
           

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Informe> informes = new ArrayList<Informe>();
            
            for (Object[] result : results) {
                Informe informe = new Informe();
                informe.setNombre_receta(result[0].toString());
                informe.setCantidad(Integer.parseInt(result[1].toString()));
                informe.setValor(Integer.parseInt(result[2].toString()));
                informe.setTotal(Integer.parseInt(result[3].toString()));
                informes.add(informe);
            }
            
            return informes;
            
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
