/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import java.io.Serializable;
import java.math.BigInteger;
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
            @NamedStoredProcedureQuery(name = "UpdateMesa", procedureName = "PACKAGE_MESA.PR_MODIFICAR_MESA",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_MESA", type = Integer.class)
                            ,
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NUMERO_MESA", type = BigInteger.class)
                            ,
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CANTIDAD_ASIENTOS_MESA", type = BigInteger.class)
                            ,
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ESTADO_MESA", type = BigInteger.class)
                    }
            )
        }
)
public class ProcedureUpdateMesa implements Serializable{
    @Id
    private int id;
}
