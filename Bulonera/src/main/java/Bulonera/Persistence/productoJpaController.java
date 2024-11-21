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
import java.util.ArrayList;
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
        if (producto.getDetalles() == null) {
            producto.setDetalles(new ArrayList<detalle_remito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<detalle_remito> attachedDetalles = new ArrayList<detalle_remito>();
            for (detalle_remito detallesdetalle_remitoToAttach : producto.getDetalles()) {
                detallesdetalle_remitoToAttach = em.getReference(detallesdetalle_remitoToAttach.getClass(), detallesdetalle_remitoToAttach.getId_remito());
                attachedDetalles.add(detallesdetalle_remitoToAttach);
            }
            producto.setDetalles(attachedDetalles);
            em.persist(producto);
            for (detalle_remito detallesdetalle_remito : producto.getDetalles()) {
                producto oldProducDetalleOfDetallesdetalle_remito = detallesdetalle_remito.getProducDetalle();
                detallesdetalle_remito.setProducDetalle(producto);
                detallesdetalle_remito = em.merge(detallesdetalle_remito);
                if (oldProducDetalleOfDetallesdetalle_remito != null) {
                    oldProducDetalleOfDetallesdetalle_remito.getDetalles().remove(detallesdetalle_remito);
                    oldProducDetalleOfDetallesdetalle_remito = em.merge(oldProducDetalleOfDetallesdetalle_remito);
                }
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
            List<detalle_remito> detallesOld = persistentproducto.getDetalles();
            List<detalle_remito> detallesNew = producto.getDetalles();
            List<detalle_remito> attachedDetallesNew = new ArrayList<detalle_remito>();
            for (detalle_remito detallesNewdetalle_remitoToAttach : detallesNew) {
                detallesNewdetalle_remitoToAttach = em.getReference(detallesNewdetalle_remitoToAttach.getClass(), detallesNewdetalle_remitoToAttach.getId_remito());
                attachedDetallesNew.add(detallesNewdetalle_remitoToAttach);
            }
            detallesNew = attachedDetallesNew;
            producto.setDetalles(detallesNew);
            producto = em.merge(producto);
            for (detalle_remito detallesOlddetalle_remito : detallesOld) {
                if (!detallesNew.contains(detallesOlddetalle_remito)) {
                    detallesOlddetalle_remito.setProducDetalle(null);
                    detallesOlddetalle_remito = em.merge(detallesOlddetalle_remito);
                }
            }
            for (detalle_remito detallesNewdetalle_remito : detallesNew) {
                if (!detallesOld.contains(detallesNewdetalle_remito)) {
                    producto oldProducDetalleOfDetallesNewdetalle_remito = detallesNewdetalle_remito.getProducDetalle();
                    detallesNewdetalle_remito.setProducDetalle(producto);
                    detallesNewdetalle_remito = em.merge(detallesNewdetalle_remito);
                    if (oldProducDetalleOfDetallesNewdetalle_remito != null && !oldProducDetalleOfDetallesNewdetalle_remito.equals(producto)) {
                        oldProducDetalleOfDetallesNewdetalle_remito.getDetalles().remove(detallesNewdetalle_remito);
                        oldProducDetalleOfDetallesNewdetalle_remito = em.merge(oldProducDetalleOfDetallesNewdetalle_remito);
                    }
                }
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
            List<detalle_remito> detalles = producto.getDetalles();
            for (detalle_remito detallesdetalle_remito : detalles) {
                detallesdetalle_remito.setProducDetalle(null);
                detallesdetalle_remito = em.merge(detallesdetalle_remito);
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
