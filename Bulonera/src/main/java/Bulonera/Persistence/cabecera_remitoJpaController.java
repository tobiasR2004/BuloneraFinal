/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cabecera_remito;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Bulonera.logica.detalle_remito;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author tobi2
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
        if (cabecera_remito.getListadetalles() == null) {
            cabecera_remito.setListadetalles(new ArrayList<detalle_remito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<detalle_remito> attachedListadetalles = new ArrayList<detalle_remito>();
            for (detalle_remito listadetallesdetalle_remitoToAttach : cabecera_remito.getListadetalles()) {
                listadetallesdetalle_remitoToAttach = em.getReference(listadetallesdetalle_remitoToAttach.getClass(), listadetallesdetalle_remitoToAttach.getId_remito());
                attachedListadetalles.add(listadetallesdetalle_remitoToAttach);
            }
            cabecera_remito.setListadetalles(attachedListadetalles);
            em.persist(cabecera_remito);
            for (detalle_remito listadetallesdetalle_remito : cabecera_remito.getListadetalles()) {
                cabecera_remito oldCabecdetalleremitoOfListadetallesdetalle_remito = listadetallesdetalle_remito.getCabecdetalleremito();
                listadetallesdetalle_remito.setCabecdetalleremito(cabecera_remito);
                listadetallesdetalle_remito = em.merge(listadetallesdetalle_remito);
                if (oldCabecdetalleremitoOfListadetallesdetalle_remito != null) {
                    oldCabecdetalleremitoOfListadetallesdetalle_remito.getListadetalles().remove(listadetallesdetalle_remito);
                    oldCabecdetalleremitoOfListadetallesdetalle_remito = em.merge(oldCabecdetalleremitoOfListadetallesdetalle_remito);
                }
            }
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
            cabecera_remito persistentcabecera_remito = em.find(cabecera_remito.class, cabecera_remito.getIdRemito());
            ArrayList<detalle_remito> listadetallesOld = persistentcabecera_remito.getListadetalles();
            ArrayList<detalle_remito> listadetallesNew = cabecera_remito.getListadetalles();
            ArrayList<detalle_remito> attachedListadetallesNew = new ArrayList<detalle_remito>();
            for (detalle_remito listadetallesNewdetalle_remitoToAttach : listadetallesNew) {
                listadetallesNewdetalle_remitoToAttach = em.getReference(listadetallesNewdetalle_remitoToAttach.getClass(), listadetallesNewdetalle_remitoToAttach.getId_remito());
                attachedListadetallesNew.add(listadetallesNewdetalle_remitoToAttach);
            }
            listadetallesNew = attachedListadetallesNew;
            cabecera_remito.setListadetalles(listadetallesNew);
            cabecera_remito = em.merge(cabecera_remito);
            for (detalle_remito listadetallesOlddetalle_remito : listadetallesOld) {
                if (!listadetallesNew.contains(listadetallesOlddetalle_remito)) {
                    listadetallesOlddetalle_remito.setCabecdetalleremito(null);
                    listadetallesOlddetalle_remito = em.merge(listadetallesOlddetalle_remito);
                }
            }
            for (detalle_remito listadetallesNewdetalle_remito : listadetallesNew) {
                if (!listadetallesOld.contains(listadetallesNewdetalle_remito)) {
                    cabecera_remito oldCabecdetalleremitoOfListadetallesNewdetalle_remito = listadetallesNewdetalle_remito.getCabecdetalleremito();
                    listadetallesNewdetalle_remito.setCabecdetalleremito(cabecera_remito);
                    listadetallesNewdetalle_remito = em.merge(listadetallesNewdetalle_remito);
                    if (oldCabecdetalleremitoOfListadetallesNewdetalle_remito != null && !oldCabecdetalleremitoOfListadetallesNewdetalle_remito.equals(cabecera_remito)) {
                        oldCabecdetalleremitoOfListadetallesNewdetalle_remito.getListadetalles().remove(listadetallesNewdetalle_remito);
                        oldCabecdetalleremitoOfListadetallesNewdetalle_remito = em.merge(oldCabecdetalleremitoOfListadetallesNewdetalle_remito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cabecera_remito.getIdRemito();
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
                cabecera_remito.getIdRemito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabecera_remito with id " + id + " no longer exists.", enfe);
            }
            ArrayList<detalle_remito> listadetalles = cabecera_remito.getListadetalles();
            for (detalle_remito listadetallesdetalle_remito : listadetalles) {
                listadetallesdetalle_remito.setCabecdetalleremito(null);
                listadetallesdetalle_remito = em.merge(listadetallesdetalle_remito);
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
            return em.createQuery("SELECT c FROM cabecera_remito c WHERE c.cliente_cabecera.nro_client = :id", cabecera_remito.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
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
