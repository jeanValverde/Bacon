/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import com.restaurante.bacon.dao.ICategoriaRecetaDao;
import com.restaurante.bacon.dao.IRecetaDao;
import com.restaurante.bacon.dao.ProcedureQuery;
import com.restaurante.bacon.dto.CategoriaReceta;
import com.restaurante.bacon.dto.Receta;
import java.util.List;
import java.util.Optional;
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
    ProcedureQuery procedureQuery;

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

}
