/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import appempresaseguridad.data.controller.exceptions.IllegalOrphanException;
import appempresaseguridad.data.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import appempresaseguridad.data.entity.Persona;
import appempresaseguridad.data.entity.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alejrudu
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getPersonaList() == null) {
            tipoDocumento.setPersonaList(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : tipoDocumento.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getIdPersona());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            tipoDocumento.setPersonaList(attachedPersonaList);
            em.persist(tipoDocumento);
            for (Persona personaListPersona : tipoDocumento.getPersonaList()) {
                TipoDocumento oldIdTipoDocumentoPersonaOfPersonaListPersona = personaListPersona.getIdTipoDocumentoPersona();
                personaListPersona.setIdTipoDocumentoPersona(tipoDocumento);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdTipoDocumentoPersonaOfPersonaListPersona != null) {
                    oldIdTipoDocumentoPersonaOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdTipoDocumentoPersonaOfPersonaListPersona = em.merge(oldIdTipoDocumentoPersonaOfPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getIdTipoDocumento());
            List<Persona> personaListOld = persistentTipoDocumento.getPersonaList();
            List<Persona> personaListNew = tipoDocumento.getPersonaList();
            List<String> illegalOrphanMessages = null;
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaListOldPersona + " since its idTipoDocumentoPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getIdPersona());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            tipoDocumento.setPersonaList(personaListNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    TipoDocumento oldIdTipoDocumentoPersonaOfPersonaListNewPersona = personaListNewPersona.getIdTipoDocumentoPersona();
                    personaListNewPersona.setIdTipoDocumentoPersona(tipoDocumento);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdTipoDocumentoPersonaOfPersonaListNewPersona != null && !oldIdTipoDocumentoPersonaOfPersonaListNewPersona.equals(tipoDocumento)) {
                        oldIdTipoDocumentoPersonaOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdTipoDocumentoPersonaOfPersonaListNewPersona = em.merge(oldIdTipoDocumentoPersonaOfPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getIdTipoDocumento();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getIdTipoDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Persona> personaListOrphanCheck = tipoDocumento.getPersonaList();
            for (Persona personaListOrphanCheckPersona : personaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoDocumento (" + tipoDocumento + ") cannot be destroyed since the Persona " + personaListOrphanCheckPersona + " in its personaList field has a non-nullable idTipoDocumentoPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
