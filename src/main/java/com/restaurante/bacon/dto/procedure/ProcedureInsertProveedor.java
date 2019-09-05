/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
            @NamedStoredProcedureQuery(name = "updatePerfilPersonal", procedureName = "PACKAGE_PERSONAL.PR_UPDATE_PERFIL_PERSONAL",
                    parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_PERSONAL", type = BigDecimal.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOMBRE", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_APE_PATERNO", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_APE_MATERNO", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FECHA_NACIMIENTO", type = Date.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CELULAR", type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CORREO", type = String.class)
                    }
            )
        }
)
public class ProcedureUpdatePerfilPersonal implements Serializable {
     @Id
    private int id;
}
