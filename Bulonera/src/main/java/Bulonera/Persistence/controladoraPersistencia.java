/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.Persistence.exceptions.PreexistingEntityException;
import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
import Bulonera.logica.pago;
import Bulonera.logica.producto;
import Bulonera.logica.usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author tobi2
 */
public class controladoraPersistencia {

    cabecera_remitoJpaController cabecera_remitoJpa = new cabecera_remitoJpaController();
    clienteJpaController clienteJpa = new clienteJpaController();
    cuenta_corrienteJpaController cuenta_corrienteJpa = new cuenta_corrienteJpaController();
    detalle_remitoJpaController detalle_remitoJpa = new detalle_remitoJpaController();
    pagoJpaController pagoJpa = new pagoJpaController();
    productoJpaController productoJpa = new productoJpaController();
    usuarioJpaController usuarioJpa = new usuarioJpaController();
    
    
    public controladoraPersistencia() {
    }
    
    //CRUD CABECERA_REMITO
    
     public void crearcabecremito(cabecera_remito idcabec) {
           cabecera_remitoJpa.create(idcabec); 
    }

    public void eliminarcabecremito(int id) {
       try{
          cabecera_remitoJpa.destroy(id);
       } catch(NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
       }
    }

    public void modifcabecremito(cabecera_remito idcabec) {
        try {
            cabecera_remitoJpa.edit(idcabec);      
        } catch (Exception ex){
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public cabecera_remito consulcabecremito(int id) {
        return cabecera_remitoJpa.findcabecera_remito(id);
    }

    public ArrayList<cabecera_remito> consultarcabecerarem() {
        List<cabecera_remito> listaC = cabecera_remitoJpa.findcabecera_remitoEntities();
        ArrayList<cabecera_remito> listacabecremi = new ArrayList<cabecera_remito>(listaC);
        return listacabecremi;
    }

    public List<cabecera_remito> consultarCabecNroClient(int nroCliente) {
    EntityManager em = cabecera_remitoJpa.getEntityManager();
    List<cabecera_remito> cabecList = new ArrayList<>();
    try {
        String jpql = "SELECT c FROM cabecera_remito c WHERE c.clienteCabecera.nroClient = :nroCliente ORDER BY c.idRemito ASC";
        Query query = em.createQuery(jpql);
        query.setParameter("nroCliente", nroCliente);
        cabecList = query.getResultList();
    } catch (NoResultException e) {
        System.out.println("No se encontró ninguna cabecera de remito.");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        em.close();
    }
    return cabecList;
}
    
    //CRUD CLIENTE
    
    public void crearCliente(cliente cliente1) {
        clienteJpa.create(cliente1);
    }

    public void eliminarCliente(int id) {
       try{
         clienteJpa.destroy(id);
       } catch(NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
       }
    }

    public void modifCliente(cliente cliente1) {
        try {
           clienteJpa.edit(cliente1);      
        } catch (Exception ex){
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
        public List<cliente> getClientes() {
        EntityManager em = clienteJpa.getEntityManager();
        List<cliente> clientes = null;
        try {
            String jpql = "SELECT c FROM cliente c";
            Query query = em.createQuery(jpql);
            clientes = query.getResultList();
        }catch (NoResultException e) {
                System.out.println("No se encontraron clientes");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            em.close();
        }
        return clientes;
}
    
    public cliente buscarDniClient(int dni) {
        EntityManager em = clienteJpa.getEntityManager();
        cliente client = null;

            try {
                String jpql = "SELECT c FROM cliente c WHERE c.dniCliente = :dni";
                Query query = em.createQuery(jpql);
                query.setParameter("dni", dni);

                client = (cliente) query.getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No se encontró el cliente con DNI: " + dni);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }

    return client;
    }
        public cliente buscarNombCliente(String razonSoc) {
        EntityManager em = clienteJpa.getEntityManager();
        cliente client2 = null;

            try {
                String jpql = "SELECT c FROM cliente c WHERE c.razonSocial = :razonSoc";
                Query query = em.createQuery(jpql);
                query.setParameter("razonSoc", razonSoc);

                client2 = (cliente) query.getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No se encontró el cliente: " + client2 );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }

    return client2;
}
    public cliente consultarcliente(int id) {
        return clienteJpa.findcliente(id);
    }

    public ArrayList<cliente> consultarClienList() {
        List<cliente> listaClient = clienteJpa.findclienteEntities();
        ArrayList<cliente> listaClientes = new ArrayList<cliente>(listaClient);
        return listaClientes;
    }
    
    //CRUD CUENTACORRIENTE

    public void crearCc(cuenta_corriente cC) {
        cuenta_corrienteJpa.create(cC);
    }

    public void eliminarCc(int id) {
        try {
            cuenta_corrienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifCc(cuenta_corriente cC1) {
        try {
            cuenta_corrienteJpa.edit(cC1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public cuenta_corriente consultarCcporcabec(cabecera_remito idcabec){
        EntityManager em = cuenta_corrienteJpa.getEntityManager();
        cuenta_corriente resultado = null;

      try {
          String query = "SELECT cc FROM cuenta_corriente cc WHERE cc.cabeceraremito = :idcabec";
          TypedQuery<cuenta_corriente> typedQuery = em.createQuery(query, cuenta_corriente.class);
          typedQuery.setParameter("idcabec", idcabec);

          List<cuenta_corriente> resultados = typedQuery.getResultList();
          if (!resultados.isEmpty()) {
              resultado = resultados.get(0); // Retorna el primer resultado si existe
          }
      } catch (Exception e) {
          e.printStackTrace(); // O usa un logger
      } finally {
          em.close(); // Cierra el EntityManager
      }

      return resultado;
    }
    
    public cuenta_corriente consultarCc(int id) {
        return cuenta_corrienteJpa.findcuenta_corriente(id);
    }

   public List<cuenta_corriente> consultarCcList(cabecera_remito cabec) {
    EntityManager em = cuenta_corrienteJpa.getEntityManager();
    
    String query = "SELECT cc FROM cuenta_corriente cc " +
                   "JOIN cc.cabeceraremito cr " +
                   "WHERE cr.clienteCabecera.nroClient = :nroCliente " +
                   "ORDER BY cc.id_cc ASC";
                   
    TypedQuery<cuenta_corriente> typedQuery = em.createQuery(query, cuenta_corriente.class);
    
    // Establecer el parámetro nroCliente
    typedQuery.setParameter("nroCliente", cabec.getClienteCabecera().getNroClient());
    
    return typedQuery.getResultList();
}

   
       
    public void eliminarCCPorCabecera(cabecera_remito cabecera) {
        EntityManager em = cuenta_corrienteJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            // Eliminar los detalles asociados a la cabecera
            Query query = em.createQuery("DELETE FROM cuenta_corriente c WHERE c.cabeceraremito = :cabecera");
            query.setParameter("cabecera", cabecera);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    
    public void actualizarSaldoCuentaCorriente(int idCuentaCorriente) {
        
        EntityManager em = cuenta_corrienteJpa.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            // Sumar los importes de los remitos asociados a la cuenta corriente
            String sumaImportesQuery = "SELECT SUM(cr.importe_total) FROM cabecera_remito cr " +
                                       "WHERE cr.idRemito IN (SELECT cc.cabeceraremito.idRemito FROM cuenta_corriente cc WHERE cc.id_cc = :idCuenta)";
            Query querySuma = em.createQuery(sumaImportesQuery);
            querySuma.setParameter("idCuenta", idCuentaCorriente);

            Double saldoTotal = (Double) querySuma.getSingleResult();

            if (saldoTotal == null) {
                saldoTotal = 0.0; // Evitar valores nulos
            }

            // Actualizar el saldo en la cuenta corriente
            String actualizarCuentaQuery = "UPDATE cuenta_corriente cc SET cc.saldo_cc = :saldoTotal, cc.debe_cc = :saldoTotal WHERE cc.id_cc = :idCuenta";
            Query queryActualizarCuenta = em.createQuery(actualizarCuentaQuery);
            queryActualizarCuenta.setParameter("saldoTotal", saldoTotal);
            queryActualizarCuenta.setParameter("idCuenta", idCuentaCorriente);
            queryActualizarCuenta.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al actualizar el saldo de la cuenta corriente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    //CRUD DETALLE REMITO
    
    public void crearDetalle(detalle_remito detalle1) {
        detalle_remitoJpa.create(detalle1);
    }

    public void eliminarDetalle(int id) {
        try {
            detalle_remitoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPorIdCabecera(int idCabecera) {
        EntityManager em = detalle_remitoJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM detalle_remito d WHERE d.cabecera.id = :idCabecera");
            query.setParameter("idCabecera", idCabecera);
            query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Registros eliminados con idCabecera: " + idCabecera); // LOG
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    
        public cabecera_remito obtenerCabeceraRemitoPorId(int idCabecera) {
        EntityManager em = cabecera_remitoJpa.getEntityManager();
        try {
            return em.find(cabecera_remito.class, idCabecera);
        } finally {
            em.close();
        }
    }
    
    public void eliminarDetallesPorCabecera(cabecera_remito cabecera) {
        EntityManager em = detalle_remitoJpa.getEntityManager();
        try {
            em.getTransaction().begin();

            // Eliminar los detalles asociados a la cabecera
            Query query = em.createQuery("DELETE FROM detalle_remito d WHERE d.cabecdetalleremito = :cabecera");
            query.setParameter("cabecera", cabecera);
            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public void modifDetalle(detalle_remito detalle1) {
        try {
            detalle_remitoJpa.edit(detalle1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public detalle_remito consultarDetalle(int id) {
      return detalle_remitoJpa.finddetalle_remito(id);
    }

    public List<detalle_remito> consultarDetalleList() {
          EntityManager em = detalle_remitoJpa.getEntityManager();
         try {
             String jpql = "SELECT dr FROM detalle_remito dr JOIN FETCH dr.producDetalle";
             Query query = em.createQuery(jpql);
             return query.getResultList();
         } finally {
             em.close();
         }
    }
    
    public List<detalle_remito> consultarListaDetalles(){
        return detalle_remitoJpa.finddetalle_remitoEntities();
    }
    
    public List<detalle_remito> consultarDetalleListCabec(List<Integer> remitosSeleccionados) {
        EntityManager em = detalle_remitoJpa.getEntityManager();

        // Consulta JPQL para filtrar solo por los remitos seleccionados
        String query = "SELECT dr FROM detalle_remito dr " +
                       "WHERE dr.cabecdetalleremito IN :remitosSeleccionados";

        TypedQuery<detalle_remito> typedQuery = em.createQuery(query, detalle_remito.class);
        typedQuery.setParameter("remitosSeleccionados", remitosSeleccionados);  // Lista de remitos seleccionados

        return typedQuery.getResultList();
    }
    
    
    
    public void actualizarReferenciasPorCodProd() {
    EntityManager em = detalle_remitoJpa.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
        transaction.begin();

        // 1. Obtener todos los productos de la base de datos
        List<producto> productos = em.createQuery("SELECT p FROM producto p", producto.class).getResultList();
        Map<String, producto> productosMap = new HashMap<>();
        
        // 2. Cargar los productos en el mapa por cod_prod
        for (producto prod : productos) {
            productosMap.put(prod.getCod_prod(), prod);
        }

        // 3. Obtener todos los detalles de remitos
        List<detalle_remito> detalles = em.createQuery("SELECT d FROM detalle_remito d", detalle_remito.class).getResultList();

        // 4. Actualizar las referencias de producto en detalle_remito
        for (detalle_remito detalle : detalles) {
            if (detalle.getProducDetalle() != null) {
                // Recuperamos el producto usando su cod_prod
                producto nuevoProducto = productosMap.get(detalle.getProducDetalle().getCod_prod());
                
                if (nuevoProducto != null) {
                    // Actualizamos la referencia en detalle_remito
                    detalle.setProducDetalle(nuevoProducto);
                    em.merge(detalle);  // Persistir el detalle con la nueva referencia
                } else {
                    // Log de error si no se encuentra el producto
                    System.out.println("Producto no encontrado para cod_prod: " + detalle.getProducDetalle().getCod_prod());
                }
            }
        }

        // 5. Confirmar la transacción
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();  // Log de errores
    } finally {
        em.close();
    }
}
    
         public void actualizarPreciosDetalleRemito(String codProducto, double nuevoPrecioVenta, double nuevoImporte) {
           EntityManager em = detalle_remitoJpa.getEntityManager();
           EntityTransaction transaction = em.getTransaction();

           try {
               transaction.begin();

               String query = "UPDATE detalle_remito dr " +
                              "SET dr.precio_unit = :nuevoPrecio, dr.importe = :nuevoImporte " +
                              "WHERE dr.cod_prod = :codProducto";

               Query updateQuery = em.createQuery(query);
               updateQuery.setParameter("nuevoPrecio", nuevoPrecioVenta);
               updateQuery.setParameter("nuevoImporte", nuevoImporte);
               updateQuery.setParameter("codProducto", codProducto);

               int filasActualizadas = updateQuery.executeUpdate();
               System.out.println("Filas actualizadas en detalle_remito: " + filasActualizadas);

               transaction.commit();
           } catch (Exception e) {
               if (transaction.isActive()) {
                   transaction.rollback();
               }
               throw new RuntimeException("Error al actualizar los precios en detalle_remito: " + e.getMessage());
           } finally {
               em.close();
           }
       }
 
public void actualizarImporteTotal( int cabecdetalle) {
    EntityManager em = detalle_remitoJpa.getEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
        transaction.begin();

        // Consulta para obtener la suma de los importes de todos los detalles asociados a la cabecera
        String sumaImportesQuery = "SELECT SUM(dr.importe) FROM detalle_remito dr WHERE dr.cabecdetalleremito.idRemito = :idCabecera";
        Query querySuma = em.createQuery(sumaImportesQuery);
        querySuma.setParameter("idCabecera", cabecdetalle);

        Double importeTotalCabecera = (Double) querySuma.getSingleResult();

        if (importeTotalCabecera == null) {
            importeTotalCabecera = 0.0; // Asegurarse de que no sea nulo
        }

        // Actualizar el importe_total en todos los detalles de la misma cabecera
        String actualizarDetallesQuery = "UPDATE detalle_remito dr SET dr.importe_total = :importeTotalCabecera WHERE dr.cabecdetalleremito.idRemito = :idCabecera";
        Query queryActualizarDetalles = em.createQuery(actualizarDetallesQuery);
        queryActualizarDetalles.setParameter("importeTotalCabecera", importeTotalCabecera);
        queryActualizarDetalles.setParameter("idCabecera", cabecdetalle);
        queryActualizarDetalles.executeUpdate();

        // Actualizar el importe_total en la cabecera
        String actualizarCabeceraQuery = "UPDATE cabecera_remito cr SET cr.importe_total = :importeTotalCabecera WHERE cr.idRemito = :idCabecera";
        Query queryActualizarCabecera = em.createQuery(actualizarCabeceraQuery);
        queryActualizarCabecera.setParameter("importeTotalCabecera", importeTotalCabecera);
        queryActualizarCabecera.setParameter("idCabecera", cabecdetalle);
        queryActualizarCabecera.executeUpdate();

        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw new RuntimeException("Error al actualizar el importe total de la cabecera y sus detalles: " + e.getMessage());
    } finally {
        em.close();
    }
}
    
    
    //CRUD PAGO
    public void crearPago(pago pago1) {
        pagoJpa.create(pago1); 
    }

    public void eliminarPago(int id) {
        try {
            pagoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifPago(pago pago1) {
        try {
            pagoJpa.edit(pago1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public pago consultarPago(int id) {
        return pagoJpa.findpago(id);
    }

    public ArrayList<pago> consultarPagoList() {
        List<pago> listaP = pagoJpa.findpagoEntities();
        ArrayList<pago> listaPagos = new ArrayList<pago>(listaP);
        return listaPagos;
    }

    
    //CRUD PRODUCTO
    public void crearProducto(producto prod1) {
            try {
                productoJpa.create(prod1);
            } catch (Exception ex) {
                Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void eliminarProducto(int id) {
        try {
            productoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifProducto(producto prod1) {
        try {
            productoJpa.edit(prod1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public producto buscarProductoPorCodProd(String codProd) {
        EntityManager em = clienteJpa.getEntityManager();
        try {
            TypedQuery<producto> query = em.createQuery("SELECT p FROM producto p WHERE p.cod_prod = :codProd", producto.class);
            query.setParameter("codProd", codProd);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public producto buscarProducto(int id){
        return productoJpa.findproducto(id);
    }

    public ArrayList<producto> consultarProductosList() {
        List<producto> listaPr = productoJpa.findproductoEntities();
        ArrayList<producto> listaProductos = new ArrayList<producto>(listaPr);
        return listaProductos;
    }
    
    public void guardarProduct(List<producto> productos)  throws Exception {
        EntityManager em = productoJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            for (producto prod : productos) {
                em.persist(prod); // Persiste cada producto en la base de datos
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error guardando productos: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void vaciarProductos(){
    EntityManager em = productoJpa.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            em.createQuery("DELETE FROM producto").executeUpdate();
             transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
              transaction.rollback();
            }
            throw new RuntimeException("Error al limpiar la base de datos", e);
        } finally {
            em.close();
        }
    }
    
    //CRUD USUARIO

    public void crearUsuario(usuario user1) {
        usuarioJpa.create(user1);
    }

    public void eliminarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifUsuario(usuario user1) {
        try {
            usuarioJpa.edit(user1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public usuario consultarUsuario(int id) {
        return usuarioJpa.findusuario(id);
    }

    public ArrayList<usuario> consultarUsuariosList() {
        List<usuario> listaUs = usuarioJpa.findusuarioEntities();
        ArrayList<usuario> listaUsuarios = new ArrayList<usuario>(listaUs);
        return listaUsuarios;
    }

 
}
