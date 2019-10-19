/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

/**
 *
 * @author Alejandro
 */
public class Informe {
    
    private String nombre_receta;
    private Integer cantidad;
    private Integer valor;
    private Integer total;

    public Informe() {
    }

    public Informe(String nombre_receta, Integer cantidad, Integer valor, Integer total) {
        this.nombre_receta = nombre_receta;
        this.cantidad = cantidad;
        this.valor = valor;
        this.total = total;
    }

    public String getNombre_receta() {
        return nombre_receta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getValor() {
        return valor;
    }

    public Integer getTotal() {
        return total;
    }

    public void setNombre_receta(String nombre_receta) {
        this.nombre_receta = nombre_receta;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
