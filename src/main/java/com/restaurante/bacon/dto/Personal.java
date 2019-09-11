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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "PERSONAL")
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //declarar que el id se usa con una secuencia 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PER")
    //declarar la secuencia 
    @SequenceGenerator(name="SEQ_PER",sequenceName="SEQ_PERSONAL", allocationSize=1 )
    @NotNull
    @Column(name = "ID_PERSONAL")
    private BigDecimal idPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RUT_PERSONAL")
    private String rutPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRES_PERSONAL")
    private String nombresPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "APE_PATERNO_PERSONAL")
    private String apePaternoPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "APE_MATERNO_PERSONAL")
    private String apeMaternoPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO_PERSONAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimientoPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CELULAR_PERSONAL")
    private String celularPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONTRASENA_PERSONAL")
    private String contrasenaPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_PERSONAL")
    private BigInteger estadoPersonal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonal")
    private Collection<ControlCaja> controlCajaCollection;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false)
    private Rol idRol;
    
    
    public static final String P_ID_PERSONAL = "P_ID_PERSONAL";
    public static final String P_RUT_PERSONAL = "P_RUT_PERSONAL";
    public static final String P_NOMBRES_PERSONAL = "P_NOMBRES_PERSONAL";
    public static final String P_APE_PATERNO_PERSONAL = "P_APE_PATERNO_PERSONAL";
    public static final String P_APE_MATERNO_PERSONAL = "P_APE_MATERNO_PERSONAL";
    public static final String P_FECHA_NACIMIENTO_PERSONAL = "P_FECHA_NACIMIENTO_PERSONAL";
    public static final String P_CELULAR_PERSONAL = "P_CELULAR_PERSONAL";
    public static final String P_CORREO_PERSONAL = "P_CORREO_PERSONAL";
    public static final String P_CONTRASENA_PERSONAL = "P_CONTRASENA_PERSONAL";
    public static final String P_ESTADO_PERSONAL = "P_ESTADO_PERSONAL";
    public static final String P_ID_ROL = "P_ID_ROL";
                    
    
    public Personal() {
    }

    public Personal(BigDecimal idPersonal) {
        this.idPersonal = idPersonal;
    }
    
    
    public Personal(BigDecimal idPersonal, String rutPersonal, String nombresPersonal, String apePaternoPersonal, String apeMaternoPersonal, Date fechaNacimientoPersonal, String celularPersonal, String correoPersonal, String contrasenaPersonal, BigInteger estadoPersonal, Collection<ControlCaja> controlCajaCollection, Rol idRol) {
        this.idPersonal = idPersonal;
        this.rutPersonal = rutPersonal;
        this.nombresPersonal = nombresPersonal;
        this.apePaternoPersonal = apePaternoPersonal;
        this.apeMaternoPersonal = apeMaternoPersonal;
        this.fechaNacimientoPersonal = fechaNacimientoPersonal;
        this.celularPersonal = celularPersonal;
        this.correoPersonal = correoPersonal;
        this.contrasenaPersonal = contrasenaPersonal;
        this.estadoPersonal = estadoPersonal;
        this.controlCajaCollection = controlCajaCollection;
        this.idRol = idRol;
    }

    public BigDecimal getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(BigDecimal idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getRutPersonal() {
        return rutPersonal;
    }

    public void setRutPersonal(String rutPersonal) {
        this.rutPersonal = rutPersonal;
    }

    public String getNombresPersonal() {
        return nombresPersonal;
    }

    public void setNombresPersonal(String nombresPersonal) {
        this.nombresPersonal = nombresPersonal;
    }

    public String getApePaternoPersonal() {
        return apePaternoPersonal;
    }

    public void setApePaternoPersonal(String apePaternoPersonal) {
        this.apePaternoPersonal = apePaternoPersonal;
    }

    public String getApeMaternoPersonal() {
        return apeMaternoPersonal;
    }

    public void setApeMaternoPersonal(String apeMaternoPersonal) {
        this.apeMaternoPersonal = apeMaternoPersonal;
    }

    public Date getFechaNacimientoPersonal() {
        return fechaNacimientoPersonal;
    }

    public void setFechaNacimientoPersonal(Date fechaNacimientoPersonal) {
        this.fechaNacimientoPersonal = fechaNacimientoPersonal;
    }

    public String getCelularPersonal() {
        return celularPersonal;
    }

    public void setCelularPersonal(String celularPersonal) {
        this.celularPersonal = celularPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getContrasenaPersonal() {
        return contrasenaPersonal;
    }

    public void setContrasenaPersonal(String contrasenaPersonal) {
        this.contrasenaPersonal = contrasenaPersonal;
    }

    public BigInteger getEstadoPersonal() {
        return estadoPersonal;
    }

    public void setEstadoPersonal(BigInteger estadoPersonal) {
        this.estadoPersonal = estadoPersonal;
    }

    
   
    @XmlTransient
    public Collection<ControlCaja> getControlCajaCollection() {
        return controlCajaCollection;
    }

    public void setControlCajaCollection(Collection<ControlCaja> controlCajaCollection) {
        this.controlCajaCollection = controlCajaCollection;
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
        hash += (idPersonal != null ? idPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idPersonal == null && other.idPersonal != null) || (this.idPersonal != null && !this.idPersonal.equals(other.idPersonal))) {
            return false;
        }
        return true;
    }

   
    
    
}
