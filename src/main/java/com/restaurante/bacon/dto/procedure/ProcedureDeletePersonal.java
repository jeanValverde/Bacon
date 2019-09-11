/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto.procedure;

import static com.restaurante.bacon.dto.Personal.P_ID_PERSONAL;
import static com.restaurante.bacon.dto.Personal.P_RUT_PERSONAL;
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
 * @author Andresiero
 */


@Entity
@Table
@Getter
@Setter
        @NamedStoredProcedureQueries(
                {
                    @NamedStoredProcedureQuery(name = "DeletePersonal", procedureName = " PACKAGE_PERSONAL.P_DELETE_PERSONAL_BY_ID",
                            parameters = {
                                @StoredProcedureParameter(mode = ParameterMode.IN, name = P_ID_PERSONAL, type = String.class)
                            }
                    )


}
      
        
)

public class ProcedureDeletePersonal implements Serializable{
    
    @Id 
    private int id;
     

}
