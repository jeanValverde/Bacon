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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "INGRESO_STOCK")
public class IngresoStock implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INGRESO_STOCK")
    private BigDecimal idIngresoStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumn(name = "ID_INSUMO", referencedColumnName = "ID_INSUMO")
    @ManyToOne(optional = false)
    private Insumo idInsumo;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
    @ManyToOne(optional = false)
    private Pedido idPedido;

    public IngresoStock() {
    }

    public IngresoStock(BigDecimal idIngresoStock) {
        this.idIngresoStock = idIngresoStock;
    }

    public IngresoStock(BigDecimal idIngresoStock, Date fechaIngreso, Date fechaCaducidad, BigInteger cantidad) {
        this.idIngresoStock = idIngresoStock;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
    }

    public BigDecimal getIdIngresoStock() {
        return idIngresoStock;
    }

    public void setIdIngresoStock(BigDecimal idIngresoStock) {
        this.idIngresoStock = idIngresoStock;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Insumo getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumo idInsumo) {
        this.idInsumo = idInsumo;
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
        hash += (idIngresoStock != null ? idIngresoStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoStock)) {
            return false;
        }
        IngresoStock other = (IngresoStock) object;
        if ((this.idIngresoStock == null && other.idIngresoStock != null) || (this.idIngresoStock != null && !this.idIngresoStock.equals(other.idIngresoStock))) {
            return false;
        }
        return true;
    }

    
}
