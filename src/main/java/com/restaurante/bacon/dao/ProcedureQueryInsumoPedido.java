/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Receta;
import com.restaurante.bacon.service.InsumoService;
import com.restaurante.bacon.service.ProveedorService;
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
public class ProcedureQueryInsumoPedido {

    //acceder a la conexión 
    @Autowired
    private EntityManager em;
    @Autowired
    private InsumoService insumoService;
    @Autowired
    private ProveedorService proveedorService;

   
    @SuppressWarnings("unchecked")
    public List<InsumoPedido> listarInsumosPedidos() {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PEDIDO.PR_LISTAR_INSUMOS_PEDIDO");

            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("cursor_insumos_pedido", Class.class, ParameterMode.REF_CURSOR);

            
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<InsumoPedido> insumos_pedidos = new ArrayList<InsumoPedido>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                InsumoPedido insumo_pedido = new InsumoPedido();
                insumo_pedido.setIdInsumoPedido(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                insumo_pedido.setCantidadInsumo(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                insumo_pedido.setEstadoInsumoPedido(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                Insumo insumo = new Insumo();
                insumo = this.insumoService.retornarInsumoById(Integer.parseInt(result[3].toString()));
                insumo_pedido.setIdInsumo(insumo);
                
              
                insumos_pedidos.add(insumo_pedido);
            }
            
            return insumos_pedidos;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Proveedor> listarProveedoresConInsumoPedido(BigInteger idInsumo) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PEDIDO.PR_PROVEEDORES_DE_INSUMO");
            
            // Registrar los parámetros de entrada y salida
            query.registerStoredProcedureParameter("cursor_proveedores", Class.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("P_ID_INSUMO", BigInteger.class, ParameterMode.IN);
           
            query.setParameter("P_ID_INSUMO", idInsumo);
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            List<Proveedor> proveedores = new ArrayList<Proveedor>();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                proveedor.setNombreProveedor(result[1].toString());
                
                
              
                proveedores.add(proveedor);
            }
            
            return proveedores;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public InsumoProveedor retornarInsumoProveedor(Integer idInsumo,BigInteger idProveedor) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PEDIDO.PR_INSUMO_PROVEEDOR_PEDIDO");

            // Registrar los parámetros de entrada y salida
           query.registerStoredProcedureParameter("P_ID_INSUMO", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_ID_PROVEEDOR", BigInteger.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_CURSOR_INSUMO_PROVEEDOR", Class.class, ParameterMode.REF_CURSOR);
            query.setParameter("P_ID_INSUMO", idInsumo);
            query.setParameter("P_ID_PROVEEDOR", idProveedor);
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            InsumoProveedor insumoProveedor = new InsumoProveedor();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                
                insumoProveedor.setIdInsumoProveedor(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                insumoProveedor.setPrecio(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                Insumo insumo = new Insumo();
                insumo = this.insumoService.retornarInsumoById(idInsumo);
                insumoProveedor.setIdInsumo(insumo);
                Proveedor proveedor = new Proveedor();
                proveedor = this.proveedorService.retornarProveedorById(BigDecimal.valueOf(Integer.parseInt(result[3].toString())));
                insumoProveedor.setIdProveedor(proveedor);
                 
            }
            
            return insumoProveedor;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public InsumoPedido retornarInsumoPedido(Integer idInsumoPedido) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PACKAGE_INSUMO_PEDIDO.PR_INSUMO_PEDIDO");

            // Registrar los parámetros de entrada y salida
           query.registerStoredProcedureParameter("P_ID_INSUMO_PEDIDO", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_CURSOR_INSUMO_PEDIDO", Class.class, ParameterMode.REF_CURSOR);
            query.setParameter("P_ID_INSUMO_PEDIDO", idInsumoPedido);
            query.execute();

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = query.getResultList();
            InsumoPedido insumoPedido = new InsumoPedido();
            
            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            for (Object[] result : results) {
                insumoPedido.setIdInsumoPedido(BigDecimal.valueOf(Integer.parseInt(result[0].toString())));
                insumoPedido.setCantidadInsumo(BigInteger.valueOf(Integer.parseInt(result[1].toString())));
                insumoPedido.setEstadoInsumoPedido(BigInteger.valueOf(Integer.parseInt(result[2].toString())));
                Insumo insumo = new Insumo();
                insumo = this.insumoService.retornarInsumoById(Integer.parseInt(result[3].toString()));
                insumoPedido.setIdInsumo(insumo);
                
                
                 
            }
            
            return insumoPedido;

        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
            ex.printStackTrace();
            return null;
        }
    }


    


}
