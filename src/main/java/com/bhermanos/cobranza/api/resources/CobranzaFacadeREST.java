/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.api.providers.LocalEntityManagerFactory;
import com.bhermanos.cobranza.db.Clientes;
import com.bhermanos.cobranza.db.Pagos;
import com.bhermanos.cobranza.db.Ventas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    @GET
    @Path("clientes-pagos-pendientes")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchClientsPendingPayments(@QueryParam("fecha") String date, @QueryParam("idCliente") Integer clientId, @QueryParam("incluirPagosAtrasados") boolean includeLatePayments) {

        LocalDate programmedLocaDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Date progammedDate = java.sql.Date.valueOf(programmedLocaDate);
        List<Pagos> result = new ArrayList<>();
        List<Pagos> filteredResult = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            String query = "Pagos.findByFechaProgramada";
            if (includeLatePayments) {
                query = "Pagos.findByLatePayments";
            }
            result = entityManager.createNamedQuery(query, Pagos.class).setParameter("fechaProgramada", progammedDate).getResultList();

            if (clientId != null && clientId > 0 && result.size() > 0) {
                filteredResult = result.stream().filter(pago -> {
                    return pago.getIdVenta().getIdVale().getIdCliente().getId().equals(clientId);
                }).sorted(Comparator.comparingInt((value) -> {
                    return value.getNumPago();
                })).collect(Collectors.toList());
            } else {
                filteredResult = result;
            }
            entityManager.close();

            List<Clientes> clientes = result.stream().map(pago -> pago.getIdVenta().getIdVale().getIdCliente())
                    .sorted((o1, o2) -> {
                        return o1.getId().compareTo(o2.getId());
                    }).collect(Collectors.toList());

            /*SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("voucherFilter",
                    SimpleBeanPropertyFilter.serializeAllExcept("idVenta", "pagosList"));*/
            ObjectMapper mapper = new ObjectMapper();
            //mapper.setFilterProvider(filterProvider);*/
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientes);
            return Response.ok(jsonResult).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener lista de ventas. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }

    }

}
