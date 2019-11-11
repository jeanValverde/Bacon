/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "ESTADO_ORDEN")
public class EstadoOrden implements Serializable {

   

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_ORDEN")
    private Integer idEstadoOrden;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESCRIPCION_ESTADO_ORDEN")
    private String descripcionEstadoOrden;
     
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoOrden")
    private Collection<Orden> ordenCollection;

    public EstadoOrden() {
    }

    public EstadoOrden(Integer idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    public EstadoOrden(Integer idEstadoOrden, String descripcionEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
        this.descripcionEstadoOrden = descripcionEstadoOrden;
    }

    public Integer getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(Integer idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    public String getDescripcionEstadoOrden() {
        return descripcionEstadoOrden;
    }

    public void setDescripcionEstadoOrden(String descripcionEstadoOrden) {
        this.descripcionEstadoOrden = descripcionEstadoOrden;
    }

    @XmlTransient
    public Collection<Orden> getOrdenCollection() {
        return ordenCollection;
    }

    public void setOrdenCollection(Collection<Orden> ordenCollection) {
        this.ordenCollection = ordenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoOrden != null ? idEstadoOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOrden)) {
            return false;
        }
        EstadoOrden other = (EstadoOrden) object;
        if ((this.idEstadoOrden == null && other.idEstadoOrden != null) || (this.idEstadoOrden != null && !this.idEstadoOrden.equals(other.idEstadoOrden))) {
            return false;
        }
        return true;
    }

   

    
}
