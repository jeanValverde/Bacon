/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "ORDEN")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
      //declarar que el id se usa con una secuencia 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ORDEN")
    //declarar la secuencia 
    @SequenceGenerator(name="SEQ_ORDEN",sequenceName="SEQ_ORDEN", allocationSize=1 )
    @NotNull
    @Column(name = "ID_ORDEN")
    private Integer idOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUB_TOTAL")
    private Integer subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA")
    private Integer iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_ORDEN")
    private Integer totalOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_PREPARACION")
    private Integer tiempoPreparacion;
    @Size(max = 250)
    @Column(name = "MOTIVO_ANULACION")
    private String motivoAnulacion;
    @Column(name = "TIPO_ORDEN")
    private Short tipoOrden;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "ID_ESTADO_ORDEN", referencedColumnName = "ID_ESTADO_ORDEN")
    @ManyToOne(optional = false)
    private EstadoOrden idEstadoOrden;

    public Orden() {
    }

    public Orden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Orden(Integer idOrden, String descripcion, Integer subTotal, Integer iva, Integer totalOrden, Integer tiempoPreparacion) {
        this.idOrden = idOrden;
        this.descripcion = descripcion;
        this.subTotal = subTotal;
        this.iva = iva;
        this.totalOrden = totalOrden;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Integer getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(Integer totalOrden) {
        this.totalOrden = totalOrden;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public Short getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(Short tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public EstadoOrden getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(EstadoOrden idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurante.bacon.dto.Orden[ idOrden=" + idOrden + " ]";
    }
    
}
