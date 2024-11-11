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
import Bulonera.logica.cabecera_remito;
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
            cabecera_remito cabecdetalleremito = detalle_remito.getCabecdetalleremito();
            if (cabecdetalleremito != null) {
                cabecdetalleremito = em.getReference(cabecdetalleremito.getClass(), cabecdetalleremito.getId_remito());
                detalle_remito.setCabecdetalleremito(cabecdetalleremito);
            }
            producto producDetalle = detalle_remito.getProducDetalle();
            if (producDetalle != null) {
                producDetalle = em.getReference(producDetalle.getClass(), producDetalle.getId_prod());
                detalle_remito.setProducDetalle(producDetalle);
            }
            em.persist(detalle_remito);
            if (cabecdetalleremito != null) {
                cabecdetalleremito.getListadetalles().add(detalle_remito);
                cabecdetalleremito = em.merge(cabecdetalleremito);
            }
            if (producDetalle != null) {
                producDetalle.getDetalles().add(detalle_remito);
                producDetalle = em.merge(producDetalle);
            }
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
            detalle_remito persistentdetalle_remito = em.find(detalle_remito.class, detalle_remito.getId_remito());
            cabecera_remito cabecdetalleremitoOld = persistentdetalle_remito.getCabecdetalleremito();
            cabecera_remito cabecdetalleremitoNew = detalle_remito.getCabecdetalleremito();
            producto producDetalleOld = persistentdetalle_remito.getProducDetalle();
            producto producDetalleNew = detalle_remito.getProducDetalle();
            if (cabecdetalleremitoNew != null) {
                cabecdetalleremitoNew = em.getReference(cabecdetalleremitoNew.getClass(), cabecdetalleremitoNew.getId_remito());
                detalle_remito.setCabecdetalleremito(cabecdetalleremitoNew);
            }
            if (producDetalleNew != null) {
                producDetalleNew = em.getReference(producDetalleNew.getClass(), producDetalleNew.getId_prod());
                detalle_remito.setProducDetalle(producDetalleNew);
            }
            detalle_remito = em.merge(detalle_remito);
            if (cabecdetalleremitoOld != null && !cabecdetalleremitoOld.equals(cabecdetalleremitoNew)) {
                cabecdetalleremitoOld.getListadetalles().remove(detalle_remito);
                cabecdetalleremitoOld = em.merge(cabecdetalleremitoOld);
            }
            if (cabecdetalleremitoNew != null && !cabecdetalleremitoNew.equals(cabecdetalleremitoOld)) {
                cabecdetalleremitoNew.getListadetalles().add(detalle_remito);
                cabecdetalleremitoNew = em.merge(cabecdetalleremitoNew);
            }
            if (producDetalleOld != null && !producDetalleOld.equals(producDetalleNew)) {
                producDetalleOld.getDetalles().remove(detalle_remito);
                producDetalleOld = em.merge(producDetalleOld);
            }
            if (producDetalleNew != null && !producDetalleNew.equals(producDetalleOld)) {
                producDetalleNew.getDetalles().add(detalle_remito);
                producDetalleNew = em.merge(producDetalleNew);
            }
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
            cabecera_remito cabecdetalleremito = detalle_remito.getCabecdetalleremito();
            if (cabecdetalleremito != null) {
                cabecdetalleremito.getListadetalles().remove(detalle_remito);
                cabecdetalleremito = em.merge(cabecdetalleremito);
            }
            producto producDetalle = detalle_remito.getProducDetalle();
            if (producDetalle != null) {
                producDetalle.getDetalles().remove(detalle_remito);
                producDetalle = em.merge(producDetalle);
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
