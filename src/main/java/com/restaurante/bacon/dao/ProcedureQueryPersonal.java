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
import static com.restaurante.bacon.dto.Personal.P_ESTADO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ID_ROL;
import static com.restaurante.bacon.dto.Proveedor.P_CATEGORIA_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CELULAR_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CONTACTO_VENTA;
import static com.restaurante.bacon.dto.Proveedor.P_CORREO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_DIRECCION_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_NOMBRE_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_RUT_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TELEFONO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TIPO_PROVEEDOR;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Proveedor;
import static com.restaurante.bacon.dto.Proveedor.P_ID_PROVEEDOR;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
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
public class ProcedureQueryPersonal {
    
     @Autowired
    private PersonalService personalServicio;

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
    public boolean modificarPersonal(BigDecimal idPersonal,String rut, String nombres, String apePaterno, String apeMaterno, Date fechaNacimiento, String celular, String correo, BigInteger estado,Integer idRol) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("modificarPersonal")
                    .setParameter(P_ID_PERSONAL, idPersonal)
                    .setParameter(P_RUT_PERSONAL, rut)
                    .setParameter(P_NOMBRES_PERSONAL, nombres)
                    .setParameter(P_APE_PATERNO_PERSONAL, apePaterno)
                    .setParameter(P_APE_MATERNO_PERSONAL, apeMaterno)
                    .setParameter(P_FECHA_NACIMIENTO_PERSONAL, fechaNacimiento)
                    .setParameter(P_CELULAR_PERSONAL, celular)
                    .setParameter(P_CORREO_PERSONAL, correo)
                    .setParameter(P_ESTADO_PERSONAL, estado)
                    .setParameter(P_ID_ROL, idRol).execute();

         return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
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

    public boolean UpdateProveedor(BigDecimal idProveedor, String rut, String nombre, String direccion, String telefono, String contacto, String tipo, String correo, Integer celular, String categoria) {
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
    public boolean DeleteProveedorById(Integer id) {
        try {
            //si no se realiza el procedimiento adecuadamente cae en una exeption 
            em.createNamedStoredProcedureQuery("DeleteProveedorById")
                    .setParameter("P_ID_PROVEEDOR", id).execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Proveedor> filtrarProveedorByNombre(String nombreProveedor) {
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
                proveedor.setTipoProveedor(result[5].toString());
                proveedor.setCorreoProveedor(result[6].toString());
                proveedor.setCelularProveedor(Integer.parseInt(result[7].toString()));
                proveedor.setCategoriaProveedor(result[8].toString());

                proveedores.add(proveedor);
            }

            return proveedores;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean DeletePersonalById(BigInteger id) {
        try {
            em.createNamedStoredProcedureQuery("DeletePersonal")
                    .setParameter("P_ID_PERSONAL", id).execute();
            return true;
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            return false;
        }

    }





//@SuppressWarnings("unchecked")
//    public boolean InsertInsumo(String nombre, String descripcion,Integer stock,Integer stockMinimo,Integer stockMaximo,String unidad) {
//        try {
//            //si no se realiza el procedimiento adecuadamente cae en una exeption 
//            em.createNamedStoredProcedureQuery("InsertInsumo")
//                    .setParameter("P_NOMBRE_INSUMO", nombre)
//                    .setParameter("P_DESCRIPCION_INSUMO", descripcion)
//                    .setParameter("P_STOCK_INSUMO", stock)
//                    .setParameter("P_UNIDAD_MEDIDA_INSUMO", unidad)
//                    .setParameter("P_MINIMO_STOCK_INSUMO", stockMinimo)
//                    .setParameter("P_MAXIMO_STOCK_INSUMO", stockMaximo)
//                    .setParameter("P_FOTO_INSUMO", "https://via.placeholder.com/92x92").execute();
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//    @SuppressWarnings("unchecked")
//    public boolean UpdateInsumo(Integer id,String nombre, String descripcion,Integer stock,Integer stockMinimo,Integer stockMaximo,String unidad) {
//        try {
//            //si no se realiza el procedimiento adecuadamente cae en una exeption 
//            em.createNamedStoredProcedureQuery("UpdateInsumo")
//                    .setParameter("P_ID_INSUMO", id)
//                    .setParameter("P_NOMBRE_INSUMO", nombre)
//                    .setParameter("P_DESCRIPCION_INSUMO", descripcion)
//                    .setParameter("P_STOCK_INSUMO", stock)
//                    .setParameter("P_UNIDAD_MEDIDA_INSUMO", unidad)
//                    .setParameter("P_MINIMO_STOCK_INSUMO", stockMinimo)
//                    .setParameter("P_MAXIMO_STOCK_INSUMO", stockMaximo)
//                    .setParameter("P_FOTO_INSUMO", "https://via.placeholder.com/92x92").execute();
//>>>>>>> Felipe
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//    Rol r= new Rol();
    
        @SuppressWarnings("unchecked")
        public boolean InsertPersonal(String rutPersonal, String nombresPersonal, String apePaternoPersonal, String apeMaternoPersonal, Date fechaNacimientoPersonal, String celularPersonal, String correoPersonal, String contrasenaPersonal,BigInteger estado,Integer rolpersonal) {
        try {


            em.createNamedStoredProcedureQuery("InsertPersonal")
                    .setParameter("P_RUT_PERSONAL_IN", rutPersonal)
                    .setParameter("P_NOMBRES_PERSONA_INL", nombresPersonal)
                    .setParameter("P_APE_PATERNO_IN", apePaternoPersonal)
                    .setParameter("P_APE_MATERNO_IN", apeMaternoPersonal)
                    .setParameter("P_FECHA_NACIMIENTO_IN", fechaNacimientoPersonal)
                    .setParameter("P_CELULAR_IN", celularPersonal)
                    .setParameter("P_CORREO_IN", correoPersonal)
                    .setParameter("P_CONTRASENA_IN", contrasenaPersonal)
                    .setParameter("P_ESTADO_PERSONAL_IN", estado)
                    .setParameter("P_ROL_PERSONAL_IN", rolpersonal)
                    .execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }
        
        
     @SuppressWarnings("unchecked")
    public List<Personal> filtrarPersonalByRut(String rut) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PERSONAL.PR_BUSCAR_PERSONAL_POR_RUT");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("P_RUT_PERSONAL", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CURSOR_PERSONAL", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("P_RUT_PERSONAL", rut);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Personal> Personals = new ArrayList<Personal>();
           
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
               
                Personal personal = new Personal();
                personal.setIdPersonal(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                personal.setRutPersonal(result[1].toString());
                personal.setNombresPersonal(result[2].toString());
                personal.setApePaternoPersonal(result[3].toString());
                personal.setApeMaternoPersonal(result[4].toString());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = result[5].toString();
                Date fecha = formatoFecha.parse(strFecha);
                personal.setFechaNacimientoPersonal(fecha);
//                
                personal.setCelularPersonal(result[6].toString());
                personal.setCorreoPersonal(result[7].toString());
                personal.setEstadoPersonal(BigInteger.valueOf(Integer.parseInt(result[8].toString())));
//                personal.setContrasenaPersonal(result[3].toString());
                
                Rol rol =new Rol();
                personal.setIdRol(rol);
//                rol=this.personalServicio.retornarRolById(Integer.parseInt(result[5].toString()));

                rol.setDescripcionRol((result[9].toString()));
              
                
                Personals.add(personal);
            }
            
            return Personals;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }  
    
         @SuppressWarnings("unchecked")
    public List<Personal> filtroPorNombre(String nombrePersonal) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_PERSONAL.FILTRO_NOMBRE_PERSONAL");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("p_nombres_personal", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CURSOR_PERSONAL", Class.class, ParameterMode.REF_CURSOR);

            // Configuramos el valor de entrada
            query.setParameter("p_nombres_personal", nombrePersonal);

            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Personal> Personals = new ArrayList<Personal>();
           
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
               Personal personal = new Personal();
                personal.setIdPersonal(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                personal.setRutPersonal(result[1].toString());
                personal.setNombresPersonal(result[2].toString());
                personal.setApePaternoPersonal(result[3].toString());
                personal.setApeMaternoPersonal(result[4].toString());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = result[5].toString();
                Date fecha = formatoFecha.parse(strFecha);
                personal.setFechaNacimientoPersonal(fecha);
//                
                personal.setCelularPersonal(result[6].toString());
                personal.setCorreoPersonal(result[7].toString());
                personal.setEstadoPersonal(BigInteger.valueOf(Integer.parseInt(result[8].toString())));
//                personal.setContrasenaPersonal(result[3].toString());
                
                Rol rol =new Rol();
                personal.setIdRol(rol);
//                rol=this.personalServicio.retornarRolById(Integer.parseInt(result[5].toString()));

                rol.setDescripcionRol((result[9].toString()));
              
                
                Personals.add(personal);
            }
            
            return Personals;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }  
    
    
 
        
        
        
        
        
        
        

    public void updatePerfilPersonal(BigDecimal idPersonal, String nombre, String paterno, String materno, Date FechaNacimiento, String celular, String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

    //SuppressWarnings suprime las abvertencias de tipo unchecked
//    @SuppressWarnings("unchecked")
//        public List<Receta> filtrarRecetaByNombre(String nombreReceta) {
//        try {
//            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_RECETA.FILTRO_NOMBRE_RECETA");
//
//            // Registrar los parámetros de entrada y salida
//            query.registerStoredProcedureParameter("P_NOMBRE_RECETA", String.class, ParameterMode.IN);
//            query.registerStoredProcedureParameter ("P_RECETAS_CURSOR", Class.class, ParameterMode.REF_CURSOR);
//            // Configuramos el valor de entrada
//            query.setParameter ("P_NOMBRE_RECETA", nombreReceta);
//            query.execute ();
//
//        // Obtenemos el resultado del cursos en una lista
//        List<Object[]> results = query.getResultList();
//        List<Receta> recetas = new ArrayList<Receta>();
//
//        // Recorremos la lista con map y devolvemos un List<BusinessObject>
//        for (Object[] result : results
//
//        
//            ) {
//                Receta receta = new Receta();
//            receta.setIdReceta(Integer.parseInt(result[0].toString()));
//            receta.setNombreReceta(result[1].toString());
//            receta.setDescripcionReceta(result[2].toString());
//            receta.setDuracionPreparacion(BigInteger.valueOf(Integer.parseInt(result[3].toString())));
//            receta.setDisponibilidadReceta(BigInteger.valueOf(Integer.parseInt(result[4].toString())));
//            receta.setPrecioReceta(BigInteger.valueOf(Integer.parseInt(result[5].toString())));
//            receta.setCantidadPrepDiariaReceta(BigInteger.valueOf(Integer.parseInt(result[6].toString())));
//            receta.setFoto(String.valueOf(result[7].toString()));
//            receta.setTipoReceta(result[8].toString());
//            CategoriaReceta categoria = new CategoriaReceta();
//            categoria.setIdCategoriaReceta(BigDecimal.valueOf(Integer.parseInt(result[9].toString())));
//            categoria.setDescripcionCategoriaReceta(result[10].toString());
//            categoria.setCantRecetasDia(BigInteger.valueOf(3));
//            receta.setIdCategoriaReceta(categoria);
//            recetas.add(receta);
//        }
//
//        return recetas ;
//
//    }
//    catch (Exception ex
//
//    
//        ) {
//            ex.printStackTrace();
//        return null;
//    }
//
//    
//        try {
//
//            em.createNamedStoredProcedureQuery("InsertPersonal")
//                .setParameter(P_RUT_PERSONAL, rut)
//                .setParameter(P_NOMBRES_PERSONAL, nombres)
//                .setParameter(P_APE_PATERNO_PERSONAL, apePaterno)
//                .setParameter(P_APE_MATERNO_PERSONAL, apeMaterno)
//                .setParameter(P_FECHA_NACIMIENTO_PERSONAL, fecha_nacimiento)
//                .setParameter(P_CELULAR_PERSONAL, celular)
//                .setParameter(P_CORREO_PERSONAL, correo)
//                .setParameter(P_CONTRASENA_PERSONAL, contrasena)
//                .setParameter(P_ESTADO_PERSONAL, estado)
//                .setParameter(P_RUT_PERSONAL, rut)
//                .setParameter(P_ID_ROL, idRol).execute();
//
//        return true;
//    }
//    catch (Exception ex
//
//    
//        ) {
//            System.out.println(ex.getMessage());

//    }
//
//    
//    
//
//return false;{}
