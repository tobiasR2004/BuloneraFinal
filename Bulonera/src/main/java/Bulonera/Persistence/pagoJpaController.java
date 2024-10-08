/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Bulonera.logica.cliente;
import Bulonera.logica.pago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author Alumno
 */
public class pagoJpaController implements Serializable {

    public pagoJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }
    
    public pagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente cliente_pago = pago.getCliente_pago();
            if (cliente_pago != null) {
                cliente_pago = em.getReference(cliente_pago.getClass(), cliente_pago.getNro_client());
                pago.setCliente_pago(cliente_pago);
            }
            em.persist(pago);
            if (cliente_pago != null) {
                cliente_pago.getListaPagos_c().add(pago);
                cliente_pago = em.merge(cliente_pago);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pago persistentpago = em.find(pago.class, pago.getId_pago());
            cliente cliente_pagoOld = persistentpago.getCliente_pago();
            cliente cliente_pagoNew = pago.getCliente_pago();
            if (cliente_pagoNew != null) {
                cliente_pagoNew = em.getReference(cliente_pagoNew.getClass(), cliente_pagoNew.getNro_client());
                pago.setCliente_pago(cliente_pagoNew);
            }
            pago = em.merge(pago);
            if (cliente_pagoOld != null && !cliente_pagoOld.equals(cliente_pagoNew)) {
                cliente_pagoOld.getListaPagos_c().remove(pago);
                cliente_pagoOld = em.merge(cliente_pagoOld);
            }
            if (cliente_pagoNew != null && !cliente_pagoNew.equals(cliente_pagoOld)) {
                cliente_pagoNew.getListaPagos_c().add(pago);
                cliente_pagoNew = em.merge(cliente_pagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pago.getId_pago();
                if (findpago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            pago pago;
            try {
                pago = em.getReference(pago.class, id);
                pago.getId_pago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            cliente cliente_pago = pago.getCliente_pago();
            if (cliente_pago != null) {
                cliente_pago.getListaPagos_c().remove(pago);
                cliente_pago = em.merge(cliente_pago);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<pago> findpagoEntities() {
        return findpagoEntities(true, -1, -1);
    }

    public List<pago> findpagoEntities(int maxResults, int firstResult) {
        return findpagoEntities(false, maxResults, firstResult);
    }

    private List<pago> findpagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(pago.class));
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

    public pago findpago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getpagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<pago> rt = cq.from(pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}