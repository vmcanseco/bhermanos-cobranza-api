/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Vales;
import com.bhermanos.cobranza.db.Ventas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
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
@Path("ventas")
public class VentasFacadeREST extends AbstractFacade<Ventas> {

    private EntityManager entityManager;

    public VentasFacadeREST() {
        super(Ventas.class);

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Ventas entity) {
        try {
            entityManager = getEntityManager();
            Vales vale = entityManager.find(Vales.class, entity.getIdVale().getId());

            if (vale.getMontoDisponible().compareTo(entity.getMonto()) == -1) {
                throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(String.format("La venta por %f, supera el monto disponible en el vale de %f", entity.getMonto(), vale.getMontoDisponible()))
                        .type(MediaType.TEXT_PLAIN).build());
            }

            BigDecimal minPay=entity.getMonto().divide(BigDecimal.valueOf(entity.getPlazo()), RoundingMode.DOWN);
            entity.setMontoDisponible(entity.getMonto());
            entity.setMontoPagado(BigDecimal.ZERO);
            entity.setPagada("N");
            entity.setIntereses(BigDecimal.ZERO);
            entity.setPagoMinimo(minPay);
            entity.setPagoFinal(entity.getMonto().subtract(minPay.multiply(BigDecimal.valueOf(entity.getPlazo()-1))));
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.refresh(entity);
            entityManager.close();
            return Response.ok(entity).build();

        } catch (Exception ex) {
            entityManager.close();
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al registrar venta. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());

        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findSalesByClient(@QueryParam("idCliente") Integer clientId) {
        List<Ventas> result = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Ventas.findByClientId", Ventas.class).setParameter("idCliente", clientId).getResultList();
            entityManager.close();
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("voucherFilter",
                    SimpleBeanPropertyFilter.serializeAllExcept("idCliente"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.setFilterProvider(filterProvider);
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            return Response.ok(jsonResult).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener lista de ventas. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }

    }
}
