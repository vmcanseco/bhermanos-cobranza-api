/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.api.providers.LocalEntityManagerFactory;
import com.bhermanos.cobranza.db.Clientes;
import com.bhermanos.cobranza.db.HistorialPagos;
import com.bhermanos.cobranza.db.InteresPagos;
import com.bhermanos.cobranza.db.Pagos;
import com.bhermanos.cobranza.db.Vales;
import com.bhermanos.cobranza.db.Ventas;
import com.bhermanos.cobranza.db.Ventas_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author canseco.victor
 */
@Path("cobranza")
public class CobranzaFacadeREST {

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return LocalEntityManagerFactory.createEntityManager();

    }

    @POST
    @Path("aplicar-pago")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response applyPayment(HistorialPagos entity) {
        try {
            BigDecimal debitAvailable = BigDecimal.ZERO;
            BigDecimal totalPayment = entity.getTotalPagado();
            entityManager = getEntityManager();
            //
            Pagos payment = entityManager.find(Pagos.class, entity.getIdPago().getId());

            if (payment == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No se permite ejecutar esta operación, pago no encontrado.")
                        .type(MediaType.TEXT_PLAIN).build();
            } else {
                if ("Y".equals(payment.getPagado())) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("No se permite ejecutar esta operación, pago previamente cubierto.")
                            .type(MediaType.TEXT_PLAIN).build();
                } else {
                    if (payment.getIdVenta().getIdVale().getIdCliente().getId().compareTo(entity.getIdCliente().getId()) != 0) {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("No se permite ejecutar esta operación, pago no relacionado al cliente.")
                                .type(MediaType.TEXT_PLAIN).build();
                    }
                }
            }

            Clientes clientId = payment.getIdVenta().getIdVale().getIdCliente();
            Ventas sale = payment.getIdVenta();
            Vales voucher = payment.getIdVenta().getIdVale();

            if ("SALDO".equals(entity.getFormaPago())) {
                List<HistorialPagos> paymentHist = entityManager.createNamedQuery("HistorialPagos.findAvailableDebitByClientId", HistorialPagos.class)
                        .setParameter("idCliente", entity.getIdCliente().getId())
                        .getResultList();
                if (paymentHist != null && paymentHist.size() > 0) {
                    debitAvailable = paymentHist.stream().map(h -> h.getMontoPagado()).reduce(BigDecimal.ZERO, (t, u) -> {
                        return t.add(u);
                    });
                    if (debitAvailable.compareTo(BigDecimal.ZERO) <= 0) {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("No se permite ejecutar esta operación, no cuenta con saldo a favor.")
                                .type(MediaType.TEXT_PLAIN).build();
                    } else {
                        BigDecimal amountPlusInt = entity.getMontoPagado().add(entity.getInteresesPagados());
                        if (debitAvailable.compareTo(totalPayment) == -1 || debitAvailable.compareTo(amountPlusInt) == -1) {
                            return Response.status(Response.Status.BAD_REQUEST)
                                    .entity("No se permite ejecutar esta operación,saldo a favor insuficiente.")
                                    .type(MediaType.TEXT_PLAIN).build();
                        }
                    }
                } else {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("No se permite ejecutar esta operación, no cuenta con saldo a favor.")
                            .type(MediaType.TEXT_PLAIN).build();
                }
            }

            entityManager.getTransaction().begin();

            entity.setFechaCreacion(new java.util.Date());
            entityManager.persist(entity);
            /*entityManager.getTransaction().commit();
            
            entityManager.getTransaction().begin();
            entityManager.refresh(entity);*/
            if ("SALDO".equals(entity.getFormaPago())) {
                HistorialPagos paymentHist = new HistorialPagos();
                paymentHist.setMontoPagado(totalPayment.negate());
                paymentHist.setIdCliente(clientId);
                paymentHist.setFechaPago(entity.getFechaPago());
                paymentHist.setFormaPago(entity.getFormaPago());
                paymentHist.setInteresesPagados(BigDecimal.ZERO);
                paymentHist.setFechaCreacion(new java.util.Date());
                entityManager.persist(paymentHist);
            } else {

                BigDecimal totalPaid = entity.getMontoPagado().add(entity.getInteresesPagados());
                if (totalPayment.compareTo(totalPaid) >= 1) {
                    HistorialPagos paymentHist = new HistorialPagos();
                    paymentHist.setMontoPagado(totalPayment.subtract(totalPaid));
                    paymentHist.setIdCliente(clientId);
                    paymentHist.setFechaPago(entity.getFechaPago());
                    paymentHist.setFormaPago(entity.getFormaPago());
                    paymentHist.setInteresesPagados(BigDecimal.ZERO);
                    paymentHist.setFechaCreacion(new java.util.Date());
                    entityManager.persist(paymentHist);
                }
            }
            //payment = entity.getIdPago();

            payment.setInteresesPagados(payment.getInteresesPagados().add(entity.getInteresesPagados()));
            payment.setMontoPagado(payment.getMontoPagado().add(entity.getMontoPagado()));

            if (payment.getIntereses().compareTo(payment.getInteresesPagados()) == 0
                    && payment.getMonto().compareTo(payment.getMontoPagado()) == 0) {
                payment.setPagado("Y");
                payment.setFechaActualizacion(new java.util.Date());
            }

            entityManager.persist(payment);

            sale.setMontoPagado(sale.getMontoPagado().add(entity.getMontoPagado()));
            sale.setMontoDisponible(sale.getMontoDisponible().subtract(entity.getMontoPagado()));

            if (sale.getMonto().compareTo(sale.getMontoPagado()) == 0) {
                sale.setPagada("Y");
                sale.setFechaModificacion(new java.util.Date());
            }
            entityManager.persist(sale);

            voucher.setMontoPagado(voucher.getMontoPagado().add(entity.getMontoPagado()));
            if (voucher.getMonto().compareTo(voucher.getMontoPagado()) == 0) {
                voucher.setPagado("Y");
                voucher.setFechaModificacion(new java.util.Date());
            }
            entityManager.persist(voucher);
            entityManager.getTransaction().commit();
            return Response.ok(entity.getId()).build();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al aplicar el pago. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        } finally {
            entityManager.close();
        }
    }

    @GET
    @Path("clientes-pagos-pendientes")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchClientsPendingPayments(@QueryParam("fecha") String date, @QueryParam("idCliente") Integer clientId, @QueryParam("incluirPagosAtrasados") boolean includeLatePayments) {

        LocalDate programmedLocaDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Date progammedDate = java.sql.Date.valueOf(programmedLocaDate);

        //List<Pagos> filteredResult = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            String query = "Pagos.findByFechaProgramada";
            if (includeLatePayments) {
                query = "Pagos.findByLatePayments";
            }
            final List<Pagos> result = entityManager.createNamedQuery(query, Pagos.class).setParameter("fechaProgramada", progammedDate).getResultList();
            final List<Pagos> filteredResult = new ArrayList<>();
            if (clientId != null && clientId > 0 && result.size() > 0) {
                filteredResult.addAll(result.stream().filter(pago -> {
                    return pago.getIdVenta().getIdVale().getIdCliente().getId().equals(clientId);
                }).sorted(Comparator.comparingInt((value) -> {
                    return value.getNumPago();
                })).collect(Collectors.toList()));
            } else {
                filteredResult.addAll(result.stream().sorted(Comparator.comparingInt((value) -> {
                    return value.getNumPago();
                })).collect(Collectors.toList()));
            }
            entityManager.close();

            List<Clientes> clientes = filteredResult.stream().map(pago -> pago.getIdVenta().getIdVale().getIdCliente())
                    .sorted((o1, o2) -> {
                        return o1.getId().compareTo(o2.getId());
                    }).distinct().collect(Collectors.toList());

            clientes.stream().forEach(cliente -> {
                cliente.setValesCollection(null);
                //cliente.setHistorialPagosList(null);
            });

            List<ClientePagos> clientePagosList = clientes.stream().map(cliente -> {
                List<Pagos> pagos = filteredResult.stream().filter(pago -> {
                    return pago.getIdVenta().getIdVale().getIdCliente().getId().equals(cliente.getId());
                }).collect(Collectors.toList());

                /*cliente.setPagos(pagos);
                return cliente;*/
                entityManager = getEntityManager();
                List<Ventas> ventas = entityManager.createNamedQuery("Ventas.findUnpaidSalesByCliendId", Ventas.class).setParameter("idCliente", cliente.getId()).getResultList();
                entityManager.close();
                ClientePagos clientePagos = new ClientePagos();
                clientePagos.setCliente(cliente);
                clientePagos.setPagos(pagos);
                clientePagos.setTotalVentas(ventas.stream().map(venta -> {
                    return venta.getMonto();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                clientePagos.setTotalPagado(ventas.stream().map(venta -> {
                    return venta.getMontoPagado();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                clientePagos.setTotalDisponible(ventas.stream().map(venta -> {
                    return venta.getMontoDisponible();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                return clientePagos;
            }).collect(Collectors.toList());

            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("voucherFilter",
                    SimpleBeanPropertyFilter.serializeAllExcept("idCliente", "idVenta", "idVale", "pagosList", "idPago"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.setFilterProvider(filterProvider);
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientePagosList);
            return Response.ok(jsonResult).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener lista de ventas. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }

    }

    @GET
    @Path("clientes-pagos-demorados")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchClientsPendingPayments(@QueryParam("fecha") String date) {

        LocalDate programmedLocaDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Date progammedDate = java.sql.Date.valueOf(programmedLocaDate);

        //List<Pagos> filteredResult = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            String query = "Pagos.findByInterestPayments";
            final List<Pagos> result = entityManager.createNamedQuery(query, Pagos.class).setParameter("fechaProgramada", progammedDate).getResultList();
            final List<Pagos> filteredResult = new ArrayList<>();

            filteredResult.addAll(result.stream().sorted(Comparator.comparingInt((value) -> {
                return value.getNumPago();
            })).collect(Collectors.toList()));

            List<Clientes> clientes = filteredResult.stream().map(pago -> pago.getIdVenta().getIdVale().getIdCliente())
                    .sorted((o1, o2) -> {
                        return o1.getId().compareTo(o2.getId());
                    }).distinct().collect(Collectors.toList());

            clientes.stream().forEach(cliente -> {
                cliente.setValesCollection(null);
                cliente.setHistorialPagosList(null);
            });

            List<ClientePagos> clientePagosList = clientes.stream().map(cliente -> {
                List<Pagos> pagos = filteredResult.stream().filter(pago -> {
                    return pago.getIdVenta().getIdVale().getIdCliente().getId().equals(cliente.getId());
                }).collect(Collectors.toList());

                pagos.stream().forEach(pago -> {
                    pago.setHistorialPagosList(null);
                });

                /*cliente.setPagos(pagos);
                return cliente;*/
                List<Ventas> ventas = entityManager.createNamedQuery("Ventas.findUnpaidSalesByCliendId", Ventas.class).setParameter("idCliente", cliente.getId()).getResultList();
                ClientePagos clientePagos = new ClientePagos();
                clientePagos.setCliente(cliente);
                clientePagos.setPagos(pagos);
                clientePagos.setTotalVentas(ventas.stream().map(venta -> {
                    return venta.getMonto();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                clientePagos.setTotalPagado(ventas.stream().map(venta -> {
                    return venta.getMontoPagado();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                clientePagos.setTotalDisponible(ventas.stream().map(venta -> {
                    return venta.getMontoDisponible();
                }).reduce(BigDecimal.ZERO, (t, u) -> {
                    return t.add(u);
                }));
                return clientePagos;
            }).collect(Collectors.toList());

            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("voucherFilter",
                    SimpleBeanPropertyFilter.serializeAllExcept("idCliente", "idVenta", "idVale", "pagosList", "idPago", "historialPagosList"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.setFilterProvider(filterProvider);
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientePagosList);
            return Response.ok(jsonResult).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener lista de ventas. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        } finally {
            entityManager.close();
        }

    }

    @POST
    @Path("calculo-interes")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response calculateInterest(@FormParam("fecha")String date, @FormParam("aplicar")boolean apply) {
        List<InteresPagos> interestPaymentList = new ArrayList<>();
        
        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Date progammedDate = java.sql.Date.valueOf(targetDate);

        entityManager = getEntityManager();
        try {
        String query = "Pagos.findByInterestPayments";
        final List<Pagos> result = entityManager.createNamedQuery(query, Pagos.class).setParameter("fechaProgramada", progammedDate).getResultList();
        
        result.stream().forEach(payment -> {
            InteresPagos interestPayment= new InteresPagos();
            Ventas sale = payment.getIdVenta();
            Vales voucher = sale.getIdVale();
            Clientes client = voucher.getIdCliente();
            
            LocalDate paymentDate=payment.getFechaProgramada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysToApplyInt = sale.getDiasInteres();
            long daysBetween = ChronoUnit.DAYS.between(paymentDate, targetDate);
            BigDecimal interestRate = BigDecimal.valueOf(sale.getTasaInteres());
            BigDecimal pendingAmount = payment.getMonto().subtract(payment.getMontoPagado());
            BigDecimal interestAmount = BigDecimal.ZERO;
            BigDecimal baseInterestAmount = BigDecimal.ZERO;
            BigDecimal interestRateDec=interestRate.divide(BigDecimal.valueOf(100));

            if (daysBetween < daysToApplyInt) {
                daysToApplyInt = daysBetween;
            }

            for (int numDay = 0; numDay < daysToApplyInt; numDay++) {

                switch (numDay) {
                    case 0:
                        baseInterestAmount = pendingAmount.multiply(interestRateDec);
                        interestAmount=interestAmount.add(baseInterestAmount);

                        break;
                    default:
                        interestAmount=interestAmount.add(baseInterestAmount.multiply(interestRateDec));
                        break;
                }
            }
            interestAmount=interestAmount.setScale(0, RoundingMode.HALF_EVEN);
            baseInterestAmount=baseInterestAmount.setScale(0, RoundingMode.HALF_EVEN);
            if (apply) {
                entityManager.getTransaction().begin();
                payment.setIntereses(interestAmount);
                entityManager.persist(payment);
                entityManager.getTransaction().commit();
            }
            
            interestPayment.setIdCliente(client.getId());
            interestPayment.setNombreCliente(client.getNombre());
            interestPayment.setApellidoPaternoCliente(client.getApellidoPaterno());
            interestPayment.setApellidoMaternoCliente(client.getApellidoMaterno());
            interestPayment.setCodigoCliente(client.getNumero());
            interestPayment.setIdVale(voucher.getId());
            interestPayment.setTipoVale(voucher.getTipo());
            interestPayment.setFolioVale(voucher.getFolio());
            interestPayment.setIdVenta(sale.getId());
            interestPayment.setDiasInteresVenta(sale.getDiasInteres());
            interestPayment.setMontoVenta(sale.getMonto());
            interestPayment.setTasaInteresVenta(sale.getTasaInteres());
            interestPayment.setPagoMinimoVenta(sale.getPagoMinimo());
            interestPayment.setPagoFinalVenta(sale.getPagoFinal());
            interestPayment.setIdPago(payment.getId());
            interestPayment.setNumPago(payment.getNumPago());
            interestPayment.setMontoPago(payment.getMonto());
            interestPayment.setInteresesPago(interestAmount);
            interestPayment.setInteresesPagadosPago(payment.getInteresesPagados());
            interestPayment.setInteresBasePago(baseInterestAmount);
            interestPayment.setInteresAcumulados(interestAmount.subtract(baseInterestAmount));   
            interestPayment.setDiasAtrasoPago((int)daysBetween);
            interestPayment.setMontoPendientePago(pendingAmount);
            interestPayment.setFechaProgramadoPago(payment.getFechaProgramada());
            interestPaymentList.add(interestPayment);
        });
        return Response.ok(interestPaymentList).build();
        }catch (Exception ex) {
            ex.printStackTrace();

            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al calcular intereses. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        } finally {
            entityManager.close();
        }
    }

}
