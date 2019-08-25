/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "ESTADO_PEDIDO")
public class EstadoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_PEDIDO")
    private BigDecimal idEstadoPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESC_ESTADO_PEDIDO")
    private String descEstadoPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPedido")
    private Collection<Pedido> pedidoCollection;

    public EstadoPedido() {
    }

    public EstadoPedido(BigDecimal idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public EstadoPedido(BigDecimal idEstadoPedido, String descEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
        this.descEstadoPedido = descEstadoPedido;
    }

    public BigDecimal getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(BigDecimal idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getDescEstadoPedido() {
        return descEstadoPedido;
    }

    public void setDescEstadoPedido(String descEstadoPedido) {
        this.descEstadoPedido = descEstadoPedido;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPedido != null ? idEstadoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPedido)) {
            return false;
        }
        EstadoPedido other = (EstadoPedido) object;
        if ((this.idEstadoPedido == null && other.idEstadoPedido != null) || (this.idEstadoPedido != null && !this.idEstadoPedido.equals(other.idEstadoPedido))) {
            return false;
        }
        return true;
    }

    
}
