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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "PROVEEDOR")
public class Proveedor implements Serializable {

    
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    
    
    @Id
    @Basic(optional = false)
    
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PROVEEDOR")
    //declarar la secuencia 
    @SequenceGenerator(name="SEQ_PROVEEDOR",sequenceName="SEQ_PROVEEDOR", allocationSize=1 )
    @NotNull
    @Column(name = "ID_PROVEEDOR")
    private BigDecimal idProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RUT_PROVEEDOR")
    private String rutProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DIRECCION_PROVEEDOR")
    private String direccionProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "TELEFONO_PROVEEDOR")
    private String telefonoProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CONTACTO_VENTA")
    private String contactoVenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TIPO_PROVEEDOR")
    private String tipoProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CORREO_PROVEEDOR")
    private String correoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CELULAR_PROVEEDOR")
    private Integer celularProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CATEGORIA_PROVEEDOR")
    private String categoriaProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private Collection<InsumoProveedor> insumoProveedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private Collection<Pedido> pedidoCollection;

     public static final String P_ID_PROVEEDOR = "P_ID_PROVEEDOR";
     public static final String P_RUT_PROVEEDOR = "P_RUT_PROVEEDOR";
     public static final String P_NOMBRE_PROVEEDOR = "P_NOMBRE_PROVEEDOR";
     public static final String P_DIRECCION_PROVEEDOR = "P_DIRECCION_PROVEEDOR";
     public static final String P_TELEFONO_PROVEEDOR = "P_TELEFONO_PROVEEDOR";
     public static final String P_CONTACTO_VENTA = "P_CONTACTO_VENTA";
     public static final String P_TIPO_PROVEEDOR = "P_TIPO_PROVEEDOR";
     public static final String P_CORREO_PROVEEDOR = "P_CORREO_PROVEEDOR";
     public static final String P_CELULAR_PROVEEDOR = "P_CELULAR_PROVEEDOR";
     public static final String P_CATEGORIA_PROVEEDOR = "P_CATEGORIA_PROVEEDOR";
     
     
    public Proveedor() {
    }

    public Proveedor(BigDecimal idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(BigDecimal idProveedor, String rutProveedor, String nombreProveedor, String direccionProveedor, String telefonoProveedor, String contactoVenta, String tipoProveedor, String correoProveedor, Integer celularProveedor, String categoriaProveedor) {
        this.idProveedor = idProveedor;
        this.rutProveedor = rutProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccionProveedor = direccionProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.contactoVenta = contactoVenta;
        this.tipoProveedor = tipoProveedor;
        this.correoProveedor = correoProveedor;
        this.celularProveedor = celularProveedor;
        this.categoriaProveedor = categoriaProveedor;
    }

    public BigDecimal getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(BigDecimal idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public void setRutProveedor(String rutProveedor) {
        this.rutProveedor = rutProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getContactoVenta() {
        return contactoVenta;
    }

    public void setContactoVenta(String contactoVenta) {
        this.contactoVenta = contactoVenta;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public long getCelularProveedor() {
        return celularProveedor;
    }

    public void setCelularProveedor(Integer celularProveedor) {
        this.celularProveedor = celularProveedor;
    }

    public String getCategoriaProveedor() {
        return categoriaProveedor;
    }

    public void setCategoriaProveedor(String categoriaProveedor) {
        this.categoriaProveedor = categoriaProveedor;
    }

    @XmlTransient
    public Collection<InsumoProveedor> getInsumoProveedorCollection() {
        return insumoProveedorCollection;
    }

    public void setInsumoProveedorCollection(Collection<InsumoProveedor> insumoProveedorCollection) {
        this.insumoProveedorCollection = insumoProveedorCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }
    
}
