/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Receta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean Se deben llamar a todos los procedimientos creados en el package
 * procedure
 */
@Repository
public class ProcedureQueryInsumo {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public boolean InsertInsumo(String nombre, String descripcion,BigInteger stock,BigInteger stockMinimo,BigInteger stockMaximo,String unidad,String url) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("InsertInsumo")
                    .setParameter("P_NOMBRE_INSUMO", nombre)
                    .setParameter("P_DESCRIPCION_INSUMO", descripcion)
                    .setParameter("P_STOCK_INSUMO", stock)
                    .setParameter("P_UNIDAD_MEDIDA_INSUMO", unidad)
                    .setParameter("P_MINIMO_STOCK_INSUMO", stockMinimo)
                    .setParameter("P_MAXIMO_STOCK_INSUMO", stockMaximo)
                    .setParameter("P_FOTO_INSUMO", url).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public boolean UpdateInsumo(Integer id,String nombre, String descripcion,BigInteger stock,BigInteger stockMinimo,BigInteger stockMaximo,String unidad,String url) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("UpdateInsumo")
                    .setParameter("P_ID_INSUMO", id)
                    .setParameter("P_NOMBRE_INSUMO", nombre)
                    .setParameter("P_DESCRIPCION_INSUMO", descripcion)
                    .setParameter("P_STOCK_INSUMO", stock)
                    .setParameter("P_UNIDAD_MEDIDA_INSUMO", unidad)
                    .setParameter("P_MINIMO_STOCK_INSUMO", stockMinimo)
                    .setParameter("P_MAXIMO_STOCK_INSUMO", stockMaximo)
                    .setParameter("P_FOTO_INSUMO", url).execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public boolean DeleteInsumo(Integer id) {
        try {
             StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.PR_ELIMINAR_INSUMO_POR_ID");
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            query.registerStoredProcedureParameter("P_ID_INSUMO",Integer.class,ParameterMode.IN);
                    
            query.setParameter("P_ID_INSUMO", id);
            query.execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Insumo> listarInsumos() {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.PR_LISTAR_INSUMOS");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("cursor_insumos", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Insumo> insumos = new ArrayList<Insumo>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));
                insumo.setNombreInsumo(result[1].toString());
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                insumo.setUnidadMedidaInsumo(result[4].toString());
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                insumo.setFotoInsumo(result[7].toString());
                
              
                insumos.add(insumo);
            }
            
            return insumos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Insumo> filtrarInsumos(String filtro) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.FILTRO_INSUMO");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_FILTRO", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMOS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_FILTRO", filtro);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Insumo> insumos = new ArrayList<Insumo>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));
                insumo.setNombreInsumo(result[1].toString());
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                insumo.setUnidadMedidaInsumo(result[4].toString());
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                insumo.setFotoInsumo(result[7].toString());
                
              
                insumos.add(insumo);
            }
            
            return insumos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Insumo> filtrarInsumosByNombre(String nombre) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.FILTRO_NOMBRE_INSUMO");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_NOMBRE_INSUMO", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMOS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_NOMBRE_INSUMO", nombre);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Insumo> insumos = new ArrayList<Insumo>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));
                insumo.setNombreInsumo(result[1].toString());
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                insumo.setUnidadMedidaInsumo(result[4].toString());
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                insumo.setFotoInsumo(result[7].toString());
                
              
                insumos.add(insumo);
            }
            
            return insumos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Insumo> filtrarInsumosByStock(BigInteger stock) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.FILTRO_STOCK_INSUMO");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_STOCK_INSUMO", BigInteger.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMOS_CURSOR", Class.class, ParameterMode.REF_CURSOR);
            
            // Configuramos el valor de entrada
            query.setParameter("P_STOCK_INSUMO", stock);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Insumo> insumos = new ArrayList<Insumo>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));
                insumo.setNombreInsumo(result[1].toString());
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                insumo.setUnidadMedidaInsumo(result[4].toString());
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                insumo.setFotoInsumo(result[7].toString());
                
              
                insumos.add(insumo);
            }
            
            return insumos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Insumo> filtrarInsumosByUnidadMedida(String unidad) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO.FILTRO_UNIDAD_INSUMO");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_UNIDAD_INSUMO", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMOS_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_UNIDAD_INSUMO", unidad);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Insumo> insumos = new ArrayList<Insumo>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[0].toString()));
                insumo.setNombreInsumo(result[1].toString());
                insumo.setDescripcionInsumo(result[2].toString());
                insumo.setStockInsumo(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                insumo.setUnidadMedidaInsumo(result[4].toString());
                insumo.setMinimoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
                insumo.setMaximoStockInsumo(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
                insumo.setFotoInsumo(result[7].toString());
                
              
                insumos.add(insumo);
            }
            
            return insumos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    


}
