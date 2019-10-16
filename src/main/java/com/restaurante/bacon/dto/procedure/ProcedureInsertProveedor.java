/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import com.restaurante.bacon.dto.Proveedor;
import static com.restaurante.bacon.dto.Proveedor.P_CATEGORIA_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CELULAR_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_CONTACTO_VENTA;
import static com.restaurante.bacon.dto.Proveedor.P_CORREO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_DIRECCION_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_NOMBRE_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_RUT_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TELEFONO_PROVEEDOR;
import static com.restaurante.bacon.dto.Proveedor.P_TIPO_PROVEEDOR;
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
 * @author jean
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "InsertProveedor", procedureName = "PACKAGE_PROVEEDOR.PR_INSERT_PROVEEDOR",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = P_RUT_PROVEEDOR, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_NOMBRE_PROVEEDOR, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_DIRECCION_PROVEEDOR, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_TELEFONO_PROVEEDOR, type = String.class)
                        ,

                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CONTACTO_VENTA, type = String.class)

                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_TIPO_PROVEEDOR, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CORREO_PROVEEDOR, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CELULAR_PROVEEDOR, type = Integer.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CATEGORIA_PROVEEDOR, type = String.class)
                    }
            )
        }
)
public class ProcedureInsertProveedor implements Serializable {

    @Id
    private int id;

}
