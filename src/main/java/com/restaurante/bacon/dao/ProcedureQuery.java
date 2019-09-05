/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import static com.restaurante.bacon.dto.Proveedor.P_CATEGORIA_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CELULAR_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CONTACTO_VENTA;
import static com.restaurante.bacon.dto.Proveedor.P_CORREO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_DIRECCION_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_NOMBRE_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_RUT_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TELEFONO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TIPO_PROVEEDOR;
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
    public boolean InsertProveedor(String rut, String nombre, String direccion, String telefono, String contacto, String tipo, String correo, Integer celular, String categoria) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("InsertProveedor")
                    .setParameter(P_RUT_PROVEEDOR, rut)
                    .setParameter(P_NOMBRE_PROVEEDOR, nombre)
                    .setParameter(P_DIRECCION_PROVEEDOR,direccion)
                    .setParameter(P_TELEFONO_PROVEEDOR,telefono)
                    .setParameter(P_CONTACTO_VENTA,contacto)
                    .setParameter(P_TIPO_PROVEEDOR,tipo)
                    .setParameter(P_CORREO_PROVEEDOR, correo)
                    .setParameter(P_CELULAR_PROVEEDOR, celular)
                    .setParameter(P_CATEGORIA_PROVEEDOR, categoria).execute();
                    
                    
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
