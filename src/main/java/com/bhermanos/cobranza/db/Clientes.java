/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canseco.victor
 */
@Entity
@Table(name = "clientes", catalog = "", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Numero"})
    , @UniqueConstraint(columnNames = {"Id"})
    , @UniqueConstraint(columnNames = {"ine"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findById", query = "SELECT c FROM Clientes c WHERE c.id = :id")
    , @NamedQuery(name = "Clientes.findByNumero", query = "SELECT c FROM Clientes c WHERE c.numero = :numero")
    , @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Clientes.findByApellidoPaterno", query = "SELECT c FROM Clientes c WHERE c.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Clientes.findByApellidoMaterno", query = "SELECT c FROM Clientes c WHERE c.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Clientes.findByCalle", query = "SELECT c FROM Clientes c WHERE c.calle = :calle")
    , @NamedQuery(name = "Clientes.findByNumeroExt", query = "SELECT c FROM Clientes c WHERE c.numeroExt = :numeroExt")
    , @NamedQuery(name = "Clientes.findByNumInt", query = "SELECT c FROM Clientes c WHERE c.numInt = :numInt")
    , @NamedQuery(name = "Clientes.findByColonia", query = "SELECT c FROM Clientes c WHERE c.colonia = :colonia")
    , @NamedQuery(name = "Clientes.findByCp", query = "SELECT c FROM Clientes c WHERE c.cp = :cp")
    , @NamedQuery(name = "Clientes.findByEstado", query = "SELECT c FROM Clientes c WHERE c.estado = :estado")
    , @NamedQuery(name = "Clientes.findByMunicipio", query = "SELECT c FROM Clientes c WHERE c.municipio = :municipio")
    , @NamedQuery(name = "Clientes.findByCiudad", query = "SELECT c FROM Clientes c WHERE c.ciudad = :ciudad")
    , @NamedQuery(name = "Clientes.findByIne", query = "SELECT c FROM Clientes c WHERE c.ine = :ine")
    , @NamedQuery(name = "Clientes.findByTelCasa", query = "SELECT c FROM Clientes c WHERE c.telCasa = :telCasa")
    , @NamedQuery(name = "Clientes.findByMovil1", query = "SELECT c FROM Clientes c WHERE c.movil1 = :movil1")
    , @NamedQuery(name = "Clientes.findByMovil2", query = "SELECT c FROM Clientes c WHERE c.movil2 = :movil2")
    , @NamedQuery(name = "Clientes.findByMovil3", query = "SELECT c FROM Clientes c WHERE c.movil3 = :movil3")
    , @NamedQuery(name = "Clientes.findByFacebook", query = "SELECT c FROM Clientes c WHERE c.facebook = :facebook")
    , @NamedQuery(name = "Clientes.findByFechaAlta", query = "SELECT c FROM Clientes c WHERE c.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Clientes.findByFoto", query = "SELECT c FROM Clientes c WHERE c.foto = :foto")
    , @NamedQuery(name = "Clientes.findByIdExterno", query = "SELECT c FROM Clientes c WHERE c.idExterno = :idExterno")
    , @NamedQuery(name = "Clientes.findByActivo", query = "SELECT c FROM Clientes c WHERE c.activo = :activo")
    , @NamedQuery(name = "Clientes.findByFechaCreacion", query = "SELECT c FROM Clientes c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Clientes.findByFechaModificacion", query = "SELECT c FROM Clientes c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Clientes.validateExistingClient", query = "SELECT count(c) FROM Clientes c WHERE c.ine = :ine OR c.numero=:numero")
    ,@NamedQuery(name = "Clientes.findByPendingPayments", query = "SELECT distinct p.idVenta.idVale.idCliente FROM Pagos p WHERE p.pagado='N'")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String apellidoPaterno;
    @Size(max = 50)
    @Column(length = 50)
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String numeroExt;
    @Size(max = 5)
    @Column(length = 5)
    private String numInt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String colonia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String cp;
    @Size(max = 100)
    @Column(length = 100)
    private String estado;
    @Size(max = 100)
    @Column(length = 100)
    private String municipio;
    @Size(max = 100)
    @Column(length = 100)
    private String ciudad;
    @Size(max = 100)
    @Column(length = 100)
    private String zona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String ine;
    @Size(max = 20)
    @Column(length = 20)
    private String telCasa;
    @Size(max = 20)
    @Column(length = 20)
    private String movil1;
    @Size(max = 20)
    @Column(length = 20)
    private String movil2;
    @Size(max = 20)
    @Column(length = 20)
    private String movil3;
    @Size(max = 100)
    @Column(length = 100)
    private String correoElectronico;
    @Size(max = 100)
    @Column(length = 100)
    private String facebook;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaAlta;
    @Size(max = 500)
    @Column(length = 500)
    private String foto;
    @Size(max = 50)
    @Column(length = 50)
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String activo;
    @Basic(optional = false)
    @NotNull
    @Column(updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;
    /*@Column(nullable = true, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT ON UPDATE CURRENT_TIMESTAMP")*/
    @Column(name = "FechaModificacion", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    @JsonIgnoreProperties("idCliente")
    private List<Vales> valesCollection;
    @OneToMany(mappedBy = "idCliente")
    @JsonIgnoreProperties("idCliente")
    private List<HistorialPagos> historialPagosList;

    @Transient
    private transient List<Pagos> pagos;
    public Clientes() {
    }

    public Clientes(Integer id) {
        this.id = id;
    }

    public Clientes(Integer id, int numero, String nombre, String apellidoPaterno, String calle, String numeroExt, String colonia, String cp, String ine, Date fechaAlta, String activo, Date fechaCreacion) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.calle = calle;
        this.numeroExt = numeroExt;
        this.colonia = colonia;
        this.cp = cp;
        this.ine = ine;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    public String getTelCasa() {
        return telCasa;
    }

    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }

    public String getMovil1() {
        return movil1;
    }

    public void setMovil1(String movil1) {
        this.movil1 = movil1;
    }

    public String getMovil2() {
        return movil2;
    }

    public void setMovil2(String movil2) {
        this.movil2 = movil2;
    }

    public String getMovil3() {
        return movil3;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setMovil3(String movil3) {
        this.movil3 = movil3;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    //@XmlTransient
    public List<Vales> getValesCollection() {
        return valesCollection;
    }

    public void setValesCollection(List<Vales> valesCollection) {
        this.valesCollection = valesCollection;
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
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.api.Clientes[ id=" + id + " ]";
    }

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    @XmlTransient
    @XmlElement(name = "debito-disponible")
    public BigDecimal getDebitoDisponible() {
        if (this.historialPagosList != null) {
            return this.historialPagosList.stream().filter(h->h.getIdPago()==null).map(h -> h.getMontoPagado()).reduce(BigDecimal.ZERO, (t, u) -> {
                return t.add(u);
            });
        }
        return BigDecimal.ZERO;
    }
}
