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
@Table(name = "ESTADO_CLIENTE")
public class EstadoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_CLIENTE")
    private BigDecimal idEstadoCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESCRIPCION_ESTADO")
    private String descripcionEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCliente")
    private Collection<Cliente> clienteCollection;

    public EstadoCliente() {
    }

    public EstadoCliente(BigDecimal idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public EstadoCliente(BigDecimal idEstadoCliente, String descripcionEstado) {
        this.idEstadoCliente = idEstadoCliente;
        this.descripcionEstado = descripcionEstado;
    }

    public BigDecimal getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(BigDecimal idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCliente != null ? idEstadoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCliente)) {
            return false;
        }
        EstadoCliente other = (EstadoCliente) object;
        if ((this.idEstadoCliente == null && other.idEstadoCliente != null) || (this.idEstadoCliente != null && !this.idEstadoCliente.equals(other.idEstadoCliente))) {
            return false;
        }
        return true;
    }

    
    
}
