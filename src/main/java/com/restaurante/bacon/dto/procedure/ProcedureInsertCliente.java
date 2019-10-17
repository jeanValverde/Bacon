/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import java.io.Serializable;
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
 * @author Alejandro
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "InsertCliente", procedureName = "PACKAGE_CLIENTE.PR_INSERT_CLIENTE",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOMBRE", type = String.class)
                        ,
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_MESA", type = Integer.class)
                    }
            )
        }
)

public class ProcedureInsertCliente implements Serializable {

    @Id
    private int id;
}
