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
import Bulonera.logica.pago;
import java.util.ArrayList;
import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobi2
 */
public class clienteJpaController implements Serializable {

    public clienteJpaController() {
        emf = Persistence.createEntityManagerFactory("buloneraPU");
    }

    public clienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cliente cliente) {
        if (cliente.getListaPagos_c() == null) {
            cliente.setListaPagos_c(new ArrayList<pago>());
        }
        if (cliente.getRemitos() == null) {
            cliente.setRemitos(new ArrayList<cabecera_remito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<pago> attachedListaPagos_c = new ArrayList<pago>();
            for (pago listaPagos_cpagoToAttach : cliente.getListaPagos_c()) {
                listaPagos_cpagoToAttach = em.getReference(listaPagos_cpagoToAttach.getClass(), listaPagos_cpagoToAttach.getId_pago());
                attachedListaPagos_c.add(listaPagos_cpagoToAttach);
            }
            cliente.setListaPagos_c(attachedListaPagos_c);
            List<cabecera_remito> attachedRemitos = new ArrayList<cabecera_remito>();
            for (cabecera_remito remitoscabecera_remitoToAttach : cliente.getRemitos()) {
                remitoscabecera_remitoToAttach = em.getReference(remitoscabecera_remitoToAttach.getClass(), remitoscabecera_remitoToAttach.getIdRemito());
                attachedRemitos.add(remitoscabecera_remitoToAttach);
            }
            cliente.setRemitos(attachedRemitos);
            em.persist(cliente);
            for (pago listaPagos_cpago : cliente.getListaPagos_c()) {
                cliente oldCliente_pagoOfListaPagos_cpago = listaPagos_cpago.getCliente_pago();
                listaPagos_cpago.setCliente_pago(cliente);
                listaPagos_cpago = em.merge(listaPagos_cpago);
                if (oldCliente_pagoOfListaPagos_cpago != null) {
                    oldCliente_pagoOfListaPagos_cpago.getListaPagos_c().remove(listaPagos_cpago);
                    oldCliente_pagoOfListaPagos_cpago = em.merge(oldCliente_pagoOfListaPagos_cpago);
                }
            }
            for (cabecera_remito remitoscabecera_remito : cliente.getRemitos()) {
                cliente oldClienteCabeceraOfRemitoscabecera_remito = remitoscabecera_remito.getClienteCabecera();
                remitoscabecera_remito.setClienteCabecera(cliente);
                remitoscabecera_remito = em.merge(remitoscabecera_remito);
                if (oldClienteCabeceraOfRemitoscabecera_remito != null) {
                    oldClienteCabeceraOfRemitoscabecera_remito.getRemitos().remove(remitoscabecera_remito);
                    oldClienteCabeceraOfRemitoscabecera_remito = em.merge(oldClienteCabeceraOfRemitoscabecera_remito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente persistentcliente = em.find(cliente.class, cliente.getNroClient());
            ArrayList<pago> listaPagos_cOld = persistentcliente.getListaPagos_c();
            ArrayList<pago> listaPagos_cNew = cliente.getListaPagos_c();
            List<cabecera_remito> remitosOld = persistentcliente.getRemitos();
            List<cabecera_remito> remitosNew = cliente.getRemitos();
            ArrayList<pago> attachedListaPagos_cNew = new ArrayList<pago>();
            for (pago listaPagos_cNewpagoToAttach : listaPagos_cNew) {
                listaPagos_cNewpagoToAttach = em.getReference(listaPagos_cNewpagoToAttach.getClass(), listaPagos_cNewpagoToAttach.getId_pago());
                attachedListaPagos_cNew.add(listaPagos_cNewpagoToAttach);
            }
            listaPagos_cNew = attachedListaPagos_cNew;
            cliente.setListaPagos_c(listaPagos_cNew);
            List<cabecera_remito> attachedRemitosNew = new ArrayList<cabecera_remito>();
            for (cabecera_remito remitosNewcabecera_remitoToAttach : remitosNew) {
                remitosNewcabecera_remitoToAttach = em.getReference(remitosNewcabecera_remitoToAttach.getClass(), remitosNewcabecera_remitoToAttach.getIdRemito());
                attachedRemitosNew.add(remitosNewcabecera_remitoToAttach);
            }
            remitosNew = attachedRemitosNew;
            cliente.setRemitos(remitosNew);
            cliente = em.merge(cliente);
            for (pago listaPagos_cOldpago : listaPagos_cOld) {
                if (!listaPagos_cNew.contains(listaPagos_cOldpago)) {
                    listaPagos_cOldpago.setCliente_pago(null);
                    listaPagos_cOldpago = em.merge(listaPagos_cOldpago);
                }
            }
            for (pago listaPagos_cNewpago : listaPagos_cNew) {
                if (!listaPagos_cOld.contains(listaPagos_cNewpago)) {
                    cliente oldCliente_pagoOfListaPagos_cNewpago = listaPagos_cNewpago.getCliente_pago();
                    listaPagos_cNewpago.setCliente_pago(cliente);
                    listaPagos_cNewpago = em.merge(listaPagos_cNewpago);
                    if (oldCliente_pagoOfListaPagos_cNewpago != null && !oldCliente_pagoOfListaPagos_cNewpago.equals(cliente)) {
                        oldCliente_pagoOfListaPagos_cNewpago.getListaPagos_c().remove(listaPagos_cNewpago);
                        oldCliente_pagoOfListaPagos_cNewpago = em.merge(oldCliente_pagoOfListaPagos_cNewpago);
                    }
                }
            }
            for (cabecera_remito remitosOldcabecera_remito : remitosOld) {
                if (!remitosNew.contains(remitosOldcabecera_remito)) {
                    remitosOldcabecera_remito.setClienteCabecera(null);
                    remitosOldcabecera_remito = em.merge(remitosOldcabecera_remito);
                }
            }
            for (cabecera_remito remitosNewcabecera_remito : remitosNew) {
                if (!remitosOld.contains(remitosNewcabecera_remito)) {
                    cliente oldClienteCabeceraOfRemitosNewcabecera_remito = remitosNewcabecera_remito.getClienteCabecera();
                    remitosNewcabecera_remito.setClienteCabecera(cliente);
                    remitosNewcabecera_remito = em.merge(remitosNewcabecera_remito);
                    if (oldClienteCabeceraOfRemitosNewcabecera_remito != null && !oldClienteCabeceraOfRemitosNewcabecera_remito.equals(cliente)) {
                        oldClienteCabeceraOfRemitosNewcabecera_remito.getRemitos().remove(remitosNewcabecera_remito);
                        oldClienteCabeceraOfRemitosNewcabecera_remito = em.merge(oldClienteCabeceraOfRemitosNewcabecera_remito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getNroClient();
                if (findcliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            cliente cliente;
            try {
                cliente = em.getReference(cliente.class, id);
                cliente.getNroClient();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            ArrayList<pago> listaPagos_c = cliente.getListaPagos_c();
            for (pago listaPagos_cpago : listaPagos_c) {
                listaPagos_cpago.setCliente_pago(null);
                listaPagos_cpago = em.merge(listaPagos_cpago);
            }
            List<cabecera_remito> remitos = cliente.getRemitos();
            for (cabecera_remito remitoscabecera_remito : remitos) {
                remitoscabecera_remito.setClienteCabecera(null);
                remitoscabecera_remito = em.merge(remitoscabecera_remito);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cliente> findclienteEntities() {
        return findclienteEntities(true, -1, -1);
    }

    public List<cliente> findclienteEntities(int maxResults, int firstResult) {
        return findclienteEntities(false, maxResults, firstResult);
    }

    private List<cliente> findclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cliente.class));
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

    public cliente findcliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cliente> rt = cq.from(cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
