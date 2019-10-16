/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class RecetaOrdenadaByOrden {
    private Orden orden;
    private List<RecetaOrdenada> recetaOrdenada = new ArrayList<RecetaOrdenada>();
    

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public List<RecetaOrdenada> getRecetaOrdenada() {
        return recetaOrdenada;
    }

    public void setRecetaOrdenada(List<RecetaOrdenada> recetaOrdenada) {
        this.recetaOrdenada = recetaOrdenada;
    }


    
    
}
