/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import appempresaseguridad.data.controller.exceptions.NonexistentEntityException;
import appempresaseguridad.data.controller.exceptions.PreexistingEntityException;
import appempresaseguridad.data.entity.TurnoUsuarioEmpresa;
import appempresaseguridad.data.entity.TurnoUsuarioEmpresaPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Felipe Garcia
 */
public class TurnoUsuarioEmpresaJpaController implements Serializable {

    public TurnoUsuarioEmpresaJpaController() {
    }

    public EntityManager getEntityManager() {
        return GenericJpaController.getInstance().getEntityManager();
    }

    public void create(TurnoUsuarioEmpresa turnoUsuarioEmpresa) throws PreexistingEntityException, Exception {
        if (turnoUsuarioEmpresa.getTurnoUsuarioEmpresaPK() == null) {
            turnoUsuarioEmpresa.setTurnoUsuarioEmpresaPK(new TurnoUsuarioEmpresaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(turnoUsuarioEmpresa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurnoUsuarioEmpresa(turnoUsuarioEmpresa.getTurnoUsuarioEmpresaPK()) != null) {
                throw new PreexistingEntityException("TurnoUsuarioEmpresa " + turnoUsuarioEmpresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TurnoUsuarioEmpresa turnoUsuarioEmpresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            turnoUsuarioEmpresa = em.merge(turnoUsuarioEmpresa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TurnoUsuarioEmpresaPK id = turnoUsuarioEmpresa.getTurnoUsuarioEmpresaPK();
                if (findTurnoUsuarioEmpresa(id) == null) {
                    throw new NonexistentEntityException("The turnoUsuarioEmpresa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TurnoUsuarioEmpresaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TurnoUsuarioEmpresa turnoUsuarioEmpresa;
            try {
                turnoUsuarioEmpresa = em.getReference(TurnoUsuarioEmpresa.class, id);
                turnoUsuarioEmpresa.getTurnoUsuarioEmpresaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turnoUsuarioEmpresa with id " + id + " no longer exists.", enfe);
            }
            em.remove(turnoUsuarioEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TurnoUsuarioEmpresa> findTurnoUsuarioEmpresaEntities() {
        return findTurnoUsuarioEmpresaEntities(true, -1, -1);
    }

    public List<TurnoUsuarioEmpresa> findTurnoUsuarioEmpresaEntities(int maxResults, int firstResult) {
        return findTurnoUsuarioEmpresaEntities(false, maxResults, firstResult);
    }

    private List<TurnoUsuarioEmpresa> findTurnoUsuarioEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TurnoUsuarioEmpresa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TurnoUsuarioEmpresa findTurnoUsuarioEmpresa(TurnoUsuarioEmpresaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TurnoUsuarioEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoUsuarioEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TurnoUsuarioEmpresa> rt = cq.from(TurnoUsuarioEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
