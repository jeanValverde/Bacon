

package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Personal;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andresiero
 */
@Repository
public interface IIPersonalDao extends JpaRepository<Personal, BigDecimal> {

   
    Personal findByIdPersonal(BigDecimal idPersonal);
}
 /**
     *
     * @param idPersonal
     * @return
     */