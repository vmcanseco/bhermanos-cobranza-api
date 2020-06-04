/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

import com.bhermanos.cobranza.db.Clientes;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        /*EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("bhermanos-cobranza");
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        List<Clientes> listClientes=entityManager.createNamedQuery("Clientes.findAll", Clientes.class).getResultList();
        entityManager.close();
        entityManagerFactory.close();*/
        testLocalDate();
        
    }
    
    private static void testLocalDate() {
        LocalDate localDate=LocalDate.now();
        LocalDate localDate2= LocalDate.of(2020,6,30);
        System.out.println(localDate.toEpochDay());
        System.out.println(localDate2.toEpochDay());
        System.out.println(ChronoUnit.DAYS.between(localDate, localDate2));
    }
}
