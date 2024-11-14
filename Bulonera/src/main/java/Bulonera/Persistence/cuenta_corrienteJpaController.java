/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cuenta_corriente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Bulonera.logica.pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        if (cuenta_corriente.getListaPagos_cc() == null) {
            cuenta_corriente.setListaPagos_cc(new ArrayList<pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<pago> attachedListaPagos_cc = new ArrayList<pago>();
            for (pago listaPagos_ccpagoToAttach : cuenta_corriente.getListaPagos_cc()) {
                listaPagos_ccpagoToAttach = em.getReference(listaPagos_ccpagoToAttach.getClass(), listaPagos_ccpagoToAttach.getId_pago());
                attachedListaPagos_cc.add(listaPagos_ccpagoToAttach);
            }
            cuenta_corriente.setListaPagos_cc(attachedListaPagos_cc);
            em.persist(cuenta_corriente);
            for (pago listaPagos_ccpago : cuenta_corriente.getListaPagos_cc()) {
                cuenta_corriente oldCc_pagoOfListaPagos_ccpago = listaPagos_ccpago.getCc_pago();
                listaPagos_ccpago.setCc_pago(cuenta_corriente);
                listaPagos_ccpago = em.merge(listaPagos_ccpago);
                if (oldCc_pagoOfListaPagos_ccpago != null) {
                    oldCc_pagoOfListaPagos_ccpago.getListaPagos_cc().remove(listaPagos_ccpago);
                    oldCc_pagoOfListaPagos_ccpago = em.merge(oldCc_pagoOfListaPagos_ccpago);
                }
            }
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
            cuenta_corriente persistentcuenta_corriente = em.find(cuenta_corriente.class, cuenta_corriente.getId_cc());
            ArrayList<pago> listaPagos_ccOld = persistentcuenta_corriente.getListaPagos_cc();
            ArrayList<pago> listaPagos_ccNew = cuenta_corriente.getListaPagos_cc();
            ArrayList<pago> attachedListaPagos_ccNew = new ArrayList<pago>();
            for (pago listaPagos_ccNewpagoToAttach : listaPagos_ccNew) {
                listaPagos_ccNewpagoToAttach = em.getReference(listaPagos_ccNewpagoToAttach.getClass(), listaPagos_ccNewpagoToAttach.getId_pago());
                attachedListaPagos_ccNew.add(listaPagos_ccNewpagoToAttach);
            }
            listaPagos_ccNew = attachedListaPagos_ccNew;
            cuenta_corriente.setListaPagos_cc(listaPagos_ccNew);
            cuenta_corriente = em.merge(cuenta_corriente);
            for (pago listaPagos_ccOldpago : listaPagos_ccOld) {
                if (!listaPagos_ccNew.contains(listaPagos_ccOldpago)) {
                    listaPagos_ccOldpago.setCc_pago(null);
                    listaPagos_ccOldpago = em.merge(listaPagos_ccOldpago);
                }
            }
            for (pago listaPagos_ccNewpago : listaPagos_ccNew) {
                if (!listaPagos_ccOld.contains(listaPagos_ccNewpago)) {
                    cuenta_corriente oldCc_pagoOfListaPagos_ccNewpago = listaPagos_ccNewpago.getCc_pago();
                    listaPagos_ccNewpago.setCc_pago(cuenta_corriente);
                    listaPagos_ccNewpago = em.merge(listaPagos_ccNewpago);
                    if (oldCc_pagoOfListaPagos_ccNewpago != null && !oldCc_pagoOfListaPagos_ccNewpago.equals(cuenta_corriente)) {
                        oldCc_pagoOfListaPagos_ccNewpago.getListaPagos_cc().remove(listaPagos_ccNewpago);
                        oldCc_pagoOfListaPagos_ccNewpago = em.merge(oldCc_pagoOfListaPagos_ccNewpago);
                    }
                }
            }
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
            ArrayList<pago> listaPagos_cc = cuenta_corriente.getListaPagos_cc();
            for (pago listaPagos_ccpago : listaPagos_cc) {
                listaPagos_ccpago.setCc_pago(null);
                listaPagos_ccpago = em.merge(listaPagos_ccpago);
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
