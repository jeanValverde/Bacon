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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jean
 */
@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEDIDO")
    private BigDecimal idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_PEDIDO")
    private BigInteger valorPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA_PEDIDO")
    private BigInteger ivaPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PEDIDO")
    private BigInteger totalPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DETALLE_PEDIDO")
    private String detallePedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REVISADO_FINANZA")
    private BigInteger revisadoFinanza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MOTIVO_RECHAZO")
    private String motivoRechazo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private Collection<IngresoStock> ingresoStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private Collection<InsumoPedidoProveedor> insumoPedidoProveedorCollection;
    @JoinColumn(name = "ID_ESTADO_PEDIDO", referencedColumnName = "ID_ESTADO_PEDIDO")
    @ManyToOne(optional = false)
    private EstadoPedido idEstadoPedido;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public Pedido() {
    }

    public Pedido(BigDecimal idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(BigDecimal idPedido, Date fechaPedido, BigInteger valorPedido, BigInteger ivaPedido, BigInteger totalPedido, String detallePedido, BigInteger revisadoFinanza, String motivoRechazo) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.valorPedido = valorPedido;
        this.ivaPedido = ivaPedido;
        this.totalPedido = totalPedido;
        this.detallePedido = detallePedido;
        this.revisadoFinanza = revisadoFinanza;
        this.motivoRechazo = motivoRechazo;
    }

    public BigDecimal getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(BigDecimal idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigInteger getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigInteger valorPedido) {
        this.valorPedido = valorPedido;
    }

    public BigInteger getIvaPedido() {
        return ivaPedido;
    }

    public void setIvaPedido(BigInteger ivaPedido) {
        this.ivaPedido = ivaPedido;
    }

    public BigInteger getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigInteger totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(String detallePedido) {
        this.detallePedido = detallePedido;
    }

    public BigInteger getRevisadoFinanza() {
        return revisadoFinanza;
    }

    public void setRevisadoFinanza(BigInteger revisadoFinanza) {
        this.revisadoFinanza = revisadoFinanza;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    @XmlTransient
    public Collection<IngresoStock> getIngresoStockCollection() {
        return ingresoStockCollection;
    }

    public void setIngresoStockCollection(Collection<IngresoStock> ingresoStockCollection) {
        this.ingresoStockCollection = ingresoStockCollection;
    }

    @XmlTransient
    public Collection<InsumoPedidoProveedor> getInsumoPedidoProveedorCollection() {
        return insumoPedidoProveedorCollection;
    }

    public void setInsumoPedidoProveedorCollection(Collection<InsumoPedidoProveedor> insumoPedidoProveedorCollection) {
        this.insumoPedidoProveedorCollection = insumoPedidoProveedorCollection;
    }

    public EstadoPedido getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(EstadoPedido idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }
    
}
