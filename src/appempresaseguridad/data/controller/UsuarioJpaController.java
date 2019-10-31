/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.controller;

import appempresaseguridad.data.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import appempresaseguridad.data.entity.Persona;
import appempresaseguridad.data.entity.Rol;
import appempresaseguridad.data.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Garcia
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
    }

    public EntityManager getEntityManager() {
        return GenericJpaController.getInstance().getEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersonaUsuario = usuario.getIdPersonaUsuario();
            if (idPersonaUsuario != null) {
                idPersonaUsuario = em.getReference(idPersonaUsuario.getClass(), idPersonaUsuario.getIdPersona());
                usuario.setIdPersonaUsuario(idPersonaUsuario);
            }
            Rol idRolUsuario = usuario.getIdRolUsuario();
            if (idRolUsuario != null) {
                idRolUsuario = em.getReference(idRolUsuario.getClass(), idRolUsuario.getIdRol());
                usuario.setIdRolUsuario(idRolUsuario);
            }
            em.persist(usuario);
            if (idPersonaUsuario != null) {
                idPersonaUsuario.getUsuarioList().add(usuario);
                idPersonaUsuario = em.merge(idPersonaUsuario);
            }
            if (idRolUsuario != null) {
                idRolUsuario.getUsuarioList().add(usuario);
                idRolUsuario = em.merge(idRolUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Persona idPersonaUsuarioOld = persistentUsuario.getIdPersonaUsuario();
            Persona idPersonaUsuarioNew = usuario.getIdPersonaUsuario();
            Rol idRolUsuarioOld = persistentUsuario.getIdRolUsuario();
            Rol idRolUsuarioNew = usuario.getIdRolUsuario();
            if (idPersonaUsuarioNew != null) {
                idPersonaUsuarioNew = em.getReference(idPersonaUsuarioNew.getClass(), idPersonaUsuarioNew.getIdPersona());
                usuario.setIdPersonaUsuario(idPersonaUsuarioNew);
            }
            if (idRolUsuarioNew != null) {
                idRolUsuarioNew = em.getReference(idRolUsuarioNew.getClass(), idRolUsuarioNew.getIdRol());
                usuario.setIdRolUsuario(idRolUsuarioNew);
            }
            usuario = em.merge(usuario);
            if (idPersonaUsuarioOld != null && !idPersonaUsuarioOld.equals(idPersonaUsuarioNew)) {
                idPersonaUsuarioOld.getUsuarioList().remove(usuario);
                idPersonaUsuarioOld = em.merge(idPersonaUsuarioOld);
            }
            if (idPersonaUsuarioNew != null && !idPersonaUsuarioNew.equals(idPersonaUsuarioOld)) {
                idPersonaUsuarioNew.getUsuarioList().add(usuario);
                idPersonaUsuarioNew = em.merge(idPersonaUsuarioNew);
            }
            if (idRolUsuarioOld != null && !idRolUsuarioOld.equals(idRolUsuarioNew)) {
                idRolUsuarioOld.getUsuarioList().remove(usuario);
                idRolUsuarioOld = em.merge(idRolUsuarioOld);
            }
            if (idRolUsuarioNew != null && !idRolUsuarioNew.equals(idRolUsuarioOld)) {
                idRolUsuarioNew.getUsuarioList().add(usuario);
                idRolUsuarioNew = em.merge(idRolUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Persona idPersonaUsuario = usuario.getIdPersonaUsuario();
            if (idPersonaUsuario != null) {
                idPersonaUsuario.getUsuarioList().remove(usuario);
                idPersonaUsuario = em.merge(idPersonaUsuario);
            }
            Rol idRolUsuario = usuario.getIdRolUsuario();
            if (idRolUsuario != null) {
                idRolUsuario.getUsuarioList().remove(usuario);
                idRolUsuario = em.merge(idRolUsuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    
    public Usuario findUsuario(String user, String password) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Usuario.findByPassUsuarioNombreUsuario", Usuario.class);
            q.setParameter("nombreUsuario", user);
            q.setParameter("passUsuario", password);
            return (Usuario) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
