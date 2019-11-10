/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Receta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.*;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_INSUMO_PROVEEDOR;
import static com.restaurante.bacon.dto.InsumoProveedor.I_PRECIO;
import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_INSUMO;
import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_PROVEEDOR;

/**
 *
 * @author estebantoledo
 */
@Repository
public class ProcedureQueryInsumoProveedor {

    @Autowired
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public boolean InsertInsumoProveedor(Integer precio, Integer idInsumo, Integer idProveedor) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption
            em.createNamedStoredProcedureQuery("InsertInsumoProveedor")
                    .setParameter(I_PRECIO, precio)
                    .setParameter(I_ID_INSUMO, idInsumo)
                    .setParameter(I_ID_PROVEEDOR, idProveedor)
                    .execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean UpdateInsumoProveedor(Integer idInsumoProveedor, BigInteger precio) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption
            em.createNamedStoredProcedureQuery("UpdateInsumoProveedor")
                    .setParameter(I_ID_INSUMO_PROVEEDOR, idInsumoProveedor)
                    .setParameter(I_PRECIO, precio)
                    .execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean DeleteInsumoProveedor(Integer idInsumoProveedor) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption
            em.createNamedStoredProcedureQuery("DeleteInsumoProveedor")
                    .setParameter(I_ID_INSUMO_PROVEEDOR, idInsumoProveedor).execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean UpdateInsumo(Integer id, String nombre, String descripcion, BigInteger stock, BigInteger stockMinimo, BigInteger stockMaximo, String unidad, String url) {
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
            //si no se realiza el procedimiento adecuadamente cae en una exeption
            em.createNamedStoredProcedureQuery("DeleteInsumo")
                    .setParameter("P_ID_INSUMO", id).execute();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<InsumoProveedor> insumosByIdProveedor(BigDecimal idProveedor) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PROVEEDOR.INSUMOS_BY_ID");
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("I_ID_PROVEEDOR", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMOS_PROVEEDOR_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("I_ID_PROVEEDOR", idProveedor);
            //ejecutamos la query
            query
                    .execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();

            List<InsumoProveedor> insumosProveedor = new ArrayList<InsumoProveedor>();

            for (Object[] result : results) {
                InsumoProveedor insumoProveedor = new InsumoProveedor();
                insumoProveedor.setIdInsumoProveedor(Integer.parseInt(result[0].toString()));
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[1].toString()));
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(BigDecimal.valueOf(Integer.parseInt(result[2].toString())));
                insumo.setNombreInsumo(result[3].toString());
                insumoProveedor.setPrecio(BigInteger.valueOf(Integer.parseInt(result[4].toString())));

                insumoProveedor.setIdProveedor(proveedor);
                insumo.setFotoInsumo(result[5].toString());
                insumoProveedor.setIdInsumo(insumo);
                insumosProveedor.add(insumoProveedor);

            }
            return insumosProveedor;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public List<InsumoProveedor> buscarIdInsumoProveedor(Integer idInsumoProveedor
    ) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PROVEEDOR.BUSCAR_ID_INSUMO_PROVEEDOR");
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("I_ID_INSUMO_PROVEEDOR", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_INSUMO_PROVEEDORES_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("I_ID_INSUMO_PROVEEDOR", idInsumoProveedor);

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<InsumoProveedor> insumosProveedor = new ArrayList<InsumoProveedor>();

            for (Object[] result : results) {

                InsumoProveedor insumoProveedor = new InsumoProveedor();
                insumoProveedor.setIdInsumoProveedor(Integer.parseInt(result[0].toString()));
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(result[1].toString()));
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(BigDecimal.valueOf(Integer.parseInt(result[2].toString())));
                insumo.setNombreInsumo(result[3].toString());
                insumoProveedor.setPrecio(BigInteger.valueOf(Integer.parseInt(result[4].toString())));

                insumoProveedor.setIdProveedor(proveedor);
                insumo.setFotoInsumo(result[5].toString());
                insumoProveedor.setIdInsumo(insumo);
                

                break;

            }
            return insumosProveedor;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}