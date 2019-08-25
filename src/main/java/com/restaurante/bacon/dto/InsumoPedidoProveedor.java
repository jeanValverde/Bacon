/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "INSUMO_PEDIDO_PROVEEDOR")
public class InsumoPedidoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INSUMO_PEDIDO_PROVEEDOR")
    private BigDecimal idInsumoPedidoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_PEDIDO_PROVEEDOR")
    private BigInteger precioPedidoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_TOTAL_INSUMO_PEDIDO")
    private BigInteger precioTotalInsumoPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA_INSUMO_PEDIDO")
    private BigInteger ivaInsumoPedido;
    @JoinColumn(name = "ID_INSUMO_PEDIDO", referencedColumnName = "ID_INSUMO_PEDIDO")
    @ManyToOne(optional = false)
    private InsumoPedido idInsumoPedido;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
    @ManyToOne(optional = false)
    private Pedido idPedido;

    public InsumoPedidoProveedor() {
    }

    public InsumoPedidoProveedor(BigDecimal idInsumoPedidoProveedor) {
        this.idInsumoPedidoProveedor = idInsumoPedidoProveedor;
    }

    public InsumoPedidoProveedor(BigDecimal idInsumoPedidoProveedor, BigInteger precioPedidoProveedor, BigInteger precioTotalInsumoPedido, BigInteger ivaInsumoPedido) {
        this.idInsumoPedidoProveedor = idInsumoPedidoProveedor;
        this.precioPedidoProveedor = precioPedidoProveedor;
        this.precioTotalInsumoPedido = precioTotalInsumoPedido;
        this.ivaInsumoPedido = ivaInsumoPedido;
    }

    public BigDecimal getIdInsumoPedidoProveedor() {
        return idInsumoPedidoProveedor;
    }

    public void setIdInsumoPedidoProveedor(BigDecimal idInsumoPedidoProveedor) {
        this.idInsumoPedidoProveedor = idInsumoPedidoProveedor;
    }

    public BigInteger getPrecioPedidoProveedor() {
        return precioPedidoProveedor;
    }

    public void setPrecioPedidoProveedor(BigInteger precioPedidoProveedor) {
        this.precioPedidoProveedor = precioPedidoProveedor;
    }

    public BigInteger getPrecioTotalInsumoPedido() {
        return precioTotalInsumoPedido;
    }

    public void setPrecioTotalInsumoPedido(BigInteger precioTotalInsumoPedido) {
        this.precioTotalInsumoPedido = precioTotalInsumoPedido;
    }

    public BigInteger getIvaInsumoPedido() {
        return ivaInsumoPedido;
    }

    public void setIvaInsumoPedido(BigInteger ivaInsumoPedido) {
        this.ivaInsumoPedido = ivaInsumoPedido;
    }

    public InsumoPedido getIdInsumoPedido() {
        return idInsumoPedido;
    }

    public void setIdInsumoPedido(InsumoPedido idInsumoPedido) {
        this.idInsumoPedido = idInsumoPedido;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumoPedidoProveedor != null ? idInsumoPedidoProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoPedidoProveedor)) {
            return false;
        }
        InsumoPedidoProveedor other = (InsumoPedidoProveedor) object;
        if ((this.idInsumoPedidoProveedor == null && other.idInsumoPedidoProveedor != null) || (this.idInsumoPedidoProveedor != null && !this.idInsumoPedidoProveedor.equals(other.idInsumoPedidoProveedor))) {
            return false;
        }
        return true;
    }

    
}
