/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.service.PagarOrdenService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
public class ProcedureQueryOrden {
    
    @Autowired
    private EntityManager em;
    
    @Autowired
    private PagarOrdenService pagarOrden;
    
    @SuppressWarnings("unchecked")
     public List<Orden> listarInsumosPedidos() {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_ORDEN.PR_LISTAR_ORDENES");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("cursor_ordenes", Class.class, ParameterMode.REF_CURSOR);

            
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Orden> orden = new ArrayList<Orden>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Orden ordenes = new Orden();
                ordenes.setIdOrden(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                ordenes.setDescripcion(String.valueOf(Integer.parseInt(result[1].toString())));
                ordenes.setSubTotal(BigInteger.valueOf(Integer.parseInt(result[0].toString())));
                ordenes.setIva(BigInteger.valueOf(Integer.parseInt(result[0].toString())));
                ordenes.setTotalOrden(BigInteger.valueOf(Integer.parseInt(result[0].toString())));
                ordenes.setTiempoPreparacion(BigInteger.valueOf(Integer.parseInt(result[0].toString())));
                ordenes.setMotivoAnulacion(String.valueOf(Integer.parseInt(result[1].toString())));
                
              
                
              
                orden.add(ordenes); //.add(insumo_pedido);
            }
            
            return orden;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
}
