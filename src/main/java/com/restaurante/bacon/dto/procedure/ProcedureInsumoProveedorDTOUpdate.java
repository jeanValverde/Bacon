/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import static com.restaurante.bacon.dto.InsumoProveedor.I_ID_INSUMO_PROVEEDOR;
import static com.restaurante.bacon.dto.InsumoProveedor.I_PRECIO;
import java.math.BigInteger;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@NamedStoredProcedureQuery(name = "UpdateInsumoProveedor", procedureName = "PACKAGE_INSUMO_PROVEEDOR.PR_MODIFICAR_INSUMO_PROVEEDOR",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = I_ID_INSUMO_PROVEEDOR, type = Integer.class)
            ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = I_PRECIO, type = BigInteger.class)

        }
)
@NamedStoredProcedureQuery(name = "DeleteInsumoProveedor", procedureName = "PACKAGE_INSUMO_PROVEEDOR.PR_ELIMINAR_INSUMO_PROVEEDOR",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = I_ID_INSUMO_PROVEEDOR, type = Integer.class)

        }
)

public class ProcedureInsumoProveedorDTOUpdate implements Serializable {

    @Id
    private int id;
}
