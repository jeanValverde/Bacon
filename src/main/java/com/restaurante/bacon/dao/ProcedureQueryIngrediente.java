/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Receta;
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
 * @author jean
 */
@Repository
public class ProcedureQueryIngrediente {
    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    
    
    
    
    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public List<Ingrediente> filtrarInsumosByIdReceta(Integer idReceta) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INGREDIENTE.FILTRO_INGREDIENTE_RECETA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ID_RECETA", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INGREDIENTES_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_ID_RECETA", idReceta);

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));  
                insumo.setNombreInsumo(result[1].toString());  
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.valueOf(result[3].toString()))); 
                insumo.setUnidadMedidaInsumo(result[4].toString()); 
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.valueOf(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.valueOf(result[6].toString()))); 
                insumo.setFotoInsumo(result[7].toString()); 
                
                Ingrediente ingrediente = new Ingrediente();
                
                ingrediente.setIdIngrediente(Integer.parseInt(result[8].toString())); 
                Receta receta = new Receta();
                
                receta.setIdReceta(Integer.parseInt(result[9].toString())); 
                ingrediente.setIdReceta(receta); 
                ingrediente.setIdInsumo(insumo); 
                ingrediente.setCantidad(Double.parseDouble(result[10].toString()));
                
                ingredientes.add(ingrediente);
            }
            
            return ingredientes;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
