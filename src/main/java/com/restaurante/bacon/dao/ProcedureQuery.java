/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean 
 * Se deben llamar a todos los procedimientos creados en el package procedure 
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
                    .setParameter("P_NOMBRE", nombres)
                    .setParameter("P_APE_PATERNO", apePaterno)
                    .setParameter("P_APE_MATERNO", apeMaterno)
                    .setParameter("P_FECHA_NACIMIENTO", fechaNacimiento)
                    .setParameter("P_CELULAR", celular)
                    .setParameter("P_CORREO", correo).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    public boolean InsertInsumo(String nombre, String descripcion,Integer stock,Integer stockMinimo,Integer stockMaximo,String unidad) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("InsertInsumo")
                    .setParameter("P_NOMBRE_INSUMO", nombre)
                    .setParameter("P_DESCRIPCION_INSUMO", descripcion)
                    .setParameter("P_STOCK_INSUMO", stock)
                    .setParameter("P_UNIDAD_MEDIDA_INSUMO", unidad)
                    .setParameter("P_MINIMO_STOCK_INSUMO", stockMinimo)
                    .setParameter("P_MAXIMO_STOCK_INSUMO", stockMaximo)
                    .setParameter("P_FOTO_INSUMO", "https://via.placeholder.com/92x92").execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
