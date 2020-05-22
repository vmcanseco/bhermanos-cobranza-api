/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author canseco.victor
 */
@Path("clientes")
public class ClientesFacadeREST extends AbstractFacade<Clientes> {

    private EntityManager entityManager;

    public ClientesFacadeREST() {
        super(Clientes.class);

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public Response create(Clientes entity) {
        try {
            entityManager = getEntityManager();
            long count = (long) entityManager.createNamedQuery("Clientes.validateExistingClient")
                    .setParameter("ine", entity.getIne())
                    .setParameter("numero", entity.getNumero())
                    .getSingleResult();
            if (count > 0) {
                return Response.status(Response.Status.NOT_FOUND).entity("INE " + entity.getIne() + " o número de cliente " + entity.getNumero() + " previamente registrado, intente con otra.").build();
            } else {
                entity.setActivo("Y");
                entityManager.getTransaction().begin();
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
                entityManager.refresh(entity);

            }
            entityManager.close();
            return Response.ok(entity).build();

        } catch (Exception ex) {
            entityManager.close();
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear cliente. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());

        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Clientes entity) {

        try {
            entity.setId(id);
            entity.setFechaModificacion(new Date());
            entityManager = getEntityManager();

            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            // entityManager.refresh(entity);
            entityManager.close();
            return Response.ok(entity).build();

        } catch (Exception ex) {
            entityManager.close();
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar cliente. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());

        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Clientes result;
        try {
            entityManager = getEntityManager();
            result = entityManager.find(Clientes.class, id);
            entityManager.close();
            if (result != null) {
                SimpleFilterProvider filterProvider = new SimpleFilterProvider();
                filterProvider.addFilter("voucherFilter",
                        SimpleBeanPropertyFilter.serializeAllExcept("idCliente", "idVenta", "idVale", "idPago", "historialPagosList"));
                ObjectMapper mapper = new ObjectMapper();
                mapper.setFilterProvider(filterProvider);
                String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
                return Response.ok(jsonResult).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al buscar cliente por Id. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAllClients() {
        List<Clientes> result = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Clientes.findAll", Clientes.class).getResultList();
            entityManager.close();
            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("voucherFilter",
                    SimpleBeanPropertyFilter.serializeAllExcept("idCliente", "idVenta", "idVale", "idPago", "historialPagosList"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.setFilterProvider(filterProvider);
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            return Response.ok(jsonResult).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al buscar clientes. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }
    }

    @GET
    @Path("pagos-pendientes")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByPendigPayments() {
        List<Clientes> result = new ArrayList<>();
        List<KeyValue> sortedResult = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Clientes.findByPendingPayments", Clientes.class).getResultList();
            sortedResult = result.stream().sorted((c1, c2) -> {
                return c1.getNombre().compareTo(c2.getNombre());
            }).map(cliente -> {
                return new KeyValue(cliente.getId(), String.format("%s %s %s", cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno()));
            }).collect(Collectors.toList());
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setFilterProvider(filterProvider);*/
        String jsonResult;
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedResult);
            return Response.ok(jsonResult).build();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ClientesFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al buscar clientes con pagos pendientes. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }

    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Clientes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("exists")
    @Produces(MediaType.TEXT_PLAIN)
    public Response exists(@QueryParam("ine") String ine) {
        try {
            entityManager = getEntityManager();
            entityManager.createNamedQuery("Clientes.findByIne", Clientes.class).setParameter("ine", ine).getSingleResult();
            entityManager.close();
            return Response.status(Response.Status.NOT_FOUND).entity("INE " + ine + " previamente registrada, intente con otra.").build();
        } catch (NoResultException ex) {
            return Response.ok("1", MediaType.TEXT_PLAIN).build();
        } catch (Exception ex) {
            throw new WebApplicationException("Error al consultar cliente por  INE. Consulte administrador del sitio.", ex);
        }

    }

    /*@Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }*/
}
