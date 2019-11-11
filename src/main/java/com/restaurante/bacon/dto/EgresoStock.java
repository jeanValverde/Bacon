/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "EGRESO_STOCK")
public class EgresoStock implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EGRESO_STOCK")
    private BigDecimal idEgresoStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEgreso;
    @JoinColumn(name = "ID_INSUMO", referencedColumnName = "ID_INSUMO")
    @ManyToOne(optional = false)
    private Insumo idInsumo;

    public EgresoStock() {
    }

    public EgresoStock(BigDecimal idEgresoStock) {
        this.idEgresoStock = idEgresoStock;
    }

    public EgresoStock(BigDecimal idEgresoStock, BigInteger cantidad, Date fechaEgreso) {
        this.idEgresoStock = idEgresoStock;
        this.cantidad = cantidad;
        this.fechaEgreso = fechaEgreso;
    }

    public BigDecimal getIdEgresoStock() {
        return idEgresoStock;
    }

    public void setIdEgresoStock(BigDecimal idEgresoStock) {
        this.idEgresoStock = idEgresoStock;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
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
        hash += (idEgresoStock != null ? idEgresoStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoStock)) {
            return false;
        }
        EgresoStock other = (EgresoStock) object;
        if ((this.idEgresoStock == null && other.idEgresoStock != null) || (this.idEgresoStock != null && !this.idEgresoStock.equals(other.idEgresoStock))) {
            return false;
        }
        return true;
    }
    
}
