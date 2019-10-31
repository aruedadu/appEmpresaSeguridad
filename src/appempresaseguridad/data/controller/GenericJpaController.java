/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Singleton para asegurar la creacion de una sola conexion a base de datos
 *
 * @author Felipe Garcia
 */
public class GenericJpaController {

    private EntityManagerFactory emf = null;

    private static volatile GenericJpaController genericController;

    private GenericJpaController() {
        this.emf = Persistence.createEntityManagerFactory("appEmpresaSeguridadPU");
        emf.createEntityManager();
    }

    public static GenericJpaController getInstance() {
        if (null == genericController) {
            synchronized (GenericJpaController.class) {
                if (null == genericController) {
                    genericController = new GenericJpaController();
                }
            }
        }
        return genericController;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return this.emf;
    }
    
    public EntityManager getEntityManager() {
        return getEmf().createEntityManager();
    }
}
