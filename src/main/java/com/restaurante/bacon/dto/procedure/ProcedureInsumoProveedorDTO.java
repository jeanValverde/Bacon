/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import com.restaurante.bacon.dto.InsumoProveedor;
import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_INSUMO;
import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_INSUMO_PROVEEDOR;
import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_PROVEEDOR;
import static com.restaurante.bacon.dto.InsumoProveedor.I_PRECIO;
import java.io.Serializable;

import java.math.BigDecimal;

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
 * @author estebantoledo
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "InsertInsumoProveedor", procedureName = "PACKAGE_INSUMO_PROVEEDOR.PR_INSERT_INSUMO_PROVEEDOR",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = I_ID_INSUMO_PROVEEDOR, type = Integer.class)
                        ,

                       
                          @StoredProcedureParameter(mode = ParameterMode.IN, name = I_PRECIO, type = BigInteger.class)
                        ,
                         @StoredProcedureParameter(mode = ParameterMode.IN, name = I_ID_INSUMO, type = Integer.class)
                        , 
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = I_ID_PROVEEDOR, type = BigDecimal.class)

                    }
            )
        }
)

public class ProcedureInsumoProveedorDTO implements Serializable {

    @Id
    private int id;
}
