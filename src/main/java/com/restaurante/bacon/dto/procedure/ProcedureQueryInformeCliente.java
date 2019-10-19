/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import com.restaurante.bacon.dto.InformeCliente;
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
public class ProcedureQueryInformeCliente {
    
    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    
    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public List<InformeCliente> listarInformeCliente(){
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INFORMES.PR_LISTAR_CLIENTES_ATENDIDOS");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_CLIENTES_ATENDIDOS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
           

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<InformeCliente> informesClientes = new ArrayList<InformeCliente>();
            
            for (Object[] result : results) {
                
                InformeCliente informeCliente = new InformeCliente();
                
                informeCliente.setNombre(result[0].toString());
                informeCliente.setDescripcion_estado((result[1].toString()));
                informesClientes.add(informeCliente);
            }
            
            return informesClientes;
            
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
}
