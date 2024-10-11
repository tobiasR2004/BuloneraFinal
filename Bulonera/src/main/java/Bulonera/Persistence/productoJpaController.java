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
import Bulonera.logica.detalle_remito;
import Bulonera.logica.producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobi2
 */
public class productoJpaController implements Serializable {

    public productoJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }
    

    public productoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalle_remito listadetalles = producto.getListadetalles();
            if (listadetalles != null) {
                listadetalles = em.getReference(listadetalles.getClass(), listadetalles.getId_remito());
                producto.setListadetalles(listadetalles);
            }
            em.persist(producto);
            if (listadetalles != null) {
                listadetalles.getProducdetalle().add(producto);
                listadetalles = em.merge(listadetalles);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto persistentproducto = em.find(producto.class, producto.getId_prod());
            detalle_remito listadetallesOld = persistentproducto.getListadetalles();
            detalle_remito listadetallesNew = producto.getListadetalles();
            if (listadetallesNew != null) {
                listadetallesNew = em.getReference(listadetallesNew.getClass(), listadetallesNew.getId_remito());
                producto.setListadetalles(listadetallesNew);
            }
            producto = em.merge(producto);
            if (listadetallesOld != null && !listadetallesOld.equals(listadetallesNew)) {
                listadetallesOld.getProducdetalle().remove(producto);
                listadetallesOld = em.merge(listadetallesOld);
            }
            if (listadetallesNew != null && !listadetallesNew.equals(listadetallesOld)) {
                listadetallesNew.getProducdetalle().add(producto);
                listadetallesNew = em.merge(listadetallesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId_prod();
                if (findproducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            producto producto;
            try {
                producto = em.getReference(producto.class, id);
                producto.getId_prod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            detalle_remito listadetalles = producto.getListadetalles();
            if (listadetalles != null) {
                listadetalles.getProducdetalle().remove(producto);
                listadetalles = em.merge(listadetalles);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<producto> findproductoEntities() {
        return findproductoEntities(true, -1, -1);
    }

    public List<producto> findproductoEntities(int maxResults, int firstResult) {
        return findproductoEntities(false, maxResults, firstResult);
    }

    private List<producto> findproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(producto.class));
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

    public producto findproducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<producto> rt = cq.from(producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
