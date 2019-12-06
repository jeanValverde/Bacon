/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import static com.amazonaws.AmazonServiceException.ErrorType.Service;
import com.restaurante.bacon.dao.ISolicitarAsistenciaDao;
import com.restaurante.bacon.dao.ProcedureSolicitarAsistencia;
import com.restaurante.bacon.dto.Notificacion;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author estebantoledo
 */
@Service
public class SolicitarAsistenciaService {

    @Autowired
    ProcedureSolicitarAsistencia solicitudAsistencia;

    @Autowired
    ISolicitarAsistenciaDao solicitarAsistencia;

    public Notificacion add(Notificacion notificacion) {
        return this.solicitarAsistencia.save(notificacion);

    }

    public List<Notificacion> listarNotificacionesByIdCliente(Integer idCliente) {

        return this.solicitudAsistencia.asistenciasByIdCliente(idCliente);

    }

}
