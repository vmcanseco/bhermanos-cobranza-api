/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canseco.victor
 */
@Entity
@Table(name = "distribuidor", catalog = "bhermanos", schema = "",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Numero"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distribuidor.findAll", query = "SELECT d FROM Distribuidor d")
         ,@NamedQuery(name = "Distribuidor.findAllActivos", query = "SELECT d FROM Distribuidor d where d.activo='Y'")
    , @NamedQuery(name = "Distribuidor.findById", query = "SELECT d FROM Distribuidor d WHERE d.id = :id")
    , @NamedQuery(name = "Distribuidor.findByNumero", query = "SELECT d FROM Distribuidor d WHERE d.numero = :numero")
    , @NamedQuery(name = "Distribuidor.findByNombre", query = "SELECT d FROM Distribuidor d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Distribuidor.findByFechaAlta", query = "SELECT d FROM Distribuidor d WHERE d.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Distribuidor.findByActivo", query = "SELECT d FROM Distribuidor d WHERE d.activo = :activo")
    , @NamedQuery(name = "Distribuidor.findByFechaCreacion", query = "SELECT d FROM Distribuidor d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Distribuidor.findByFechaModificacion", query = "SELECT d FROM Distribuidor d WHERE d.fechaModificacion = :fechaModificacion")})
public class Distribuidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String activo;
    @Basic(optional = false)
    @NotNull
    @Column(updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDistribuidor")
    @JsonIgnore
    private List<Vales> valesCollection;

    public Distribuidor() {
    }

    public Distribuidor(Integer id) {
        this.id = id;
    }

    public Distribuidor(Integer id, String numero, String nombre, Date fechaAlta, String activo, Date fechaCreacion) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    @XmlTransient
    public List<Vales> getValesCollection() {
        return valesCollection;
    }

    public void setValesCollection(List<Vales> valesCollection) {
        this.valesCollection = valesCollection;
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
        if (!(object instanceof Distribuidor)) {
            return false;
        }
        Distribuidor other = (Distribuidor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bhermanos.cobranza.api.Distribuidor[ id=" + id + " ]";
    }
    
}
