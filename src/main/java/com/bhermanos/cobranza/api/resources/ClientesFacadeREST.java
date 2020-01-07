/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import java.util.ArrayList;
import java.util.List;
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
            int count = (int) entityManager.createNamedQuery("Clientes.validateExistingClient")
                    .setParameter("ine", entity.getIne())
                    .setParameter("numero", entity.getNumero())
                    .getSingleResult();
            if (count > 0) {
                return Response.status(Response.Status.NOT_FOUND).entity("INE " + entity.getIne() + " o número de cliente " + entity.getNumero() + " previamente registrada, intente con otra.").build();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
                entityManager.refresh(entity);

            }
            entityManager.close();
            return Response.ok(entity).build();

        } catch (Exception ex) {
            throw new WebApplicationException("Error al crear cliente. Consulte administrador del sitio.", ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Clientes entity) {

        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Clientes find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Clientes> findAll() {
        List<Clientes> result = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Clientes.findAll", Clientes.class).getResultList();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
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
