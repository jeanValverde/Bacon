/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Proveedor;
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
public class ProcedureQueryProveedor {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;

    
   
    @SuppressWarnings("unchecked")
    public List<Insumo> listarProveedores() {
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
    public Proveedor retornarProveedor(Integer id) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PROVEEDOR.PR_PROVEEDOR");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ID_PROVEEDOR", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_PROVEEDOR_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_ID_PROVEEDOR", id);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            Proveedor proveedor = new Proveedor();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                proveedor.setIdProveedor(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                proveedor.setRutProveedor(result[1].toString());
                proveedor.setNombreProveedor(result[2].toString());
                proveedor.setDireccionProveedor(result[3].toString());
                proveedor.setTelefonoProveedor(result[4].toString());
                proveedor.setContactoVenta(result[5].toString());
                proveedor.setTipoProveedor(result[6].toString());
                proveedor.setCorreoProveedor(result[7].toString());
                proveedor.setCelularProveedor(Integer.parseInt(result[8].toString()));
                proveedor.setCategoriaProveedor(result[9].toString());
              
            }
            
            return proveedor;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    


}
