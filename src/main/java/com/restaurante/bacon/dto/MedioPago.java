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
@Table(name = "MEDIO_PAGO")
public class MedioPago implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEDIO_PAGO")
    private BigDecimal idMedioPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESCRIPCION_MEDIO_PAGO")
    private String descripcionMedioPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedioPago")
    private Collection<Boleta> boletaCollection;

    public MedioPago() {
    }

    public MedioPago(BigDecimal idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public MedioPago(BigDecimal idMedioPago, String descripcionMedioPago) {
        this.idMedioPago = idMedioPago;
        this.descripcionMedioPago = descripcionMedioPago;
    }

    public BigDecimal getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(BigDecimal idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public String getDescripcionMedioPago() {
        return descripcionMedioPago;
    }

    public void setDescripcionMedioPago(String descripcionMedioPago) {
        this.descripcionMedioPago = descripcionMedioPago;
    }

    @XmlTransient
    public Collection<Boleta> getBoletaCollection() {
        return boletaCollection;
    }

    public void setBoletaCollection(Collection<Boleta> boletaCollection) {
        this.boletaCollection = boletaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedioPago != null ? idMedioPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedioPago)) {
            return false;
        }
        MedioPago other = (MedioPago) object;
        if ((this.idMedioPago == null && other.idMedioPago != null) || (this.idMedioPago != null && !this.idMedioPago.equals(other.idMedioPago))) {
            return false;
        }
        return true;
    }

    
}
