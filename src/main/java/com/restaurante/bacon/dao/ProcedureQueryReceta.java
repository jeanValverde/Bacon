/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Receta;
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
 * @author jean
 */
@Repository
public class ProcedureQueryReceta {
    
    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    
    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public List<Receta> filtrarRecetaByNombre(String nombreReceta) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_RECETA.FILTRO_NOMBRE_RECETA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_NOMBRE_RECETA", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_RECETAS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_NOMBRE_RECETA", nombreReceta);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Receta> recetas = new ArrayList<Receta>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Receta receta = new Receta();
                receta.setIdReceta(Integer.parseInt(result[0].toString()));
                receta.setNombreReceta(result[1].toString());
                receta.setDescripcionReceta(result[2].toString());
                receta.setDuracionPreparacion(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                receta.setDisponibilidadReceta(BigInteger.valueOf(Integer.parseInt(result[4].toString())));
                receta.setPrecioReceta(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                receta.setCantidadPrepDiariaReceta(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                receta.setFoto(String.valueOf(result[7].toString())); 
                receta.setTipoReceta(result[8].toString());
                CategoriaReceta categoria = new CategoriaReceta();
                categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(result[9].toString()))); 
                categoria.setDescripcionCategoriaReceta(result[10].toString()); 
                categoria.setCantRecetasDia(BigInteger.valueOf(3)); 
                receta.setIdCategoriaReceta(categoria); 
                recetas.add(receta);
            }
            
            return recetas;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
