/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.RecetaOrdenada;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Felipe
 */
public class ProcedureQueryRecetaOrdenada {
    
    
    @Autowired
    private EntityManager em;
    
//        @SuppressWarnings("unchecked")
//     public List<RecetaOrdenada> recetaByOrden(Integer idOrden) {
//        try {
//            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_ORDEN.PR_LIST_RECTAS_ORDENS_BY_ORDEN");
//
//            // Registrar los parámetros de entrada y salida
//            query.registerStoredProcedureParameter("P_ID_ORDEN", Integer.class, ParameterMode.IN);
//            query.registerStoredProcedureParameter("CURSOR_ORDENES", Class.class, ParameterMode.REF_CURSOR);
//            
//            // Configuramos el valor de entrada
//            query.setParameter("P_ID_ORDEN", idOrden);
//
//            
//            query.execute();
//
//            // Obtenemos el resultado del cursos en una lista
//            List<Object[]> results = query.getResultList();
//            List<RecetaOrdenada> recetasOrdenadas = new ArrayList<RecetaOrdenada>();
//            
//            
//            // Recorremos la lista con map y devolvemos un List<BusinessObject>
//            for (Object[] result : results) {
//                RecetaOrdenada recetaOrdenada = new RecetaOrdenada();
//                recetaOrdenada.setIdOrden((Integer.parseInt(result[0].toString())));
////                ordenes.setDescripcion(result[1].toString());
////                ordenes.setSubTotal((Integer.parseInt(result[2].toString())));
////                ordenes.setIva((Integer.parseInt(result[3].toString())));
////                ordenes.setTotalOrden((Integer.parseInt(result[4].toString())));
////                ordenes.setTiempoPreparacion((Integer.parseInt(result[5].toString())));
////                ordenes.setMotivoAnulacion(result[6].toString());
////                ordenes.setTipoOrden(BigInteger.valueOf(Integer.parseInt(result[8].toString())));
//                
//                Cliente clientes = new Cliente();
//                clientes.setNombre(result[1].toString());
////                clientes.setIdCliente((BigDecimal.valueOf(Integer.parseInt(result[8].toString()))));
////                
////                EstadoOrden estadoOrden = new EstadoOrden();
////                estadoOrden.setIdEstadoOrden((BigDecimal.valueOf(Integer.parseInt(result[9].toString()))));
////                
////              
//                
//              
//                orden.add(ordenes); //.add(insumo_pedido);
//            }
//            
//            return orden;
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage().toString());
//            ex.printStackTrace();
//            return null;
//        }
//    }
}
