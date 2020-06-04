/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author canseco.victor
 */
public class InteresPagos {

    private int idCliente;
    private int codigoCliente;
    private String nombreCliente;
    private String apellidoPaternoCliente;
    private String apellidoMaternoCliente;
    private int idVale;
    private String tipoVale;
    private String folioVale;
    private int idVenta;
    private int diasInteresVenta;
    private int tasaInteresVenta;
    private BigDecimal montoVenta;
    private BigDecimal pagoMinimoVenta;
    private BigDecimal pagoFinalVenta;
    private int idPago;
    private int numPago;
    private BigDecimal montoPago;
    private BigDecimal montoPagadoPago;
    private BigDecimal montoPendientePago;
    private BigDecimal interesesPago;
    private BigDecimal interesesPagadosPago;
    private BigDecimal interesBasePago;
    private BigDecimal interesAcumulados;
    private int diasAtrasoPago;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaProgramadoPago;

    public InteresPagos() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoPaternoCliente() {
        return apellidoPaternoCliente;
    }

    public void setApellidoPaternoCliente(String apellidoPaternoCliente) {
        this.apellidoPaternoCliente = apellidoPaternoCliente;
    }

    public String getApellidoMaternoCliente() {
        return apellidoMaternoCliente;
    }

    public void setApellidoMaternoCliente(String apellidoMaternoCliente) {
        this.apellidoMaternoCliente = apellidoMaternoCliente;
    }

    public int getIdVale() {
        return idVale;
    }

    public void setIdVale(int idVale) {
        this.idVale = idVale;
    }

    public String getTipoVale() {
        return tipoVale;
    }

    public void setTipoVale(String tipoVale) {
        this.tipoVale = tipoVale;
    }

    public String getFolioVale() {
        return folioVale;
    }

    public void setFolioVale(String folioVale) {
        this.folioVale = folioVale;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getDiasInteresVenta() {
        return diasInteresVenta;
    }

    public void setDiasInteresVenta(int diasInteresVenta) {
        this.diasInteresVenta = diasInteresVenta;
    }

    public int getTasaInteresVenta() {
        return tasaInteresVenta;
    }

    public void setTasaInteresVenta(int tasaInteresVenta) {
        this.tasaInteresVenta = tasaInteresVenta;
    }

    public BigDecimal getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(BigDecimal montoVenta) {
        this.montoVenta = montoVenta;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNumPago() {
        return numPago;
    }

    public void setNumPago(int numPago) {
        this.numPago = numPago;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public BigDecimal getInteresesPago() {
        return interesesPago;
    }

    public void setInteresesPago(BigDecimal interesesPago) {
        this.interesesPago = interesesPago;
    }

    public BigDecimal getInteresesPagadosPago() {
        return interesesPagadosPago;
    }

    public void setInteresesPagadosPago(BigDecimal interesesPagadosPago) {
        this.interesesPagadosPago = interesesPagadosPago;
    }

    public BigDecimal getInteresBasePago() {
        return interesBasePago;
    }

    public void setInteresBasePago(BigDecimal interesBasePago) {
        this.interesBasePago = interesBasePago;
    }

    public BigDecimal getInteresAcumulados() {
        return interesAcumulados;
    }

    public void setInteresAcumulados(BigDecimal interesAcumulados) {
        this.interesAcumulados = interesAcumulados;
    }

    public int getDiasAtrasoPago() {
        return diasAtrasoPago;
    }

    public void setDiasAtrasoPago(int diasAtrasoPago) {
        this.diasAtrasoPago = diasAtrasoPago;
    }

    public BigDecimal getPagoMinimoVenta() {
        return pagoMinimoVenta;
    }

    public void setPagoMinimoVenta(BigDecimal pagoMinimoVenta) {
        this.pagoMinimoVenta = pagoMinimoVenta;
    }

    public BigDecimal getPagoFinalVenta() {
        return pagoFinalVenta;
    }

    public void setPagoFinalVenta(BigDecimal pagoFinalVenta) {
        this.pagoFinalVenta = pagoFinalVenta;
    }

    public BigDecimal getMontoPagadoPago() {
        return montoPagadoPago;
    }

    public void setMontoPagadoPago(BigDecimal montoPagadoPago) {
        this.montoPagadoPago = montoPagadoPago;
    }

    public BigDecimal getMontoPendientePago() {
        return montoPendientePago;
    }

    public void setMontoPendientePago(BigDecimal montoPendientePago) {
        this.montoPendientePago = montoPendientePago;
    }
    
    
    public String getNombreCompletoCliente() {
        return Arrays.asList(this.nombreCliente,this.apellidoPaternoCliente,this.apellidoMaternoCliente).stream().collect(Collectors.joining(" "));
    }

    public Date getFechaProgramadoPago() {
        return fechaProgramadoPago;
    }

    public void setFechaProgramadoPago(Date fechaProgramadoPago) {
        this.fechaProgramadoPago = fechaProgramadoPago;
    }
    
    

}
