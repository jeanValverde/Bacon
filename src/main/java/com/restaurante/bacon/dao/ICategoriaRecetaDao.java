/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.CategoriaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean
 */
@Repository
public interface ICategoriaRecetaDao extends JpaRepository<CategoriaReceta, Integer>{
    
}