/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import static com.restaurante.bacon.dto.Cliente.c_id;
import static com.restaurante.bacon.dto.Cliente.c_nom;
import com.restaurante.bacon.dto.Notificacion;
import com.restaurante.bacon.dto.Rol;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author estebantoledo
 */
@Repository
public class ProcedureSolicitarAsistencia {

    @Autowired
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public boolean InsertAsistencia(Integer idCliente, String nombre) {
        try {
            em.createNamedStoredProcedureQuery("InsertAsistencia")
                    .setParameter(c_id, idCliente)
                    .setParameter(c_nom, nombre).execute();
            return true;

        } catch (Exception ex) {
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    public List<Notificacion> asistenciasByIdCliente(Integer idCliente) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_NOTIFICACION.PR_ASISTENCIAS_BY_ID");
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("c_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_ASISTENCIAS", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("c_id", idCliente);
            //ejecutamos la query
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();

            List<Notificacion> asistencia = new ArrayList<Notificacion>();

            for (Object[] result : results) {

//                System.out.println(result[0].toString());
//                System.out.println(result[1].toString());
//                System.out.println(result[2].toString());
//                System.out.println(result[3].toString());
//                System.out.println(result[4].toString());
//                System.out.println(result[5].toString());

                Notificacion notificacion = new Notificacion();
                notificacion.setIdNotificacion(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                notificacion.setDescripcion(result[1].toString());
                notificacion.setAsunto(result[2].toString());
               
                notificacion.setEstado(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
                
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = result[4].toString();
                Date fecha = formatoFecha.parse(strFecha);
                notificacion.setFechaNotificacion(fecha);
                notificacion.setFechaNotificacion(new Date());
                Rol rol = new Rol();
                rol.setIdRol(Integer.parseInt(result[5].toString()));
                rol.setIdRol(4);
                notificacion.setIdRol(rol);
                asistencia.add(notificacion);
                System.out.println(notificacion.getAsunto());
            }
            return asistencia;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
