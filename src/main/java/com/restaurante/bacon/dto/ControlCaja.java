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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "CONTROL_CAJA")
public class ControlCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONTROL_CAJA")
    private BigDecimal idControlCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTROL_CAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaControlCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_BOLETAS")
    private BigInteger totalBoletas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UTILIDAD_BRUTA")
    private BigInteger utilidadBruta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FINAL")
    private BigInteger montoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIAL")
    private BigInteger montoInicial;
    @JoinColumn(name = "ID_PERSONAL", referencedColumnName = "ID_PERSONAL")
    @ManyToOne(optional = false)
    private Personal idPersonal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idControlCaja")
    private Collection<BoletaControl> boletaControlCollection;

    public ControlCaja() {
    }

    public ControlCaja(BigDecimal idControlCaja) {
        this.idControlCaja = idControlCaja;
    }

    public ControlCaja(BigDecimal idControlCaja, Date fechaControlCaja, BigInteger totalBoletas, BigInteger utilidadBruta, BigInteger montoFinal, BigInteger montoInicial) {
        this.idControlCaja = idControlCaja;
        this.fechaControlCaja = fechaControlCaja;
        this.totalBoletas = totalBoletas;
        this.utilidadBruta = utilidadBruta;
        this.montoFinal = montoFinal;
        this.montoInicial = montoInicial;
    }

    public BigDecimal getIdControlCaja() {
        return idControlCaja;
    }

    public void setIdControlCaja(BigDecimal idControlCaja) {
        this.idControlCaja = idControlCaja;
    }

    public Date getFechaControlCaja() {
        return fechaControlCaja;
    }

    public void setFechaControlCaja(Date fechaControlCaja) {
        this.fechaControlCaja = fechaControlCaja;
    }

    public BigInteger getTotalBoletas() {
        return totalBoletas;
    }

    public void setTotalBoletas(BigInteger totalBoletas) {
        this.totalBoletas = totalBoletas;
    }

    public BigInteger getUtilidadBruta() {
        return utilidadBruta;
    }

    public void setUtilidadBruta(BigInteger utilidadBruta) {
        this.utilidadBruta = utilidadBruta;
    }

    public BigInteger getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigInteger montoFinal) {
        this.montoFinal = montoFinal;
    }

    public BigInteger getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigInteger montoInicial) {
        this.montoInicial = montoInicial;
    }

    public Personal getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Personal idPersonal) {
        this.idPersonal = idPersonal;
    }

    @XmlTransient
    public Collection<BoletaControl> getBoletaControlCollection() {
        return boletaControlCollection;
    }

    public void setBoletaControlCollection(Collection<BoletaControl> boletaControlCollection) {
        this.boletaControlCollection = boletaControlCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlCaja != null ? idControlCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlCaja)) {
            return false;
        }
        ControlCaja other = (ControlCaja) object;
        if ((this.idControlCaja == null && other.idControlCaja != null) || (this.idControlCaja != null && !this.idControlCaja.equals(other.idControlCaja))) {
            return false;
        }
        return true;
    }
    
}
