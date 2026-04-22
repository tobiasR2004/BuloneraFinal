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
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.cliente;
import Bulonera.logica.cabecera_remito;
import Bulonera.logica.pago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobi2
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
            cuenta_corriente cc_pago = pago.getCc_pago();
            if (cc_pago != null) {
                cc_pago = em.getReference(cc_pago.getClass(), cc_pago.getId_cc());
                pago.setCc_pago(cc_pago);
            }
            cliente cliente_pago = pago.getCliente_pago();
            if (cliente_pago != null) {
                cliente_pago = em.getReference(cliente_pago.getClass(), cliente_pago.getNroClient());
                pago.setCliente_pago(cliente_pago);
            }
            cabecera_remito cabecRemitoAsociado = pago.getCabecRemitoAsociado();
            if (cabecRemitoAsociado != null) {
                cabecRemitoAsociado = em.getReference(cabecRemitoAsociado.getClass(), cabecRemitoAsociado.getIdRemito());
                pago.setCabecRemitoAsociado(cabecRemitoAsociado);
            }
            em.persist(pago);
            if (cc_pago != null) {
                cc_pago.getListaPagos_cc().add(pago);
                cc_pago = em.merge(cc_pago);
            }
            if (cliente_pago != null) {
                cliente_pago.getListaPagos_c().add(pago);
                cliente_pago = em.merge(cliente_pago);
            }
            if (cabecRemitoAsociado != null) {
                pago oldPagoAsociadoOfCabecRemitoAsociado = cabecRemitoAsociado.getPagoAsociado();
                if (oldPagoAsociadoOfCabecRemitoAsociado != null) {
                    oldPagoAsociadoOfCabecRemitoAsociado.setCabecRemitoAsociado(null);
                    oldPagoAsociadoOfCabecRemitoAsociado = em.merge(oldPagoAsociadoOfCabecRemitoAsociado);
                }
                cabecRemitoAsociado.setPagoAsociado(pago);
                cabecRemitoAsociado = em.merge(cabecRemitoAsociado);
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
            cuenta_corriente cc_pagoOld = persistentpago.getCc_pago();
            cuenta_corriente cc_pagoNew = pago.getCc_pago();
            cliente cliente_pagoOld = persistentpago.getCliente_pago();
            cliente cliente_pagoNew = pago.getCliente_pago();
            cabecera_remito cabecRemitoAsociadoOld = persistentpago.getCabecRemitoAsociado();
            cabecera_remito cabecRemitoAsociadoNew = pago.getCabecRemitoAsociado();
            if (cc_pagoNew != null) {
                cc_pagoNew = em.getReference(cc_pagoNew.getClass(), cc_pagoNew.getId_cc());
                pago.setCc_pago(cc_pagoNew);
            }
            if (cliente_pagoNew != null) {
                cliente_pagoNew = em.getReference(cliente_pagoNew.getClass(), cliente_pagoNew.getNroClient());
                pago.setCliente_pago(cliente_pagoNew);
            }
            if (cabecRemitoAsociadoNew != null) {
                cabecRemitoAsociadoNew = em.getReference(cabecRemitoAsociadoNew.getClass(), cabecRemitoAsociadoNew.getIdRemito());
                pago.setCabecRemitoAsociado(cabecRemitoAsociadoNew);
            }
            pago = em.merge(pago);
            if (cc_pagoOld != null && !cc_pagoOld.equals(cc_pagoNew)) {
                cc_pagoOld.getListaPagos_cc().remove(pago);
                cc_pagoOld = em.merge(cc_pagoOld);
            }
            if (cc_pagoNew != null && !cc_pagoNew.equals(cc_pagoOld)) {
                cc_pagoNew.getListaPagos_cc().add(pago);
                cc_pagoNew = em.merge(cc_pagoNew);
            }
            if (cliente_pagoOld != null && !cliente_pagoOld.equals(cliente_pagoNew)) {
                cliente_pagoOld.getListaPagos_c().remove(pago);
                cliente_pagoOld = em.merge(cliente_pagoOld);
            }
            if (cliente_pagoNew != null && !cliente_pagoNew.equals(cliente_pagoOld)) {
                cliente_pagoNew.getListaPagos_c().add(pago);
                cliente_pagoNew = em.merge(cliente_pagoNew);
            }
            if (cabecRemitoAsociadoOld != null && !cabecRemitoAsociadoOld.equals(cabecRemitoAsociadoNew)) {
                cabecRemitoAsociadoOld.setPagoAsociado(null);
                cabecRemitoAsociadoOld = em.merge(cabecRemitoAsociadoOld);
            }
            if (cabecRemitoAsociadoNew != null && !cabecRemitoAsociadoNew.equals(cabecRemitoAsociadoOld)) {
                pago oldPagoAsociadoOfCabecRemitoAsociado = cabecRemitoAsociadoNew.getPagoAsociado();
                if (oldPagoAsociadoOfCabecRemitoAsociado != null) {
                    oldPagoAsociadoOfCabecRemitoAsociado.setCabecRemitoAsociado(null);
                    oldPagoAsociadoOfCabecRemitoAsociado = em.merge(oldPagoAsociadoOfCabecRemitoAsociado);
                }
                cabecRemitoAsociadoNew.setPagoAsociado(pago);
                cabecRemitoAsociadoNew = em.merge(cabecRemitoAsociadoNew);
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
            cuenta_corriente cc_pago = pago.getCc_pago();
            if (cc_pago != null) {
                cc_pago.getListaPagos_cc().remove(pago);
                cc_pago = em.merge(cc_pago);
            }
            cliente cliente_pago = pago.getCliente_pago();
            if (cliente_pago != null) {
                cliente_pago.getListaPagos_c().remove(pago);
                cliente_pago = em.merge(cliente_pago);
            }
            cabecera_remito cabecRemitoAsociado = pago.getCabecRemitoAsociado();
            if (cabecRemitoAsociado != null) {
                cabecRemitoAsociado.setPagoAsociado(null);
                cabecRemitoAsociado = em.merge(cabecRemitoAsociado);
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
