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
import java.util.List;


/**
 *
 * @author CRISTIAN
 */

public class InsumoPedidoProveedorDAO {
   
    InsumoPedido insumoPedido;
    
    Insumo insumo;
    
    List<Proveedor> proveedores;
    
    InsumoProveedor insumoProveedor;

    public InsumoPedidoProveedorDAO() {
    }

    public InsumoPedidoProveedorDAO(InsumoPedido insumoPedido, Insumo insumo, List<Proveedor> proveedores, InsumoProveedor insumoProveedor) {
        this.insumoPedido = insumoPedido;
        this.insumo = insumo;
        this.proveedores = proveedores;
        this.insumoProveedor = insumoProveedor;
    }

    public InsumoPedido getInsumoPedido() {
        return insumoPedido;
    }

    public void setInsumoPedido(InsumoPedido insumoPedido) {
        this.insumoPedido = insumoPedido;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public InsumoProveedor getInsumoProveedor() {
        return insumoProveedor;
    }

    public void setInsumoProveedor(InsumoProveedor insumoProveedor) {
        this.insumoProveedor = insumoProveedor;
    }

   
    
    
}
