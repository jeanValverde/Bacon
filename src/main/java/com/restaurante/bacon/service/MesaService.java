/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IMesaDao;
import com.restaurante.bacon.dao.ProcedureQueryMesa;
import com.restaurante.bacon.dto.Mesa;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro
 */
@Service
public class MesaService {

    @Autowired
    IMesaDao mesaDao;

    @Autowired
    ProcedureQueryMesa procedureQuery;

//    public boolean add(Integer numero, Integer cantidadAsientos, Integer estado){
//        return this.procedureQuery.InsertMesa(numero, cantidadAsientos, estado);
//    }
    public Mesa add(Mesa mesa) {
        return this.mesaDao.save(mesa);
    }
    
    public Mesa updateEstado(Mesa mesa) {
        return this.mesaDao.save(mesa);
    }

    public boolean editarMesa(Mesa mesa) {
        return this.procedureQuery.UpdateMesa(mesa.getIdMesa(), mesa.getNumeroMesa(), mesa.getCantidadAsientosMesa(), mesa.getEstadoMesa());
    }

    public Mesa retornarMesaById(Integer idMesa) {
        Optional<Mesa> optinalEntity = mesaDao.findById(idMesa);
        Mesa mesa = optinalEntity.get();
        return mesa;
    }

    public List<Mesa> listarMesa() {
        return this.mesaDao.findAll();
    }

    public Mesa eliminarMesa(Integer idMesa) {
        Mesa mesa = mesaDao.findByIdMesa(idMesa);
        mesaDao.deleteById(idMesa);
        return mesa;
    }
    
    public List<Mesa> filtrarRecetaByNumero(BigInteger numeroMesa) {
        return this.procedureQuery.filtrarRecetaByNumero(numeroMesa);
    }
    
    public List<Mesa> filtrarRecetaByAsientos(BigInteger cantidadAsientosMesa) {
        return this.procedureQuery.filtrarRecetaByAsientos(cantidadAsientosMesa);
    }
    
    public List<Mesa> filtrarRecetaByEstado(BigInteger estadoMesa) {
        return this.procedureQuery.filtrarRecetaByEstado(estadoMesa);
    }
    
    public List<Mesa> EstadoPedido() {
        return this.procedureQuery.EstadoPedido();
    }
}
