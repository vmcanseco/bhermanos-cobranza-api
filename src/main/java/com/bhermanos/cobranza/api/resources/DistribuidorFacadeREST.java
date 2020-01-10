/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import com.bhermanos.cobranza.db.Distribuidor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author canseco.victor
 */
@Path("distribuidores")
public class DistribuidorFacadeREST extends AbstractFacade<Distribuidor> {

    private EntityManager entityManager;

    public DistribuidorFacadeREST() {
        super(Distribuidor.class);

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("activos")
    public Response findAllActives() {
        List<Distribuidor> result = new ArrayList<>();

        try {
            entityManager = getEntityManager();
            result = entityManager.createNamedQuery("Distribuidor.findAllActivos", Distribuidor.class).getResultList();
            entityManager.close();
            return Response.ok(result).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener lista de distribuidores. Consulte administrador del sitio.")
                    .type(MediaType.TEXT_PLAIN).build());
        }
    }

}
