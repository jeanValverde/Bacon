/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.ICategoriaRecetaDao;
import com.restaurante.bacon.dao.IIngredienteDao;
import com.restaurante.bacon.dao.IRecetaDao;
import com.restaurante.bacon.dao.ProcedureQueryIngrediente;
import com.restaurante.bacon.dao.ProcedureQueryReceta;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Ingrediente;
import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.Receta;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean
 */
@Service
public class RecetaService {

    @Autowired
    IRecetaDao recetaDao;

    @Autowired
    ICategoriaRecetaDao categoriaReceta;

    @Autowired
    ProcedureQueryReceta procedureQuery;

    @Autowired
    ProcedureQueryIngrediente procedureQueryIngrediente;

    @Autowired
    IIngredienteDao ingrediente;

    @Autowired
    InsumoService insumoService;

    public Receta add(Receta receta) {
        return this.recetaDao.save(receta);
    }

    public List<Receta> listar() {
        return this.recetaDao.findAll();
    }

    public List<CategoriaReceta> listarCategoria() {
        return this.categoriaReceta.findAll();
    }

    public List<Receta> filtrarRecetasByNombre(String nombreReceta) {
        return this.procedureQuery.filtrarRecetaByNombre(nombreReceta);
    }

    public void deleteRecetaById(Integer idReceta) {
        this.recetaDao.deleteById(idReceta);
    }

    public Receta buscarRecetaById(Integer idReceta) {
        return this.recetaDao.findByIdReceta(idReceta);
    }

    public List<Receta> filtrarRecetasByDisponibilidad(Integer disponibilidad) {
        return this.procedureQuery.filtrarRecetaByDisponibilidad(disponibilidad);
    }

    public List<Receta> filtrarRecetasByCategoriaCocina(Integer idCategoriaReceta) {
        return this.procedureQuery.filtrarRecetaByCategoriaCocina(idCategoriaReceta);
    }

    public List<Receta> filtrarRecetasByBar() {
        return this.procedureQuery.filtrarRecetaByBar();
    }

    public Receta update(Receta receta) {
        return this.recetaDao.save(receta);
    }

    public Ingrediente addIngrediente(Ingrediente ingrediente) {
        return this.ingrediente.save(ingrediente);
    }

    public List<Ingrediente> listarIngredientesByIdReceta(Integer idReceta) {
        return this.procedureQueryIngrediente.filtrarInsumosByIdReceta(idReceta);
    }

    public List<Insumo> listarInsumosAgregarReceta(Integer idReceta) {

        List<Insumo> totalInsumos = this.insumoService.listarInsumos();
        List<Insumo> insumos =  totalInsumos;
        

        List<Ingrediente> ingredientesUsados = this.listarIngredientesByIdReceta(idReceta);
        List<Ingrediente> ingredientes = ingredientesUsados;

        try {
            
            for (Insumo insumo : insumos) {
                for (Ingrediente ingrediente1 : ingredientes) {
                    if(insumo.getIdInsumo() == ingrediente1.getIdInsumo().getIdInsumo()){
                        insumos.remove(insumo);
                    }
                }
            }

            return insumos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return insumos;
        }
    }

}
