/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.bhermanos.cobranza.db.Clientes;
import com.bhermanos.cobranza.db.Pagos;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canseco.victor
 */
@Entity
@Table(name = "historial_pagos", catalog = "bhermanos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialPagos.findAll", query = "SELECT h FROM HistorialPagos h")
    , @NamedQuery(name = "HistorialPagos.findById", query = "SELECT h FROM HistorialPagos h WHERE h.id = :id")
    , @NamedQuery(name = "HistorialPagos.findByFormaPago", query = "SELECT h FROM HistorialPagos h WHERE h.formaPago = :formaPago")
    , @NamedQuery(name = "HistorialPagos.findByMontoPagado", query = "SELECT h FROM HistorialPagos h WHERE h.montoPagado = :montoPagado")
    , @NamedQuery(name = "HistorialPagos.findByInteresesPagados", query = "SELECT h FROM HistorialPagos h WHERE h.interesesPagados = :interesesPagados")
    , @NamedQuery(name = "HistorialPagos.findByFechaPago", query = "SELECT h FROM HistorialPagos h WHERE h.fechaPago = :fechaPago")
    , @NamedQuery(name = "HistorialPagos.findByComentarios", query = "SELECT h FROM HistorialPagos h WHERE h.comentarios = :comentarios")
    , @NamedQuery(name = "HistorialPagos.findByFechaCreacion", query = "SELECT h FROM HistorialPagos h WHERE h.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "HistorialPagos.findByFechaActualizacion", query = "SELECT h FROM HistorialPagos h WHERE h.fechaActualizacion = :fechaActualizacion")})
public class HistorialPagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String formaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal montoPagado;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal interesesPagados;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Size(max = 1000)
    @Column(length = 1000)
    private String comentarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String fechaCreacion;
    @Size(max = 45)
    @Column(length = 45)
    private String fechaActualizacion;
    @JoinColumn(name = "IdCliente", referencedColumnName = "Id")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "IdPago", referencedColumnName = "Id")
    @ManyToOne
    private Pagos idPago;

    public HistorialPagos() {
    }

    public HistorialPagos(Integer id) {
        this.id = id;
    }

    public HistorialPagos(Integer id, String formaPago, BigDecimal montoPagado, BigDecimal interesesPagados, Date fechaPago, String fechaCreacion) {
        this.id = id;
        this.formaPago = formaPago;
        this.montoPagado = montoPagado;
        this.interesesPagados = interesesPagados;
        this.fechaPago = fechaPago;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public BigDecimal getInteresesPagados() {
        return interesesPagados;
    }

    public void setInteresesPagados(BigDecimal interesesPagados) {
        this.interesesPagados = interesesPagados;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Pagos getIdPago() {
        return idPago;
    }

    public void setIdPago(Pagos idPago) {
        this.idPago = idPago;
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
        if (!(object instanceof HistorialPagos)) {
            return false;
        }
        HistorialPagos other = (HistorialPagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.db.temp.HistorialPagos[ id=" + id + " ]";
    }
    
}
