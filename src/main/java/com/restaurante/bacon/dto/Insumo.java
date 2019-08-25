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
@Table(name = "INSUMO")
public class Insumo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INSUMO")
    private BigDecimal idInsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_INSUMO")
    private String nombreInsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION_INSUMO")
    private String descripcionInsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_INSUMO")
    private BigInteger stockInsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "UNIDAD_MEDIDA_INSUMO")
    private String unidadMedidaInsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MINIMO_STOCK_INSUMO")
    private BigInteger minimoStockInsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXIMO_STOCK_INSUMO")
    private BigInteger maximoStockInsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FOTO_INSUMO")
    private String fotoInsumo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private Collection<EgresoStock> egresoStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private Collection<IngresoStock> ingresoStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private Collection<InsumoPedido> insumoPedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private Collection<InsumoProveedor> insumoProveedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private Collection<Ingrediente> ingredienteCollection;

    public Insumo() {
    }

    public Insumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Insumo(BigDecimal idInsumo, String nombreInsumo, String descripcionInsumo, BigInteger stockInsumo, String unidadMedidaInsumo, BigInteger minimoStockInsumo, BigInteger maximoStockInsumo, String fotoInsumo) {
        this.idInsumo = idInsumo;
        this.nombreInsumo = nombreInsumo;
        this.descripcionInsumo = descripcionInsumo;
        this.stockInsumo = stockInsumo;
        this.unidadMedidaInsumo = unidadMedidaInsumo;
        this.minimoStockInsumo = minimoStockInsumo;
        this.maximoStockInsumo = maximoStockInsumo;
        this.fotoInsumo = fotoInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public String getDescripcionInsumo() {
        return descripcionInsumo;
    }

    public void setDescripcionInsumo(String descripcionInsumo) {
        this.descripcionInsumo = descripcionInsumo;
    }

    public BigInteger getStockInsumo() {
        return stockInsumo;
    }

    public void setStockInsumo(BigInteger stockInsumo) {
        this.stockInsumo = stockInsumo;
    }

    public String getUnidadMedidaInsumo() {
        return unidadMedidaInsumo;
    }

    public void setUnidadMedidaInsumo(String unidadMedidaInsumo) {
        this.unidadMedidaInsumo = unidadMedidaInsumo;
    }

    public BigInteger getMinimoStockInsumo() {
        return minimoStockInsumo;
    }

    public void setMinimoStockInsumo(BigInteger minimoStockInsumo) {
        this.minimoStockInsumo = minimoStockInsumo;
    }

    public BigInteger getMaximoStockInsumo() {
        return maximoStockInsumo;
    }

    public void setMaximoStockInsumo(BigInteger maximoStockInsumo) {
        this.maximoStockInsumo = maximoStockInsumo;
    }

    public String getFotoInsumo() {
        return fotoInsumo;
    }

    public void setFotoInsumo(String fotoInsumo) {
        this.fotoInsumo = fotoInsumo;
    }

    @XmlTransient
    public Collection<EgresoStock> getEgresoStockCollection() {
        return egresoStockCollection;
    }

    public void setEgresoStockCollection(Collection<EgresoStock> egresoStockCollection) {
        this.egresoStockCollection = egresoStockCollection;
    }

    @XmlTransient
    public Collection<IngresoStock> getIngresoStockCollection() {
        return ingresoStockCollection;
    }

    public void setIngresoStockCollection(Collection<IngresoStock> ingresoStockCollection) {
        this.ingresoStockCollection = ingresoStockCollection;
    }

    @XmlTransient
    public Collection<InsumoPedido> getInsumoPedidoCollection() {
        return insumoPedidoCollection;
    }

    public void setInsumoPedidoCollection(Collection<InsumoPedido> insumoPedidoCollection) {
        this.insumoPedidoCollection = insumoPedidoCollection;
    }

    @XmlTransient
    public Collection<InsumoProveedor> getInsumoProveedorCollection() {
        return insumoProveedorCollection;
    }

    public void setInsumoProveedorCollection(Collection<InsumoProveedor> insumoProveedorCollection) {
        this.insumoProveedorCollection = insumoProveedorCollection;
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
        hash += (idInsumo != null ? idInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.idInsumo == null && other.idInsumo != null) || (this.idInsumo != null && !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        return true;
    }

    
}
