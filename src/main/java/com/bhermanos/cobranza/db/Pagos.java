/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.bhermanos.cobranza.db.Vales;
import com.bhermanos.cobranza.db.Ventas;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@Table(name = "pagos", catalog = "bhermanos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findById", query = "SELECT p FROM Pagos p WHERE p.id = :id")
    , @NamedQuery(name = "Pagos.findByMonto", query = "SELECT p FROM Pagos p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pagos.findByTipoPago", query = "SELECT p FROM Pagos p WHERE p.tipoPago = :tipoPago")
    , @NamedQuery(name = "Pagos.findByPagado", query = "SELECT p FROM Pagos p WHERE p.pagado = :pagado")
    , @NamedQuery(name = "Pagos.findByFechaProgramada", query = "SELECT p FROM Pagos p WHERE p.fechaProgramada = :fechaProgramada")
    , @NamedQuery(name = "Pagos.findByFechaCreacion", query = "SELECT p FROM Pagos p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Pagos.findByFechaActualizacion", query = "SELECT p FROM Pagos p WHERE p.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Pagos.findByLatePayments", query = "SELECT p FROM Pagos p WHERE p.fechaProgramada <= :fechaProgramada AND p.pagado='N'")
    , @NamedQuery(name = "Pagos.findByPaymentsByClientId", query = "SELECT p FROM Pagos p WHERE p.idVenta.idVale.idCliente.id=:idCliente")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int tipoPago;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal intereses;
    @Size(max = 1)
    @Column(length = 1)
    private String pagado;
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
    @Column(name = "FechaProgramada", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaProgramada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaCreacion", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaActualizacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(mappedBy = "idPago")
    private List<HistorialPagos> historialPagosList;
    @JoinColumn(name = "IdVenta", referencedColumnName = "Id")
    @ManyToOne
    @JsonFilter("voucherFilter")
    private Ventas idVenta;

    public Pagos() {
    }

    public Pagos(Integer id) {
        this.id = id;
    }

    public Pagos(Integer id, BigDecimal monto, int tipoPago, Date fechaProgramada, Date fechaCreacion) {
        this.id = id;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.fechaProgramada = fechaProgramada;
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

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public int getNumPago() {
        return numPago;
    }

    public void setNumPago(int numPago) {
        this.numPago = numPago;
    }

    public BigDecimal getIntereses() {
        return intereses;
    }

    public void setIntereses(BigDecimal intereses) {
        this.intereses = intereses;
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

    public List<HistorialPagos> getHistorialPagosList() {
        return historialPagosList;
    }

    public void setHistorialPagosList(List<HistorialPagos> historialPagosList) {
        this.historialPagosList = historialPagosList;
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
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.db.temp.Pagos[ id=" + id + " ]";
    }

}
