/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dao;

import com.restaurante.bacon.dto.Insumo;
import com.restaurante.bacon.dto.InsumoPedido;
import com.restaurante.bacon.dto.InsumoProveedor;
import com.restaurante.bacon.dto.Proveedor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author CRISTIAN
 */

public class ListaInsumosPedidosProveedores {
   
    private ArrayList <InsumoPedidoInsumoProveedorDAO> insumos;
    private ArrayList <Proveedor> grupoProveedores ;

    public ListaInsumosPedidosProveedores() {
    }

    public ListaInsumosPedidosProveedores(ArrayList<InsumoPedidoInsumoProveedorDAO> insumos, ArrayList<Proveedor> grupoProveedores) {
        this.insumos = insumos;
        this.grupoProveedores = grupoProveedores;
    }
    
    

    public ArrayList<InsumoPedidoInsumoProveedorDAO> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<InsumoPedidoInsumoProveedorDAO> insumos) {
        this.insumos = insumos;
    }

    public ArrayList<Proveedor> getGrupoProveedores() {
        return grupoProveedores;
    }

    public void setGrupoProveedores(ArrayList<Proveedor> grupoProveedores) {
        this.grupoProveedores = grupoProveedores;
    }

    
    
    
    
   
    
    
}
