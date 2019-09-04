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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "RECETA")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //declarar que el id se usa con una secuencia 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_REC")
    //declarar la secuencia 
    @SequenceGenerator(name="SEQ_REC",sequenceName="SEQ_RECETA", allocationSize=1 )
    
    @NotNull
    @Column(name = "ID_RECETA")
    private BigDecimal idReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_RECETA")
    private String nombreReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION_RECETA")
    private String descripcionReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DURACION_PREPARACION")
    private BigInteger duracionPreparacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISPONIBILIDAD_RECETA")
    private BigInteger disponibilidadReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_RECETA")
    private BigInteger precioReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_PREP_DIARIA_RECETA")
    private BigInteger cantidadPrepDiariaReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FOTO")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_RECETA")
    private String tipoReceta;
    @JoinColumn(name = "ID_CATEGORIA_RECETA", referencedColumnName = "ID_CATEGORIA_RECETA")
    @ManyToOne(optional = false)
    private CategoriaReceta idCategoriaReceta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReceta")
    private Collection<RecetaOrdenada> recetaOrdenadaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReceta")
    private Collection<Ingrediente> ingredienteCollection;

    public Receta() {
    }

    public Receta(BigDecimal idReceta) {
        this.idReceta = idReceta;
    }

    public Receta(BigDecimal idReceta, String nombreReceta, String descripcionReceta, BigInteger duracionPreparacion, BigInteger disponibilidadReceta, BigInteger precioReceta, BigInteger cantidadPrepDiariaReceta, String foto, String tipoReceta) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.duracionPreparacion = duracionPreparacion;
        this.disponibilidadReceta = disponibilidadReceta;
        this.precioReceta = precioReceta;
        this.cantidadPrepDiariaReceta = cantidadPrepDiariaReceta;
        this.foto = foto;
        this.tipoReceta = tipoReceta;
    }

    public BigDecimal getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(BigDecimal idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public BigInteger getDuracionPreparacion() {
        return duracionPreparacion;
    }

    public void setDuracionPreparacion(BigInteger duracionPreparacion) {
        this.duracionPreparacion = duracionPreparacion;
    }

    public BigInteger getDisponibilidadReceta() {
        return disponibilidadReceta;
    }

    public void setDisponibilidadReceta(BigInteger disponibilidadReceta) {
        this.disponibilidadReceta = disponibilidadReceta;
    }

    public BigInteger getPrecioReceta() {
        return precioReceta;
    }

    public void setPrecioReceta(BigInteger precioReceta) {
        this.precioReceta = precioReceta;
    }

    public BigInteger getCantidadPrepDiariaReceta() {
        return cantidadPrepDiariaReceta;
    }

    public void setCantidadPrepDiariaReceta(BigInteger cantidadPrepDiariaReceta) {
        this.cantidadPrepDiariaReceta = cantidadPrepDiariaReceta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(String tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public CategoriaReceta getIdCategoriaReceta() {
        return idCategoriaReceta;
    }

    public void setIdCategoriaReceta(CategoriaReceta idCategoriaReceta) {
        this.idCategoriaReceta = idCategoriaReceta;
    }

    @XmlTransient
    public Collection<RecetaOrdenada> getRecetaOrdenadaCollection() {
        return recetaOrdenadaCollection;
    }

    public void setRecetaOrdenadaCollection(Collection<RecetaOrdenada> recetaOrdenadaCollection) {
        this.recetaOrdenadaCollection = recetaOrdenadaCollection;
    }

    @XmlTransient
    public Collection<Ingrediente> getIngredienteCollection() {
        return ingredienteCollection;
    }

    public void setIngredienteCollection(Collection<Ingrediente> ingredienteCollection) {
        this.ingredienteCollection = ingredienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReceta != null ? idReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.idReceta == null && other.idReceta != null) || (this.idReceta != null && !this.idReceta.equals(other.idReceta))) {
            return false;
        }
        return true;
    }
    
}
