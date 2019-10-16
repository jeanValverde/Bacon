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
import com.restaurante.bacon.dto.CategoriaReceta;

import com.restaurante.bacon.dto.Proveedor;
import static com.restaurante.bacon.dto.Proveedor.P_ID_PROVEEDOR;

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
                    .setParameter(P_DIRECCION_PROVEEDOR, direccion)
                    .setParameter(P_TELEFONO_PROVEEDOR, telefono)
                    .setParameter(P_CONTACTO_VENTA, contacto)
                    .setParameter(P_TIPO_PROVEEDOR, tipo)
                    .setParameter(P_CORREO_PROVEEDOR, correo)
                    .setParameter(P_CELULAR_PROVEEDOR, celular)
                    .setParameter(P_CATEGORIA_PROVEEDOR, categoria).execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    

  
     public boolean UpdateProveedor(BigDecimal idProveedor,String rut, String nombre, String direccion, String telefono, String contacto, String tipo, String correo, Integer celular, String categoria) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("UpdateProveedor")
                    .setParameter(P_ID_PROVEEDOR, idProveedor)
                    .setParameter(P_RUT_PROVEEDOR, rut)
                    .setParameter(P_NOMBRE_PROVEEDOR, nombre)
                    .setParameter(P_DIRECCION_PROVEEDOR, direccion)
                    .setParameter(P_TELEFONO_PROVEEDOR, telefono)
                    .setParameter(P_CONTACTO_VENTA, contacto)
                    .setParameter(P_TIPO_PROVEEDOR, tipo)
                    .setParameter(P_CORREO_PROVEEDOR, correo)
                    .setParameter(P_CELULAR_PROVEEDOR, celular)
                    .setParameter(P_CATEGORIA_PROVEEDOR, categoria).execute();
            
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            return false;
        }
    }


    @SuppressWarnings("unchecked")
    public boolean DeleteProveedorById(Integer idProveedor) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("DeleteProveedorById")
                    .setParameter("P_ID_PROVEEDOR", idProveedor).execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    //Filtro nombre proveedor
    @SuppressWarnings("unchecked")
    public List<Proveedor> filtrarProveedoresByNombre(String nombreProveedor) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PROVEEDOR.FILTRO_NOMBRE_PROVEEDOR");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_NOMBRE_PROVEEDOR", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_PROVEEDOR_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_NOMBRE_PROVEEDOR", nombreProveedor);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Proveedor> proveedores = new ArrayList<Proveedor>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Proveedor proveedor = new Proveedor();
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
               
                
              
                proveedores.add(proveedor);
            }
            
            return proveedores;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    //filtro por Rut
     @SuppressWarnings("unchecked")
    public List<Proveedor> filtrarProveedorByRut(String rut) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PROVEEDOR.FILTRO_RUT_PROVEEDOR");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_RUT_PROVEEDOR", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_PROVEEDOR_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_RUT_PROVEEDOR", rut);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Proveedor> proveedores = new ArrayList<Proveedor>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
               Proveedor proveedor = new Proveedor();
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
                
              
                proveedores.add(proveedor);
            }
            
            return proveedores;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
       //filtro por Categoria
     @SuppressWarnings("unchecked")
    public List<Proveedor> filtrarProveedorByCategoria(String categoria) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PROVEEDOR.FILTRO_CATEGORIA_PROVEEDOR");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_CATEGORIA_PROVEEDOR", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_PROVEEDOR_CURSOR", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_CATEGORIA_PROVEEDOR", categoria);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Proveedor> proveedores = new ArrayList<Proveedor>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
               Proveedor proveedor = new Proveedor();
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
                
              
                proveedores.add(proveedor);
            }
            
            return proveedores;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
    @SuppressWarnings("unchecked")
    public boolean UpdateInsumo(Integer id,String nombre, String descripcion,Integer stock,Integer stockMinimo,Integer stockMaximo,String unidad) {
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
                    .setParameter("P_FOTO_INSUMO", "https://via.placeholder.com/92x92").execute();
            return true;
        } catch (Exception ex) {
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
