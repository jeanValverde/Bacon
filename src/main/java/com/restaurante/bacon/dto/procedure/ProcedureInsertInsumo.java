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
 * @author jean
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "InsertInsumo", procedureName = "PACKAGE_INSUMO.PR_INSERT_INSUMO",
                    parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOMBRE_INSUMO", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DESCRIPCION_INSUMO", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_STOCK_INSUMO", type = BigInteger.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_UNIDAD_MEDIDA_INSUMO", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MINIMO_STOCK_INSUMO", type = BigInteger.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MAXIMO_STOCK_INSUMO", type = BigInteger.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FOTO_INSUMO", type = String.class)
                    }
            )
        }
)
public class ProcedureInsertInsumo implements Serializable {
     @Id
    private int id;
}
