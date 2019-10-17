/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Mesa;
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
 * @author Alejandro
 */
@Repository
public class ProcedureQueryMesa {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;

    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public boolean InsertMesa(Integer numero, Integer cantidadAsientos, Integer estado) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("InsertMesa")
                    .setParameter("P_NUMERO_MESA", numero)
                    .setParameter("P_CANTIDAD_ASIENTOS_MESA", cantidadAsientos)
                    .setParameter("P_ESTADO_MESA", estado).execute();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean UpdateMesa(Integer id, BigInteger numero, BigInteger cantidadAsientos, BigInteger estado) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("UpdateMesa")
                    .setParameter("P_ID_MESA", id)
                    .setParameter("P_NUMERO_MESA", numero)
                    .setParameter("P_CANTIDAD_ASIENTOS_MESA", cantidadAsientos)
                    .setParameter("P_ESTADO_MESA", estado).execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mesa> filtrarRecetaByNumero(BigInteger numeroMesa) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_MESA.FILTRO_NUMERO_MESA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_NUMERO_MESA", BigInteger.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_MESA_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_NUMERO_MESA", numeroMesa);

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Mesa> mesas = new ArrayList<Mesa>();

            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(Integer.parseInt(result[0].toString()));
                mesa.setNumeroMesa(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                mesa.setCantidadAsientosMesa(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                mesa.setEstadoMesa(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                mesas.add(mesa);
            }

            return mesas;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mesa> filtrarRecetaByAsientos(BigInteger cantidadAsientosMesa) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_MESA.FILTRO_CANTIDAD_ASIENTOS_MESA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_CANTIDAD_ASIENTOS_MESA", BigInteger.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_MESA_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_CANTIDAD_ASIENTOS_MESA", cantidadAsientosMesa);

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Mesa> mesas = new ArrayList<Mesa>();

            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(Integer.parseInt(result[0].toString()));
                mesa.setNumeroMesa(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                mesa.setCantidadAsientosMesa(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                mesa.setEstadoMesa(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                mesas.add(mesa);
            }

            return mesas;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mesa> filtrarRecetaByEstado(BigInteger estadoMesa) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_MESA.FILTRO_ESTADO_MESA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_ESTADO_MESA", BigInteger.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_MESA_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_ESTADO_MESA", estadoMesa);

            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Mesa> mesas = new ArrayList<Mesa>();

            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(Integer.parseInt(result[0].toString()));
                mesa.setNumeroMesa(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                mesa.setCantidadAsientosMesa(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                mesa.setEstadoMesa(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                mesas.add(mesa);
            }

            return mesas;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mesa> EstadoPedido() {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_MESA.ESTADO_MESA_PEDIDA");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_MESA_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            //ejecutamos la query
            query.execute();
            
            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Mesa> mesas = new ArrayList<Mesa>();
            
            for (Object[] result : results) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(Integer.parseInt(result[0].toString()));
                mesa.setNumeroMesa(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                mesa.setCantidadAsientosMesa(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                mesa.setEstadoMesa(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                mesas.add(mesa);
            }

            return mesas;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
