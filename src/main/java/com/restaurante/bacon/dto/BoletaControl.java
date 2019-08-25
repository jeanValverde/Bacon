/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "BOLETA_CONTROL")
public class BoletaControl implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BOLETA_CONTROL")
    private BigDecimal idBoletaControl;
    @JoinColumn(name = "ID_BOLETA", referencedColumnName = "ID_BOLETA")
    @ManyToOne(optional = false)
    private Boleta idBoleta;
    @JoinColumn(name = "ID_CONTROL_CAJA", referencedColumnName = "ID_CONTROL_CAJA")
    @ManyToOne(optional = false)
    private ControlCaja idControlCaja;

    public BoletaControl() {
    }

    public BoletaControl(BigDecimal idBoletaControl) {
        this.idBoletaControl = idBoletaControl;
    }

    public BigDecimal getIdBoletaControl() {
        return idBoletaControl;
    }

    public void setIdBoletaControl(BigDecimal idBoletaControl) {
        this.idBoletaControl = idBoletaControl;
    }

    public Boleta getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Boleta idBoleta) {
        this.idBoleta = idBoleta;
    }

    public ControlCaja getIdControlCaja() {
        return idControlCaja;
    }

    public void setIdControlCaja(ControlCaja idControlCaja) {
        this.idControlCaja = idControlCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoletaControl != null ? idBoletaControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoletaControl)) {
            return false;
        }
        BoletaControl other = (BoletaControl) object;
        if ((this.idBoletaControl == null && other.idBoletaControl != null) || (this.idBoletaControl != null && !this.idBoletaControl.equals(other.idBoletaControl))) {
            return false;
        }
        return true;
    }
    
}
