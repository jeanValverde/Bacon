/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Proveedor;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe
 */
@Repository
public interface IProveedorDao extends JpaRepository<Proveedor, BigDecimal> {
    
    Proveedor findByIdProveedor(BigDecimal idProveedor);
    
}
