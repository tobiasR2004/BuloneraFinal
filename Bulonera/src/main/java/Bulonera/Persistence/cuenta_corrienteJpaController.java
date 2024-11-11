/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cuenta_corriente;
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
public class cuenta_corrienteJpaController implements Serializable {

    public cuenta_corrienteJpaController() {
         emf = Persistence.createEntityManagerFactory("buloneraPU");
    }

    public cuenta_corrienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cuenta_corriente cuenta_corriente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cuenta_corriente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cuenta_corriente cuenta_corriente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cuenta_corriente = em.merge(cuenta_corriente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cuenta_corriente.getId_cc();
                if (findcuenta_corriente(id) == null) {
                    throw new NonexistentEntityException("The cuenta_corriente with id " + id + " no longer exists.");
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
            cuenta_corriente cuenta_corriente;
            try {
                cuenta_corriente = em.getReference(cuenta_corriente.class, id);
                cuenta_corriente.getId_cc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta_corriente with id " + id + " no longer exists.", enfe);
            }
            em.remove(cuenta_corriente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cuenta_corriente> findcuenta_corrienteEntities() {
        return findcuenta_corrienteEntities(true, -1, -1);
    }

    public List<cuenta_corriente> findcuenta_corrienteEntities(int maxResults, int firstResult) {
        return findcuenta_corrienteEntities(false, maxResults, firstResult);
    }

    private List<cuenta_corriente> findcuenta_corrienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cuenta_corriente.class));
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

    public cuenta_corriente findcuenta_corriente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cuenta_corriente.class, id);
        } finally {
            em.close();
        }
    }

    public int getcuenta_corrienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cuenta_corriente> rt = cq.from(cuenta_corriente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
