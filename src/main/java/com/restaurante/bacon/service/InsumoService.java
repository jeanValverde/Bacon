/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoDao;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dao.ProcedureQueryInsumo;
import com.restaurante.bacon.dto.Insumo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean
 */
@Service
public class InsumoService {
    
    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IInsumoDao insumoDao;
    @Autowired
    ProcedureQueryInsumo procedureQueryInsumo;
    
    //se implementan los metodos declarados en la interfaz 
   
    public boolean ingresarInsumo(Insumo insumo) {
        return this.procedureQueryInsumo.InsertInsumo(insumo.getNombreInsumo(), insumo.getDescripcionInsumo(), insumo.getStockInsumo(), insumo.getMinimoStockInsumo(), insumo.getMinimoStockInsumo(), insumo.getUnidadMedidaInsumo(), insumo.getFotoInsumo());
         
    }
    public boolean modificarInsumo(Insumo insumo) {
        return this.procedureQueryInsumo.UpdateInsumo(insumo.getIdInsumo(),insumo.getNombreInsumo(), insumo.getDescripcionInsumo(), insumo.getStockInsumo(), insumo.getMinimoStockInsumo(), insumo.getMinimoStockInsumo(), insumo.getUnidadMedidaInsumo(), insumo.getFotoInsumo());
    }
    public boolean eliminarInsumo(Integer id) {
        return this.procedureQueryInsumo.DeleteInsumo(id);
    }
    
    public Insumo retornarInsumoById(Integer  idInsumo){
        Optional<Insumo> optinalEntity =  insumoDao.findById(idInsumo);
        Insumo insumo = optinalEntity.get();
        return insumo;
    }
    public List<Insumo> listarInsumos(){
        return this.insumoDao.findAll();
    }
     public List<Insumo> filtrarInsumosByNombre(String nombreInsumo) {
        return this.procedureQueryInsumo.filtrarInsumosByNombre(nombreInsumo);
    }
     public List<Insumo> filtrarInsumosByStock(BigInteger stock) {
        return this.procedureQueryInsumo.filtrarInsumosByStock(stock);
    }
     public List<Insumo> filtrarInsumosByUnidadMedida(String unidad) {
        return this.procedureQueryInsumo.filtrarInsumosByUnidadMedida(unidad);
    }
   
   
    
}
