/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import appempresaseguridad.data.controller.exceptions.IllegalOrphanException;
import appempresaseguridad.data.controller.exceptions.NonexistentEntityException;
import appempresaseguridad.data.entity.Persona;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import appempresaseguridad.data.entity.TipoDocumento;
import appempresaseguridad.data.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Garcia
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController() {
    }

    public EntityManager getEntityManager() {
        return GenericJpaController.getInstance().getEntityManager();
    }

    public void create(Persona persona) {
        if (persona.getUsuarioList() == null) {
            persona.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento idTipoDocumentoPersona = persona.getIdTipoDocumentoPersona();
            if (idTipoDocumentoPersona != null) {
                idTipoDocumentoPersona = em.getReference(idTipoDocumentoPersona.getClass(), idTipoDocumentoPersona.getIdTipoDocumento());
                persona.setIdTipoDocumentoPersona(idTipoDocumentoPersona);
            }
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : persona.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            persona.setUsuarioList(attachedUsuarioList);
            em.persist(persona);
            if (idTipoDocumentoPersona != null) {
                idTipoDocumentoPersona.getPersonaList().add(persona);
                idTipoDocumentoPersona = em.merge(idTipoDocumentoPersona);
            }
            for (Usuario usuarioListUsuario : persona.getUsuarioList()) {
                Persona oldIdPersonaUsuarioOfUsuarioListUsuario = usuarioListUsuario.getIdPersonaUsuario();
                usuarioListUsuario.setIdPersonaUsuario(persona);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdPersonaUsuarioOfUsuarioListUsuario != null) {
                    oldIdPersonaUsuarioOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdPersonaUsuarioOfUsuarioListUsuario = em.merge(oldIdPersonaUsuarioOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdPersona());
            TipoDocumento idTipoDocumentoPersonaOld = persistentPersona.getIdTipoDocumentoPersona();
            TipoDocumento idTipoDocumentoPersonaNew = persona.getIdTipoDocumentoPersona();
            List<Usuario> usuarioListOld = persistentPersona.getUsuarioList();
            List<Usuario> usuarioListNew = persona.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its idPersonaUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoDocumentoPersonaNew != null) {
                idTipoDocumentoPersonaNew = em.getReference(idTipoDocumentoPersonaNew.getClass(), idTipoDocumentoPersonaNew.getIdTipoDocumento());
                persona.setIdTipoDocumentoPersona(idTipoDocumentoPersonaNew);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            persona.setUsuarioList(usuarioListNew);
            persona = em.merge(persona);
            if (idTipoDocumentoPersonaOld != null && !idTipoDocumentoPersonaOld.equals(idTipoDocumentoPersonaNew)) {
                idTipoDocumentoPersonaOld.getPersonaList().remove(persona);
                idTipoDocumentoPersonaOld = em.merge(idTipoDocumentoPersonaOld);
            }
            if (idTipoDocumentoPersonaNew != null && !idTipoDocumentoPersonaNew.equals(idTipoDocumentoPersonaOld)) {
                idTipoDocumentoPersonaNew.getPersonaList().add(persona);
                idTipoDocumentoPersonaNew = em.merge(idTipoDocumentoPersonaNew);
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Persona oldIdPersonaUsuarioOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdPersonaUsuario();
                    usuarioListNewUsuario.setIdPersonaUsuario(persona);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdPersonaUsuarioOfUsuarioListNewUsuario != null && !oldIdPersonaUsuarioOfUsuarioListNewUsuario.equals(persona)) {
                        oldIdPersonaUsuarioOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdPersonaUsuarioOfUsuarioListNewUsuario = em.merge(oldIdPersonaUsuarioOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getIdPersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuario> usuarioListOrphanCheck = persona.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable idPersonaUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoDocumento idTipoDocumentoPersona = persona.getIdTipoDocumentoPersona();
            if (idTipoDocumentoPersona != null) {
                idTipoDocumentoPersona.getPersonaList().remove(persona);
                idTipoDocumentoPersona = em.merge(idTipoDocumentoPersona);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }
    
    public Persona findPersona(String numeroIdentificacion) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Persona.findByNumeroDocumentoPersona", Persona.class);
            q.setParameter("numeroDocumentoPersona", numeroIdentificacion);
            return (Persona) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    //Persona.findByNumeroDocumentoPersona

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
