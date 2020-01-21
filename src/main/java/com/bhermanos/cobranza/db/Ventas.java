/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.bhermanos.cobranza.db.Vales;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "ventas", catalog = "bhermanos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findById", query = "SELECT v FROM Ventas v WHERE v.id = :id")
    , @NamedQuery(name = "Ventas.findByMonto", query = "SELECT v FROM Ventas v WHERE v.monto = :monto")
    , @NamedQuery(name = "Ventas.findByPlazo", query = "SELECT v FROM Ventas v WHERE v.plazo = :plazo")
    , @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Ventas.findByMontoPagado", query = "SELECT v FROM Ventas v WHERE v.montoPagado = :montoPagado")
    , @NamedQuery(name = "Ventas.findByMontoDisponible", query = "SELECT v FROM Ventas v WHERE v.montoDisponible = :montoDisponible")
    , @NamedQuery(name = "Ventas.findByPagada", query = "SELECT v FROM Ventas v WHERE v.pagada = :pagada")
    , @NamedQuery(name = "Ventas.findByIntereses", query = "SELECT v FROM Ventas v WHERE v.intereses = :intereses")
    , @NamedQuery(name = "Ventas.findByUltimoPago", query = "SELECT v FROM Ventas v WHERE v.ultimoPago = :ultimoPago")
    , @NamedQuery(name = "Ventas.findByFechaCreacion", query = "SELECT v FROM Ventas v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Ventas.findByFechaModificacion", query = "SELECT v FROM Ventas v WHERE v.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Ventas.findByClientId", query = "SELECT v FROM Ventas v WHERE v.idVale.idCliente.id = :idCliente")})

public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Monto", nullable = false, precision = 7, scale = 2)
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Plazo", nullable = false)
    private int plazo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DiaPago", nullable = false)
    private int diaPago;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "mesInicioPago", nullable = false)
    private int mesInicioPago;    
    @Basic(optional = false)
    @NotNull
     @Column(name = "PagoMinimo", nullable = false, precision = 7, scale = 2)
    private BigDecimal pagoMinimo;    
    
    @Basic(optional = true)
    @NotNull
     @Column(name = "PagoFinal", nullable = true, precision = 7, scale = 2)
    private BigDecimal pagoFinal;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MontoPagado", nullable = false, precision = 7, scale = 2)
    private BigDecimal montoPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MontoDisponible", nullable = false, precision = 7, scale = 2)
    private BigDecimal montoDisponible;
    @Size(max = 1)
    @Column(name = "Pagada", length = 1)
    private String pagada;
    @Column(name = "Intereses", precision = 7, scale = 2)
    private BigDecimal intereses;
    @Column(name = "PrimerPago")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date primerPago;
    @Column(name = "UltimoPago")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date ultimoPago;
    @Basic(optional = false)
    @NotNull
    @Column(updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;
    @Column(name = "FechaModificacion", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaModificacion;
    @JoinColumn(name = "IdVale", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    @JsonFilter("voucherFilter")
    private Vales idVale;

    public Ventas() {
    }

    public Ventas(Integer id) {
        this.id = id;
    }

    public Ventas(Integer id, BigDecimal monto, int plazo, Date fecha, BigDecimal montoPagado, BigDecimal montoDisponible, Date fechaCreacion) {
        this.id = id;
        this.monto = monto;
        this.plazo = plazo;
        this.fecha = fecha;
        this.montoPagado = montoPagado;
        this.montoDisponible = montoDisponible;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public BigDecimal getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(BigDecimal montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public String getPagada() {
        return pagada;
    }

    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public BigDecimal getIntereses() {
        return intereses;
    }

    public void setIntereses(BigDecimal intereses) {
        this.intereses = intereses;
    }

    public Date getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Date ultimoPago) {
        this.ultimoPago = ultimoPago;
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

    public Vales getIdVale() {
        return idVale;
    }

    public void setIdVale(Vales idVale) {
        this.idVale = idVale;
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
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.db.temp.Ventas[ id=" + id + " ]";
    }

    public int getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(int diaPago) {
        this.diaPago = diaPago;
    }

    public int getMesInicioPago() {
        return mesInicioPago;
    }

    public void setMesInicioPago(int mesInicioPago) {
        this.mesInicioPago = mesInicioPago;
    }

    public BigDecimal getPagoMinimo() {
        return pagoMinimo;
    }

    public void setPagoMinimo(BigDecimal pagoMinimo) {
        this.pagoMinimo = pagoMinimo;
    }

    public BigDecimal getPagoFinal() {
        return pagoFinal;
    }

    public void setPagoFinal(BigDecimal pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    public Date getPrimerPago() {
        return primerPago;
    }

    public void setPrimerPago(Date primerPago) {
        this.primerPago = primerPago;
    }
    
    
    

}
