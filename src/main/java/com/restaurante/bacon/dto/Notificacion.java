/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "NOTIFICACION")
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //declarar que el id se usa con una secuencia 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTI")
    //declarar la secuencia 
    @SequenceGenerator(name = "SEQ_NOTI", sequenceName = "SEQ_NOTIFICACION", allocationSize = 1)

    @NotNull
    @Column(name = "ID_NOTIFICACION")
    private BigDecimal idNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private BigInteger estado;

    @Basic(optional = false)
    
    @Column(name = "FECHA")
    private Date fechaNotificacion;
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ASUNTO")
    private String asunto;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false)
    private Rol idRol;

    public static String N_ID_NOTIFICACION = "N_ID_NOTIFICACION";
    public static String N_DESCRIPCION = "N_DESCRIPCION";
    public static String N_ESTADO = "N_ESTADO";
    public static String N_ID_ROL = "N_ID_ROL";
    public static String N_ASUNTO = "N_ASUNTO";

    public Notificacion() {
    }

    public Notificacion(BigDecimal idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificacion(BigDecimal idNotificacion, String descripcion, Date fechaNotificacion, BigInteger estado, String asunto) {
        this.idNotificacion = idNotificacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.asunto = asunto;
        this.fechaNotificacion = fechaNotificacion;
    }

    public BigDecimal getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(BigDecimal idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;

    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
        this.estado = estado;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;

    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

}
