/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author canseco.victor
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "ocr.azure.pedimento.microservice");
        return properties;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.bhermanos.cobranza.api.providers.CorsFilter.class);
        resources.add(com.bhermanos.cobranza.api.providers.ObjectMapperContextResolver.class);
        resources.add(com.bhermanos.cobranza.api.resources.ClientesFacadeREST.class);
        resources.add(com.bhermanos.cobranza.api.resources.DistribuidorFacadeREST.class);
        resources.add(com.bhermanos.cobranza.api.resources.ValesFacadeREST.class);
        resources.add(com.bhermanos.cobranza.api.resources.VentasFacadeREST.class);
    }
}
