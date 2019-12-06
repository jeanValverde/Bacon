/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;


import static com.restaurante.bacon.dto.Cliente.c_id;
import static com.restaurante.bacon.dto.Cliente.c_nom;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.restaurante.bacon.dto.Notificacion;
import static com.restaurante.bacon.dto.Notificacion.N_ID_NOTIFICACION;
import static com.restaurante.bacon.dto.Notificacion.N_DESCRIPCION;
import static com.restaurante.bacon.dto.Notificacion.N_ESTADO;
import static com.restaurante.bacon.dto.Notificacion.N_ID_ROL;

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
            @NamedStoredProcedureQuery(name = "InsertAsistencia", procedureName = "PACKAGE_NOTIFICACION.PR_INSERT_ASISTENCIA",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "c_id", type = Integer.class)
                        ,

                       
                          @StoredProcedureParameter(mode = ParameterMode.IN, name = "c_nombre", type = String.class)
                        ,
                       
                      

                    }
            )
        }
)








public class ProcedureSolicitarAsistencia implements Serializable {
        @Id
    private int id;
}
