/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Orden;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.dto.RecetaOrdenada;
import com.restaurante.bacon.service.RecetaOrdenadaService;
import com.restaurante.bacon.service.RecetaService;
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
public class ProcedureQueryRecetaOrdenada {
    
    
    @Autowired
    private EntityManager em;
    
   @Autowired
    private RecetaOrdenadaService recetaOrdenadaService;
   
    @Autowired
    private RecetaService recetaservice;
    
    @Autowired
    private ProcedureQueryReceta procedureQueryReceta;
            
    
        @SuppressWarnings("unchecked")
     public List<RecetaOrdenada> listarRecetaByOrden(Integer idOrden) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_ORDEN.PR_LIST_RECTAS_ORDENS_BY_ORDEN");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ID_ORDEN", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CURSOR_ORDENES", Class.class, ParameterMode.REF_CURSOR);
            
            // Configuramos el valor de entrada
            query.setParameter("P_ID_ORDEN", idOrden);

            
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<RecetaOrdenada> recetasOrdenadas = new ArrayList<RecetaOrdenada>();
            
            
            
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                RecetaOrdenada recetaOrdenada = new RecetaOrdenada();
                Receta receta = new Receta();
               
               
                recetaOrdenada.setIdReceta(receta);
                recetaOrdenada.setIdRecetaOrdenada((Integer.parseInt(result[0].toString())));
                receta.setNombreReceta(result[1].toString());
                recetaOrdenada.setCantidad((Integer.parseInt(result[2].toString())));
                
                 receta = this.procedureQueryReceta.retornarRecetaById((Integer.parseInt(result[3].toString())));
                recetaOrdenada.setValor((Integer.parseInt(result[4].toString())));
                recetaOrdenada.setIdReceta(receta);
                
                
                
                receta.setPrecioReceta(BigInteger.valueOf(Integer.parseInt(result[4].toString())));
                
                System.out.println("Nombre receta: " + recetaOrdenada.getIdReceta().getNombreReceta() );
                
//                ordenes.setDescripcion(result[1].toString());
//                ordenes.setSubTotal((Integer.parseInt(result[2].toString())));
//                ordenes.setIva((Integer.parseInt(result[3].toString())));
//                ordenes.setTotalOrden((Integer.parseInt(result[4].toString())));
//                ordenes.setTiempoPreparacion((Integer.parseInt(result[5].toString())));
//                ordenes.setMotivoAnulacion(result[6].toString());
//                ordenes.setTipoOrden(BigInteger.valueOf(Integer.parseInt(result[8].toString())));
                
           
//                clientes.setIdCliente((BigDecimal.valueOf(Integer.parseInt(result[8].toString()))));
//                
//                EstadoOrden estadoOrden = new EstadoOrden();
//                estadoOrden.setIdEstadoOrden((BigDecimal.valueOf(Integer.parseInt(result[9].toString()))));
//                
                  
//              
                
              
                recetasOrdenadas.add(recetaOrdenada); //.add(insumo_pedido);
            }
            
            return recetasOrdenadas;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
     
     
}
