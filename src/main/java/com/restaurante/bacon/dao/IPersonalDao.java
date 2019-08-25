/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean
 * 
 * Se pueden crear metodos con los nombres de la columnas por ejemplo 
 * findByColumna se declara en la intefaz  
 * 
 */
@Repository
public interface IPersonalDao extends JpaRepository<Personal, Integer> {
    //llamar a metodo buscar por rut del personal 
    Personal findByRutPersonal(String rut);
}
