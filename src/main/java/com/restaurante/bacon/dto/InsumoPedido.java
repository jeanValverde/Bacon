/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "INSUMO_PEDIDO")
public class InsumoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INSUMO_PEDIDO")
    private BigDecimal idInsumoPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_INSUMO")
    private BigInteger cantidadInsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_INSUMO_PEDIDO")
    private BigInteger estadoInsumoPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumoPedido")
    private Collection<InsumoPedidoProveedor> insumoPedidoProveedorCollection;
    @JoinColumn(name = "ID_INSUMO", referencedColumnName = "ID_INSUMO")
    @ManyToOne(optional = false)
    private Insumo idInsumo;

    public InsumoPedido() {
    }

    public InsumoPedido(BigDecimal idInsumoPedido) {
        this.idInsumoPedido = idInsumoPedido;
    }

    public InsumoPedido(BigDecimal idInsumoPedido, BigInteger cantidadInsumo, BigInteger estadoInsumoPedido) {
        this.idInsumoPedido = idInsumoPedido;
        this.cantidadInsumo = cantidadInsumo;
        this.estadoInsumoPedido = estadoInsumoPedido;
    }

    public BigDecimal getIdInsumoPedido() {
        return idInsumoPedido;
    }

    public void setIdInsumoPedido(BigDecimal idInsumoPedido) {
        this.idInsumoPedido = idInsumoPedido;
    }

    public BigInteger getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(BigInteger cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public BigInteger getEstadoInsumoPedido() {
        return estadoInsumoPedido;
    }

    public void setEstadoInsumoPedido(BigInteger estadoInsumoPedido) {
        this.estadoInsumoPedido = estadoInsumoPedido;
    }

    @XmlTransient
    public Collection<InsumoPedidoProveedor> getInsumoPedidoProveedorCollection() {
        return insumoPedidoProveedorCollection;
    }

    public void setInsumoPedidoProveedorCollection(Collection<InsumoPedidoProveedor> insumoPedidoProveedorCollection) {
        this.insumoPedidoProveedorCollection = insumoPedidoProveedorCollection;
    }

    public Insumo getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumo idInsumo) {
        this.idInsumo = idInsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumoPedido != null ? idInsumoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoPedido)) {
            return false;
        }
        InsumoPedido other = (InsumoPedido) object;
        if ((this.idInsumoPedido == null && other.idInsumoPedido != null) || (this.idInsumoPedido != null && !this.idInsumoPedido.equals(other.idInsumoPedido))) {
            return false;
        }
        return true;
    }

    
}
