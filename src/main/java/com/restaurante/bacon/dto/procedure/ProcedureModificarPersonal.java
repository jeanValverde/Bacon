/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import static com.restaurante.bacon.dto.Personal.P_APE_MATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_APE_PATERNO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CELULAR_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_CORREO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ESTADO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_FECHA_NACIMIENTO_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ID_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_ID_ROL;
import static com.restaurante.bacon.dto.Personal.P_NOMBRES_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_RUT_PERSONAL;
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
 * @author Andresiero
 */
@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries(
        {
            @NamedStoredProcedureQuery(name = "modificarPersonal", procedureName = "PACKAGE_PERSONAL.PR_MODIFICAR_PERSONAL",
                    parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_ID_PERSONAL, type = BigDecimal.class)
                                                ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_RUT_PERSONAL, type = String.class)
                            ,
                        
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_NOMBRES_PERSONAL, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_APE_PATERNO_PERSONAL, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_APE_MATERNO_PERSONAL, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_FECHA_NACIMIENTO_PERSONAL, type = Date.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CELULAR_PERSONAL, type = String.class)
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_CORREO_PERSONAL, type = String.class)
                    
                        ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_ESTADO_PERSONAL, type = BigInteger.class)
                    
                                ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = P_ID_ROL, type = Integer.class)
                    }
                    
            )
        }
)


public class ProcedureModificarPersonal implements Serializable {
     @Id
    private int id;
}

//       P_ESTADO IN personal.estado_personal%TYPE,
//       P_ID_ROL IN personal.id_rol%TYPE);