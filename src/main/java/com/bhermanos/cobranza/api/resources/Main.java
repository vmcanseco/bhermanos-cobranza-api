/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author canseco.victor
 */
public class Main {
    
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("bhermanos-cobranza");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        /*javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Clientes.class));*/
        List<Clientes> listClientes=entityManager.createNamedQuery("Clientes.findAll", Clientes.class).getResultList();
        entityManager.close();
        entityManagerFactory.close();
        
    }
}
