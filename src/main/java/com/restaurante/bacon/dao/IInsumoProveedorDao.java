/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean
 */
@Repository
public interface IInsumoProveedorDao extends JpaRepository<InsumoProveedor, Integer>{

   
    
}
