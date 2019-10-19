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
public class InformeCliente {
    
    private String nombre;
    private String descripcion_estado;

    public InformeCliente() {
    }

    public InformeCliente(String nombre, String descripcion_estado) {
        this.nombre = nombre;
        this.descripcion_estado = descripcion_estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion_estado() {
        return descripcion_estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion_estado(String descripcion_estado) {
        this.descripcion_estado = descripcion_estado;
    }
    
    
    
}
