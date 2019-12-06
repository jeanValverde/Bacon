/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import java.io.Serializable;
import java.math.BigDecimal;
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
            @NamedStoredProcedureQuery(name = "asistenciasByIdCliente", procedureName = "PACKAGE_NOTIFICACION.PR_ASISTENCIAS_BY_ID",
                    parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "c_id", type = Integer.class)
                    
                       
                      

                    }
            )
        }
)
public class ProcedureListarAsistencias implements Serializable {
         @Id
    private int id;  
}
