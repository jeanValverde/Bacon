/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.InsumoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author estebantoledo
 */
public interface IInsumoProveedorDao extends JpaRepository<InsumoProveedor,Integer> {
    
    InsumoProveedor findByIdInsumoProveedor(Integer idInsumoProveedor);
    
}
