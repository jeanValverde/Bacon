/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import com.restaurante.bacon.dto.Personal;
import static com.restaurante.bacon.dto.Personal.P_RUT_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_NOMBRES_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_APE_MATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_APE_PATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CELULAR_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CONTRASENA_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CORREO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ESTADO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_FECHA_NACIMIENTO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ID_ROL;
import com.restaurante.bacon.dto.Rol;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Enrique
 */

@Entity
@Table
@Getter
@Setter
        @NamedStoredProcedureQueries(
                {
                    @NamedStoredProcedureQuery(name = "InsertPersonal", procedureName = " PACKAGE_PERSONAL.PR_INSERT_PERSONAL",
                            parameters = {
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RUT_PERSONAL_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOMBRES_PERSONA_INL", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_APE_PATERNO_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_APE_MATERNO_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FECHA_NACIMIENTO_IN", type = Date.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CELULAR_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CORREO_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CONTRASENA_IN", type = String.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ESTADO_PERSONAL_IN", type = BigInteger.class),
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ROL_PERSONAL_IN", type = Integer.class)
                 
                            }
                    )
                }
        
                )       


public class ProcedureInsertPersonal implements Serializable {
    @Id 
    private int id;
    
}
