/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import appempresaseguridad.data.controller.exceptions.NonexistentEntityException;
import appempresaseguridad.data.entity.ReporteTurnos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import appempresaseguridad.data.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Felipe Garcia
 */
public class ReporteTurnosJpaController implements Serializable {

    public ReporteTurnosJpaController() {
    }

    public EntityManager getEntityManager() {
        return GenericJpaController.getInstance().getEntityManager();
    }

    public void create(ReporteTurnos reporteTurnos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuarioRegistra = reporteTurnos.getIdUsuarioRegistra();
            if (idUsuarioRegistra != null) {
                idUsuarioRegistra = em.getReference(idUsuarioRegistra.getClass(), idUsuarioRegistra.getIdUsuario());
                reporteTurnos.setIdUsuarioRegistra(idUsuarioRegistra);
            }
            Usuario idUsuarioReportado = reporteTurnos.getIdUsuarioReportado();
            if (idUsuarioReportado != null) {
                idUsuarioReportado = em.getReference(idUsuarioReportado.getClass(), idUsuarioReportado.getIdUsuario());
                reporteTurnos.setIdUsuarioReportado(idUsuarioReportado);
            }
            em.persist(reporteTurnos);
            if (idUsuarioRegistra != null) {
                idUsuarioRegistra.getReporteTurnosCollection().add(reporteTurnos);
                idUsuarioRegistra = em.merge(idUsuarioRegistra);
            }
            if (idUsuarioReportado != null) {
                idUsuarioReportado.getReporteTurnosCollection().add(reporteTurnos);
                idUsuarioReportado = em.merge(idUsuarioReportado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReporteTurnos reporteTurnos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteTurnos persistentReporteTurnos = em.find(ReporteTurnos.class, reporteTurnos.getIdRegistro());
            Usuario idUsuarioRegistraOld = persistentReporteTurnos.getIdUsuarioRegistra();
            Usuario idUsuarioRegistraNew = reporteTurnos.getIdUsuarioRegistra();
            Usuario idUsuarioReportadoOld = persistentReporteTurnos.getIdUsuarioReportado();
            Usuario idUsuarioReportadoNew = reporteTurnos.getIdUsuarioReportado();
            if (idUsuarioRegistraNew != null) {
                idUsuarioRegistraNew = em.getReference(idUsuarioRegistraNew.getClass(), idUsuarioRegistraNew.getIdUsuario());
                reporteTurnos.setIdUsuarioRegistra(idUsuarioRegistraNew);
            }
            if (idUsuarioReportadoNew != null) {
                idUsuarioReportadoNew = em.getReference(idUsuarioReportadoNew.getClass(), idUsuarioReportadoNew.getIdUsuario());
                reporteTurnos.setIdUsuarioReportado(idUsuarioReportadoNew);
            }
            reporteTurnos = em.merge(reporteTurnos);
            if (idUsuarioRegistraOld != null && !idUsuarioRegistraOld.equals(idUsuarioRegistraNew)) {
                idUsuarioRegistraOld.getReporteTurnosCollection().remove(reporteTurnos);
                idUsuarioRegistraOld = em.merge(idUsuarioRegistraOld);
            }
            if (idUsuarioRegistraNew != null && !idUsuarioRegistraNew.equals(idUsuarioRegistraOld)) {
                idUsuarioRegistraNew.getReporteTurnosCollection().add(reporteTurnos);
                idUsuarioRegistraNew = em.merge(idUsuarioRegistraNew);
            }
            if (idUsuarioReportadoOld != null && !idUsuarioReportadoOld.equals(idUsuarioReportadoNew)) {
                idUsuarioReportadoOld.getReporteTurnosCollection().remove(reporteTurnos);
                idUsuarioReportadoOld = em.merge(idUsuarioReportadoOld);
            }
            if (idUsuarioReportadoNew != null && !idUsuarioReportadoNew.equals(idUsuarioReportadoOld)) {
                idUsuarioReportadoNew.getReporteTurnosCollection().add(reporteTurnos);
                idUsuarioReportadoNew = em.merge(idUsuarioReportadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reporteTurnos.getIdRegistro();
                if (findReporteTurnos(id) == null) {
                    throw new NonexistentEntityException("The reporteTurnos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteTurnos reporteTurnos;
            try {
                reporteTurnos = em.getReference(ReporteTurnos.class, id);
                reporteTurnos.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteTurnos with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuarioRegistra = reporteTurnos.getIdUsuarioRegistra();
            if (idUsuarioRegistra != null) {
                idUsuarioRegistra.getReporteTurnosCollection().remove(reporteTurnos);
                idUsuarioRegistra = em.merge(idUsuarioRegistra);
            }
            Usuario idUsuarioReportado = reporteTurnos.getIdUsuarioReportado();
            if (idUsuarioReportado != null) {
                idUsuarioReportado.getReporteTurnosCollection().remove(reporteTurnos);
                idUsuarioReportado = em.merge(idUsuarioReportado);
            }
            em.remove(reporteTurnos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReporteTurnos> findReporteTurnosEntities() {
        return findReporteTurnosEntities(true, -1, -1);
    }

    public List<ReporteTurnos> findReporteTurnosEntities(int maxResults, int firstResult) {
        return findReporteTurnosEntities(false, maxResults, firstResult);
    }

    private List<ReporteTurnos> findReporteTurnosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReporteTurnos.class));
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

    public ReporteTurnos findReporteTurnos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReporteTurnos.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteTurnosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReporteTurnos> rt = cq.from(ReporteTurnos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
