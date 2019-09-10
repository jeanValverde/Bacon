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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "MESA")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //declarar que el id se usa con una secuencia 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESA")
    //declarar la secuencia 
    @SequenceGenerator(name = "SEQ_MESA", sequenceName = "SEQ_MESA", allocationSize = 1)
    
    
    @NotNull
    @Column(name = "ID_MESA")
    private Integer idMesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_MESA")
    private BigInteger numeroMesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_ASIENTOS_MESA")
    private BigInteger cantidadAsientosMesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_MESA")
    private BigInteger estadoMesa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMesa")
    private Collection<Cliente> clienteCollection;

    public Mesa() {
    }

    public Mesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Mesa(Integer idMesa, BigInteger numeroMesa, BigInteger cantidadAsientosMesa, BigInteger estadoMesa) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.cantidadAsientosMesa = cantidadAsientosMesa;
        this.estadoMesa = estadoMesa;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public BigInteger getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(BigInteger numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public BigInteger getCantidadAsientosMesa() {
        return cantidadAsientosMesa;
    }

    public void setCantidadAsientosMesa(BigInteger cantidadAsientosMesa) {
        this.cantidadAsientosMesa = cantidadAsientosMesa;
    }

    public BigInteger getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(BigInteger estadoMesa) {
        this.estadoMesa = estadoMesa;
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
        hash += (idMesa != null ? idMesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.idMesa == null && other.idMesa != null) || (this.idMesa != null && !this.idMesa.equals(other.idMesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurante.bacon.dto.Mesa[ idMesa=" + idMesa + " ]";
    }

    public void setNumeroMesa(Integer numeroMesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCantidadAsientosMesa(Integer cantidadAsientosMesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEstadoMesa(Integer estadoMesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
