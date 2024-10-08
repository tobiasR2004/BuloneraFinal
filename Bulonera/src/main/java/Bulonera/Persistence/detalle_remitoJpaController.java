/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.detalle_remito;
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
public class detalle_remitoJpaController implements Serializable {

    public detalle_remitoJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }


    public detalle_remitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(detalle_remito detalle_remito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalle_remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(detalle_remito detalle_remito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalle_remito = em.merge(detalle_remito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detalle_remito.getId_remito();
                if (finddetalle_remito(id) == null) {
                    throw new NonexistentEntityException("The detalle_remito with id " + id + " no longer exists.");
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
            detalle_remito detalle_remito;
            try {
                detalle_remito = em.getReference(detalle_remito.class, id);
                detalle_remito.getId_remito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle_remito with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalle_remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<detalle_remito> finddetalle_remitoEntities() {
        return finddetalle_remitoEntities(true, -1, -1);
    }

    public List<detalle_remito> finddetalle_remitoEntities(int maxResults, int firstResult) {
        return finddetalle_remitoEntities(false, maxResults, firstResult);
    }

    private List<detalle_remito> finddetalle_remitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(detalle_remito.class));
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

    public detalle_remito finddetalle_remito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(detalle_remito.class, id);
        } finally {
            em.close();
        }
    }

    public int getdetalle_remitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<detalle_remito> rt = cq.from(detalle_remito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
