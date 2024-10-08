/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cabecera_remito;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alumno
 */
public class cabecera_remitoJpaController implements Serializable {

    public cabecera_remitoJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }
    
    public cabecera_remitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cabecera_remito cabecera_remito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabecera_remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cabecera_remito cabecera_remito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabecera_remito = em.merge(cabecera_remito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cabecera_remito.getId_remito();
                if (findcabecera_remito(id) == null) {
                    throw new NonexistentEntityException("The cabecera_remito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabecera_remito cabecera_remito;
            try {
                cabecera_remito = em.getReference(cabecera_remito.class, id);
                cabecera_remito.getId_remito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabecera_remito with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabecera_remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cabecera_remito> findcabecera_remitoEntities() {
        return findcabecera_remitoEntities(true, -1, -1);
    }

    public List<cabecera_remito> findcabecera_remitoEntities(int maxResults, int firstResult) {
        return findcabecera_remitoEntities(false, maxResults, firstResult);
    }

    private List<cabecera_remito> findcabecera_remitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cabecera_remito.class));
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

    public cabecera_remito findcabecera_remito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cabecera_remito.class, id);
        } finally {
            em.close();
        }
    }

    public int getcabecera_remitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cabecera_remito> rt = cq.from(cabecera_remito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}