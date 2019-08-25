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
import javax.persistence.OneToOne;
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
@Table(name = "BOLETA")
public class Boleta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BOLETA")
    private BigDecimal idBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_MESA")
    private BigInteger totalMesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_PAGADO")
    private BigInteger montoPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VUELTO")
    private BigInteger vuelto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "NUMERO_TARJETA")
    private BigInteger numeroTarjeta;
    @Column(name = "MES_VENCIMIENTO")
    private BigInteger mesVencimiento;
    @Column(name = "ANIO_VENCIMIENTO")
    private BigInteger anioVencimiento;
    @Column(name = "CVV_SEGURIDAD")
    private BigInteger cvvSeguridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_EXTRA")
    private BigInteger montoExtra;
    @Size(max = 255)
    @Column(name = "MOTIVO_MONTO_EXTRA")
    private String motivoMontoExtra;
    @Column(name = "OPERACION")
    private BigInteger operacion;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @OneToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "ID_MEDIO_PAGO", referencedColumnName = "ID_MEDIO_PAGO")
    @ManyToOne(optional = false)
    private MedioPago idMedioPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBoleta")
    private Collection<BoletaControl> boletaControlCollection;

    public Boleta() {
    }

    public Boleta(BigDecimal idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Boleta(BigDecimal idBoleta, BigInteger totalMesa, BigInteger montoPagado, BigInteger vuelto, Date fecha, BigInteger montoExtra) {
        this.idBoleta = idBoleta;
        this.totalMesa = totalMesa;
        this.montoPagado = montoPagado;
        this.vuelto = vuelto;
        this.fecha = fecha;
        this.montoExtra = montoExtra;
    }

    public BigDecimal getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(BigDecimal idBoleta) {
        this.idBoleta = idBoleta;
    }

    public BigInteger getTotalMesa() {
        return totalMesa;
    }

    public void setTotalMesa(BigInteger totalMesa) {
        this.totalMesa = totalMesa;
    }

    public BigInteger getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigInteger montoPagado) {
        this.montoPagado = montoPagado;
    }

    public BigInteger getVuelto() {
        return vuelto;
    }

    public void setVuelto(BigInteger vuelto) {
        this.vuelto = vuelto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(BigInteger numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public BigInteger getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(BigInteger mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public BigInteger getAnioVencimiento() {
        return anioVencimiento;
    }

    public void setAnioVencimiento(BigInteger anioVencimiento) {
        this.anioVencimiento = anioVencimiento;
    }

    public BigInteger getCvvSeguridad() {
        return cvvSeguridad;
    }

    public void setCvvSeguridad(BigInteger cvvSeguridad) {
        this.cvvSeguridad = cvvSeguridad;
    }

    public BigInteger getMontoExtra() {
        return montoExtra;
    }

    public void setMontoExtra(BigInteger montoExtra) {
        this.montoExtra = montoExtra;
    }

    public String getMotivoMontoExtra() {
        return motivoMontoExtra;
    }

    public void setMotivoMontoExtra(String motivoMontoExtra) {
        this.motivoMontoExtra = motivoMontoExtra;
    }

    public BigInteger getOperacion() {
        return operacion;
    }

    public void setOperacion(BigInteger operacion) {
        this.operacion = operacion;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public MedioPago getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(MedioPago idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    @XmlTransient
    public Collection<BoletaControl> getBoletaControlCollection() {
        return boletaControlCollection;
    }

    public void setBoletaControlCollection(Collection<BoletaControl> boletaControlCollection) {
        this.boletaControlCollection = boletaControlCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoleta != null ? idBoleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleta)) {
            return false;
        }
        Boleta other = (Boleta) object;
        if ((this.idBoleta == null && other.idBoleta != null) || (this.idBoleta != null && !this.idBoleta.equals(other.idBoleta))) {
            return false;
        }
        return true;
    }

    
    
}
