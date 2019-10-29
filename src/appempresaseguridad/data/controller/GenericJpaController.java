/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Clase creada para generalizar la creacion del entity manager factory, y no
 * repetir el proceso por cada controlador JPA
 *
 * @author Felipe Garcia
 */
public class GenericJpaController {

    protected EntityManagerFactory emf = null;

    public GenericJpaController() {
        this.emf = Persistence.createEntityManagerFactory("appEmpresaSeguridadPU");
    }

}
