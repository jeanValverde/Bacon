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

    public Proveedor addProveedor(Proveedor proveedor) {
        return this.provedorDao.save(proveedor);
    }

    public boolean addProveedor(String rut, String nombre, String direccion, String telefono, String contacto, String tipo, String correo, Integer celular, String categoria) {
        return this.procedureQuery.InsertProveedor(rut, nombre, direccion, telefono, contacto, tipo, correo, celular, categoria);
    }
    

    public boolean modificarProveedor(BigDecimal idProveedor,String rut, String nombre, String direccion, String telefono, String contacto, String tipo, String correo, Integer celular, String categoria){
        return this.procedureQuery.UpdateProveedor(idProveedor,rut, nombre, direccion, telefono, contacto, tipo, correo, celular, categoria);

    }

    public List<Proveedor> listarProveedores() {
        return this.provedorDao.findAll();

    }
    
    public Proveedor retornarInsumoById(BigDecimal  idProveedor){
        Optional<Proveedor> optinalEntity = provedorDao.findById(idProveedor);
        Proveedor proveedor = optinalEntity.get();
        return proveedor;
    }
    
    public Proveedor buscarProveedorById(BigDecimal idProveedor) {
        return this.provedorDao.findByIdProveedor(idProveedor);
    }
}