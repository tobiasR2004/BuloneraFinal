/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.pagoDetalle;
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
 * @author tobi2
 */
public class pagoDetalleJpaController implements Serializable {

    public pagoDetalleJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }

    public pagoDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(pagoDetalle pagoDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pagoDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(pagoDetalle pagoDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pagoDetalle = em.merge(pagoDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = pagoDetalle.getIdPagoDet();
                if (findpagoDetalle(id) == null) {
                    throw new NonexistentEntityException("The pagoDetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pagoDetalle pagoDetalle;
            try {
                pagoDetalle = em.getReference(pagoDetalle.class, id);
                pagoDetalle.getIdPagoDet();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(pagoDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<pagoDetalle> findpagoDetalleEntities() {
        return findpagoDetalleEntities(true, -1, -1);
    }

    public List<pagoDetalle> findpagoDetalleEntities(int maxResults, int firstResult) {
        return findpagoDetalleEntities(false, maxResults, firstResult);
    }

    private List<pagoDetalle> findpagoDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(pagoDetalle.class));
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

    public pagoDetalle findpagoDetalle(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(pagoDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getpagoDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<pagoDetalle> rt = cq.from(pagoDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
