/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Vales;
import java.math.BigDecimal;
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
@Path("vales")
public class ValesFacadeREST extends AbstractFacade<Vales> {

    private EntityManager entityManager;

    public ValesFacadeREST() {
        super(Vales.class);

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Vales entity) {
        try {
            entityManager = getEntityManager();

            entity.setMontoDisponible(entity.getMonto());
            entity.setMontoPagado(BigDecimal.valueOf(0));
            entity.setPagado("N");
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
                    .entity("Error al crear cliente. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());

        }
    }
    @GET
    @Path("disponibles")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Vales> findAvailableVouchersByClient(@QueryParam("idCliente") Integer clientId){
         List<Vales> result = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Vales.findAvailablesByClient", Vales.class).setParameter("idCliente", clientId).getResultList();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
