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
@Table(name = "INSUMO_PROVEEDOR")
public class InsumoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INSUMO_PROVEEDOR")
    private BigDecimal idInsumoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private BigInteger precio;
    @JoinColumn(name = "ID_INSUMO", referencedColumnName = "ID_INSUMO")
    @ManyToOne(optional = false)
    private Insumo idInsumo;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public InsumoProveedor() {
    }

    public InsumoProveedor(BigDecimal idInsumoProveedor) {
        this.idInsumoProveedor = idInsumoProveedor;
    }

    public InsumoProveedor(BigDecimal idInsumoProveedor, BigInteger precio) {
        this.idInsumoProveedor = idInsumoProveedor;
        this.precio = precio;
    }

    public BigDecimal getIdInsumoProveedor() {
        return idInsumoProveedor;
    }

    public void setIdInsumoProveedor(BigDecimal idInsumoProveedor) {
        this.idInsumoProveedor = idInsumoProveedor;
    }

    public BigInteger getPrecio() {
        return precio;
    }

    public void setPrecio(BigInteger precio) {
        this.precio = precio;
    }

    public Insumo getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumo idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumoProveedor != null ? idInsumoProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoProveedor)) {
            return false;
        }
        InsumoProveedor other = (InsumoProveedor) object;
        if ((this.idInsumoProveedor == null && other.idInsumoProveedor != null) || (this.idInsumoProveedor != null && !this.idInsumoProveedor.equals(other.idInsumoProveedor))) {
            return false;
        }
        return true;
    }
}
