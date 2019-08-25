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
@Table(name = "CATEGORIA_RECETA")
public class CategoriaReceta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATEGORIA_RECETA")
    private BigDecimal idCategoriaReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESCRIPCION_CATEGORIA_RECETA")
    private String descripcionCategoriaReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_RECETAS_DIA")
    private BigInteger cantRecetasDia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaReceta")
    private Collection<Receta> recetaCollection;

    public CategoriaReceta() {
    }

    public CategoriaReceta(BigDecimal idCategoriaReceta) {
        this.idCategoriaReceta = idCategoriaReceta;
    }

    public CategoriaReceta(BigDecimal idCategoriaReceta, String descripcionCategoriaReceta, BigInteger cantRecetasDia) {
        this.idCategoriaReceta = idCategoriaReceta;
        this.descripcionCategoriaReceta = descripcionCategoriaReceta;
        this.cantRecetasDia = cantRecetasDia;
    }

    public BigDecimal getIdCategoriaReceta() {
        return idCategoriaReceta;
    }

    public void setIdCategoriaReceta(BigDecimal idCategoriaReceta) {
        this.idCategoriaReceta = idCategoriaReceta;
    }

    public String getDescripcionCategoriaReceta() {
        return descripcionCategoriaReceta;
    }

    public void setDescripcionCategoriaReceta(String descripcionCategoriaReceta) {
        this.descripcionCategoriaReceta = descripcionCategoriaReceta;
    }

    public BigInteger getCantRecetasDia() {
        return cantRecetasDia;
    }

    public void setCantRecetasDia(BigInteger cantRecetasDia) {
        this.cantRecetasDia = cantRecetasDia;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaReceta != null ? idCategoriaReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaReceta)) {
            return false;
        }
        CategoriaReceta other = (CategoriaReceta) object;
        if ((this.idCategoriaReceta == null && other.idCategoriaReceta != null) || (this.idCategoriaReceta != null && !this.idCategoriaReceta.equals(other.idCategoriaReceta))) {
            return false;
        }
        return true;
    }

    
}
