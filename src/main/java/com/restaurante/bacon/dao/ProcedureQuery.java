/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import static com.restaurante.bacon.dto.Personal.P_ID_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_RUT_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_NOMBRES_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_APE_PATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_APE_MATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_FECHA_NACIMIENTO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CELULAR_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CORREO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CONTRASENA_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ESTADO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ID_ROL;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

import java.math.BigDecimal;
import java.util.Date;
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
public class ProcedureQuery {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;

    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public boolean updateContrasenaPersonal(Integer idPersonal, String contrasena) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("updateContrasena")
                    .setParameter("P_ID_PERSONAL", idPersonal)
                    .setParameter("P_CONTRASENA", contrasena).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //SuppressWarnings suprime las abvertencias de tipo unchecked
    @SuppressWarnings("unchecked")
    public boolean updatePerfilPersonal(BigDecimal idPersonal, String nombres, String apePaterno, String apeMaterno, Date fechaNacimiento, String celular, String correo) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("updatePerfilPersonal")
                    .setParameter("P_ID_PERSONAL", idPersonal)
                    .setParameter("P_NOMBRES_PERSONAL", nombres)
                    .setParameter("P_APE_PATERNO_PERSONAL", apePaterno)
                    .setParameter("P_APE_MATERNO_PERSONAL", apeMaterno)
                    .setParameter("P_FECHA_NACIMIENTO_PERSONAL", fechaNacimiento)
                    .setParameter("P_CELULAR_PERSONAL", celular)
                    .setParameter("P_CORREO_PERSONAL", correo).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean InsertPersonal(String rut, String nombres, String apePaterno, String apeMaterno, Date fecha_nacimiento, String celular, String correo, String contrasena, String estado, String idRol) {
        try {

            em.createNamedStoredProcedureQuery("InsertPersonal")
                    .setParameter(P_RUT_PERSONAL, rut)
                    .setParameter(P_NOMBRES_PERSONAL, nombres)
                    .setParameter(P_APE_PATERNO_PERSONAL, apePaterno)
                    .setParameter(P_APE_MATERNO_PERSONAL, apeMaterno)
                    .setParameter(P_FECHA_NACIMIENTO_PERSONAL, fecha_nacimiento)
                    .setParameter(P_CELULAR_PERSONAL, celular)
                    .setParameter(P_CORREO_PERSONAL, correo)
                    .setParameter(P_CONTRASENA_PERSONAL, contrasena)
                    .setParameter(P_ESTADO_PERSONAL, estado)
                    .setParameter(P_RUT_PERSONAL, rut)
                    .setParameter(P_ID_ROL, idRol).execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

}
