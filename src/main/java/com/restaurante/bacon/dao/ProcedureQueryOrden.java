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
     public List<Orden> listarOrdenes(BigDecimal idCliente) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_ORDEN.PR_LISTAR_ORDENES");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("p_id_cliente", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("cursor_ordenes", Class.class, ParameterMode.REF_CURSOR);
            
            // Configuramos el valor de entrada
            query.setParameter("p_id_cliente", idCliente);

            
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Orden> ordenes = new ArrayList<Orden>();
            
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Orden orden = new Orden();
                orden.setIdOrden((Integer.parseInt(result[0].toString())));
                orden.setDescripcion(result[1].toString());
                orden.setSubTotal((Integer.parseInt(result[2].toString())));
                orden.setIva((Integer.parseInt(result[3].toString())));
                orden.setTotalOrden((Integer.parseInt(result[4].toString())));
                orden.setTiempoPreparacion((Integer.parseInt(result[5].toString())));
                orden.setMotivoAnulacion(result[6].toString());
                orden.setTipoOrden(BigInteger.valueOf(Integer.parseInt(result[8].toString())));
                
                //Cliente clientes = new Cliente();
                //clientes.setNombre(result[9].toString());
//                clientes.setIdCliente((BigDecimal.valueOf(Integer.parseInt(result[8].toString()))));
//                
//                EstadoOrden estadoOrden = new EstadoOrden();
//                estadoOrden.setIdEstadoOrden((BigDecimal.valueOf(Integer.parseInt(result[9].toString()))));
//                
//              
                
              
                ordenes.add(orden); //.add(insumo_pedido);
            }
            
            return ordenes;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
     
     
     
}
