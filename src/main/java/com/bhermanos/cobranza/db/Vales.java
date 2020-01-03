/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canseco.victor
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Folio"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vales.findAll", query = "SELECT v FROM Vales v")
    , @NamedQuery(name = "Vales.findById", query = "SELECT v FROM Vales v WHERE v.id = :id")
    , @NamedQuery(name = "Vales.findByFolio", query = "SELECT v FROM Vales v WHERE v.folio = :folio")
    , @NamedQuery(name = "Vales.findByTipo", query = "SELECT v FROM Vales v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "Vales.findByMonto", query = "SELECT v FROM Vales v WHERE v.monto = :monto")
    , @NamedQuery(name = "Vales.findByFecha", query = "SELECT v FROM Vales v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Vales.findByMontoDisponible", query = "SELECT v FROM Vales v WHERE v.montoDisponible = :montoDisponible")
    , @NamedQuery(name = "Vales.findByMontoPagado", query = "SELECT v FROM Vales v WHERE v.montoPagado = :montoPagado")
    , @NamedQuery(name = "Vales.findByUltimoPago", query = "SELECT v FROM Vales v WHERE v.ultimoPago = :ultimoPago")
    , @NamedQuery(name = "Vales.findByPagado", query = "SELECT v FROM Vales v WHERE v.pagado = :pagado")
    , @NamedQuery(name = "Vales.findByFechaCreacion", query = "SELECT v FROM Vales v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Vales.findByFechaModificacion", query = "SELECT v FROM Vales v WHERE v.fechaModificacion = :fechaModificacion")})
public class Vales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String folio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(precision = 7, scale = 2)
    private BigDecimal montoDisponible;
    @Column(precision = 7, scale = 2)
    private BigDecimal montoPagado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String pagado;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "IdCliente", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    private Clientes idCliente;
    @JoinColumn(name = "IdDistribuidor", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    private Distribuidor idDistribuidor;

    public Vales() {
    }

    public Vales(Integer id) {
        this.id = id;
    }

    public Vales(Integer id, String folio, String tipo, BigDecimal monto, Date fecha, String pagado, Date fechaCreacion) {
        this.id = id;
        this.folio = folio;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.pagado = pagado;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(BigDecimal montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Date getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Date ultimoPago) {
        this.ultimoPago = ultimoPago;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Distribuidor getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Distribuidor idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vales)) {
            return false;
        }
        Vales other = (Vales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.api.Vales[ id=" + id + " ]";
    }
    
}
