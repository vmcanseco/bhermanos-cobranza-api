/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import com.bhermanos.cobranza.db.Pagos;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canseco.victor
 */
@XmlRootElement(name = "cliente-pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientePagos {
    
    @XmlElement(name = "cliente")
    private Clientes cliente;
    @XmlElement(name = "pagos")
    private List<Pagos> pagos;
    
    private BigDecimal totalVentas;
    private BigDecimal totalPagado;
    private BigDecimal totalDisponible;
    
    public ClientePagos() {
    }
    
    @XmlTransient
    @XmlElement(name = "pago-total")
    public BigDecimal getPagoTotal() {
        
        if (pagos != null) {
            return pagos.stream().map(pago -> {
                return (pago.getMonto().subtract(pago.getMontoPagado())).add((pago.getIntereses().subtract(pago.getInteresesPagados())));
            }).reduce(BigDecimal.ZERO, (t, u) -> {
                return t.add(u); //To change body of generated lambdas, choose Tools | Templates.
            });
        }
        return BigDecimal.ZERO;
    }
    
    @XmlTransient
    @XmlElement(name = "pago")
    public BigDecimal getPago() {
        if (pagos != null) {
            return pagos.stream().map(pago -> {
                return pago.getMonto().subtract(pago.getMontoPagado());
            }).reduce(BigDecimal.ZERO, (t, u) -> {
                return t.add(u); //To change body of generated lambdas, choose Tools | Templates.
            });
        }
        return BigDecimal.ZERO;
    }
    
    @XmlTransient
    @XmlElement(name = "intereses")
    public BigDecimal getIntereses() {
        if (pagos != null) {
            return pagos.stream().map(pago -> {
                return pago.getIntereses().subtract(pago.getInteresesPagados());
            }).reduce(BigDecimal.ZERO, (t, u) -> {
                return t.add(u); //To change body of generated lambdas, choose Tools | Templates.
            });
        }
        return BigDecimal.ZERO;
    }
    
    public Clientes getCliente() {
        return cliente;
    }
    
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public List<Pagos> getPagos() {
        return pagos;
    }
    
    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    
    @XmlTransient
    @XmlElement(name = "total-ventas")
    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    @XmlTransient
    @XmlElement(name = "total-pagado")
    public BigDecimal getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(BigDecimal totalPagado) {
        this.totalPagado = totalPagado;
    }

    @XmlTransient
    @XmlElement(name = "total-pendiente-pago")
    public BigDecimal getTotalDisponible() {
        return totalDisponible;
    }

    public void setTotalDisponible(BigDecimal totalDisponible) {
        this.totalDisponible = totalDisponible;
    }
    
    
    
}
