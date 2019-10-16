/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IProveedorDao;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.Proveedor;
import com.restaurante.bacon.dto.Receta;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe
 */
@Service
public class ProveedorService {

    @Autowired
    IProveedorDao provedorDao;
    @Autowired
    ProcedureQuery procedureQuery;

   
    public boolean ingresarProveedor(Proveedor proveedor) {
        return this.procedureQuery.InsertProveedor(proveedor.getRutProveedor(), proveedor.getNombreProveedor(), proveedor.getDireccionProveedor(), proveedor.getTelefonoProveedor(), proveedor.getContactoVenta(), proveedor.getTipoProveedor(),proveedor.getCorreoProveedor(),proveedor.getCelularProveedor(),proveedor.getCategoriaProveedor());
         
    }
    
     public boolean modificarProveedor(Proveedor proveedor) {
        return this.procedureQuery.UpdateProveedor(proveedor.getIdProveedor(),proveedor.getRutProveedor(), proveedor.getNombreProveedor(), proveedor.getDireccionProveedor(), proveedor.getTelefonoProveedor(), proveedor.getContactoVenta(), proveedor.getTipoProveedor(),proveedor.getCorreoProveedor(),proveedor.getCelularProveedor(),proveedor.getCategoriaProveedor());
         
    }
     
      public boolean eliminarProveedor(Integer idProveedor) {
        return this.procedureQuery.DeleteProveedorById(idProveedor);
    }

   
    

   

    public List<Proveedor> listarProveedores() {
        return this.provedorDao.findAll();

    }
    
    public Proveedor retornarProveedorById(BigDecimal  idProveedor){
        Optional<Proveedor> optinalEntity = provedorDao.findById(idProveedor);
        Proveedor proveedor = optinalEntity.get();
        return proveedor;
    }
    
    public Proveedor buscarProveedorById(BigDecimal idProveedor) {
        return this.provedorDao.findByIdProveedor(idProveedor);
    }
    //Filtro por nombre
     public List<Proveedor> filtrarProveedoresByNombre(String nombreProveedor) {
        return this.procedureQuery.filtrarProveedoresByNombre(nombreProveedor);
    }
     
     //Filtro por Rut
     
      public List<Proveedor> filtrarProveedoresByRut(String rut) {
        return this.procedureQuery.filtrarProveedorByRut(rut);
    }
      
    //Filtro por categoria
   
     public List<Proveedor> filtrarProveedoresByCategoria(String categoria) {
        return this.procedureQuery.filtrarProveedorByCategoria(categoria);
    }
     
     
}
