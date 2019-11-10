/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.IInsumoProveedorDao;
import com.restaurante.bacon.dao.IInsumoProveedorDao2;
import com.restaurante.bacon.dao.IProveedorDao;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dao.ProcedureQueryInsumoProveedor;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author estebantoledo
 */
@Service
public class InsumoProveedorService {

    @Autowired
    ProcedureQueryInsumoProveedor procedureQueryInsumo;
    @Autowired
    IInsumoProveedorDao2 insumoproveedordao;
    @Autowired
    IProveedorDao provedorDao;
    @Autowired
    ProcedureQuery procedureQuery;
    @Autowired
    ProcedureQueryInsumoProveedor procedureQueryInsumoProveedor;
    @Autowired
    InsumoService insumoService;

    public boolean ingresarInsumoProveedor(Integer precio, Integer idInsumo, Integer idProveedor) {
        return this.procedureQueryInsumoProveedor.InsertInsumoProveedor(precio, idInsumo, idProveedor);
    }

    public InsumoProveedor ingresarInsumoProveedor(InsumoProveedor insumoProveedor) {
        return this.insumoproveedordao.save(insumoProveedor);

    }

    public List<InsumoProveedor> buscarPorId(Integer idInsumoProveedor) {
        return this.procedureQueryInsumoProveedor.buscarIdInsumoProveedor(idInsumoProveedor);
    }

    public List<InsumoProveedor> listarInsumoProveedor() {
        return this.insumoproveedordao.findAll();
    }

    public List<InsumoProveedor> insumosByIdProveedor(BigDecimal idProveedor) {
        return this.procedureQueryInsumo.insumosByIdProveedor(idProveedor);

    }

    public List<Insumo> listarInsumosAgregarProveedor(BigDecimal idProveedor) {
        List<Insumo> totalInsumos = this.insumoService.listarInsumos();
        List<Insumo> insumos = new ArrayList<Insumo>();
        List<Insumo> insumosReturn = new ArrayList<Insumo>();

        List<InsumoProveedor> insumosUsados = this.insumosByIdProveedor(idProveedor);
        List<Insumo> insumosProveedor = new ArrayList<Insumo>();
        try {
            for (InsumoProveedor insumoUsado : insumosUsados) {
                Insumo insu = new Insumo();
                insu = insumoUsado.getIdInsumo();
                insumosProveedor.add(insu);
            }
            for (Insumo insumo : totalInsumos) {
                Insumo ins = new Insumo();
                ins = insumo;
                insumos.add(ins);
                insumosReturn.add(ins);
            }
            for (Insumo insumo : insumos) {
                for (Insumo insumoProveedor1 : insumosProveedor) {
                    if (insumo.getIdInsumo() == insumoProveedor1.getIdInsumo()) {
                        insumosReturn.remove(insumoProveedor1);
                    }
                }

            }
            return insumosReturn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return insumos;
        }
    }

    public boolean update(InsumoProveedor insumoProvee) {

        return this.procedureQuery.UpdateInsumoProveedor(insumoProvee.getIdInsumoProveedor(), insumoProvee.getPrecio());

    }

    public InsumoProveedor actualizar(InsumoProveedor insumoProveedor) {
        return this.insumoproveedordao.save(insumoProveedor);

    }

    public boolean eliminarInsumoProveedor(Integer idInsumoProveedor) {
        return this.procedureQuery.DeleteInsumoProveedorById(idInsumoProveedor);
    }

    public Proveedor buscarProveedorById(BigDecimal idProveedor) {
        return this.provedorDao.findByIdProveedor(idProveedor);
    }

}