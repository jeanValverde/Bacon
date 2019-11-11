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


/**
 *
 * @author CRISTIAN
 */

public class InsumoPedidoInsumoProveedorDAO {
   
    private InsumoPedido insumoPedido;
    
    private Insumo insumo;
    
    private InsumoProveedor insumoProveedor;
    
    private Proveedor proveedor;

    public InsumoPedidoInsumoProveedorDAO() {
    }

    public InsumoPedidoInsumoProveedorDAO(InsumoPedido insumoPedido, Insumo insumo, InsumoProveedor insumoProveedor, Proveedor proveedor) {
        this.insumoPedido = insumoPedido;
        this.insumo = insumo;
        this.insumoProveedor = insumoProveedor;
        this.proveedor = proveedor;
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

    public InsumoProveedor getInsumoProveedor() {
        return insumoProveedor;
    }

    public void setInsumoProveedor(InsumoProveedor insumoProveedor) {
        this.insumoProveedor = insumoProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    

   
    
    
}
