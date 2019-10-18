/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jean
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "insertDatos", procedureName = "UPDATE_DATOS",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID", type = Integer.class)
                        , 
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLAVE", type = String.class)
                    }
            )
        }
)
public class PrInsertDato {

    @Id
    private int id;
}
