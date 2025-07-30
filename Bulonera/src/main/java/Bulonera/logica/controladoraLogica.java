/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;
import Bulonera.logica.usuario;
import Bulonera.Persistence.controladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alumno
 */
public class controladoraLogica {
    controladoraPersistencia ctrlpersis = new controladoraPersistencia();
    
    //CRUD CABECERA REMITO
    
    public void crearcabecremito(cabecera_remito cabecera1){
        ctrlpersis.crearcabecremito(cabecera1);
    }
    
    public void eliminarcabecremito(int nroCliente){
        ctrlpersis.eliminarcabecremito(nroCliente);
    }
    
    public void modifcabecremito(cabecera_remito cabecera1){
        ctrlpersis.modifcabecremito(cabecera1);
    }
    
    public cabecera_remito consultarCabecremito(int id){
        return ctrlpersis.consulcabecremito(id);
    }
    
    public ArrayList<cabecera_remito> cosultarCabeceraRemList(){
        return ctrlpersis.consultarcabecerarem();
    }
    
    public void eliminarCabecIdCli(int idCli){
        ctrlpersis.eliminarcCabecPorCliente(idCli);
    }
   
    public List<cabecera_remito> consultarCabecNroClient(int nroClient){
        return ctrlpersis.consultarCabecNroClient(nroClient);
    }
    // CRUD CLIENTE
    
    public void crearCliente(cliente cliente1){
        ctrlpersis.crearCliente(cliente1);
    }
    
    public void eliminarCliente(int id){
        ctrlpersis.eliminarCliente(id);
    }
    
    public void modifCliente(cliente cliente1){
        ctrlpersis.modifCliente(cliente1);
    }
    
    public cliente buscarDniCliente(int dni){
        return ctrlpersis.buscarDniClient(dni);
    }
    
    public cliente consultarCliente(int id){
        return ctrlpersis.consultarcliente(id);
    }
    
    public ArrayList<cliente> consultarClienteList(){
        return ctrlpersis.consultarClienList();
    }
    
    public cliente buscarNombCliente(String Razonsoc)
    { 
        return ctrlpersis.buscarNombCliente(Razonsoc);
    }  
        
    public List<cliente> obtenerClientes() {
        return ctrlpersis.getClientes();
    }
    
    //CRUD CUENTA_CORRIENTE
    
    public void crearCc(cuenta_corriente cC1){
        ctrlpersis.crearCc(cC1);  
    }
    
    public void eliminarCc(int id){
        ctrlpersis.eliminarCc(id);
    }
    
    public void eliminarCCPorCabecera(cabecera_remito cabecera) {
        ctrlpersis.eliminarCCPorCabecera(cabecera);
    }
   /* 
    public void eliminarCcporIdClient(int idClient){
        ctrlpersis.eliminarCcPorIdClient(idClient);
    }
    */
    public void modifCc(cuenta_corriente cC1) {
        ctrlpersis.modifCc(cC1);
    }
    
    public cuenta_corriente consultarCcporCabec(cabecera_remito idcabec){
        return ctrlpersis.consultarCcporcabec(idcabec);
    }
    public cuenta_corriente consultarCc(int id){
        return ctrlpersis.consultarCc(id);
    }
    
    public List<cuenta_corriente> consultarCcList(cabecera_remito cab){
        return ctrlpersis.consultarCcList(cab);
    }
    
    public void actualizarImportesCc(int idCc){
       ctrlpersis.actualizarCuentaCorriente(idCc);
       
    }
    
    //CRUD DETALLE
    
    public void crearDetalle(detalle_remito detalle1){
        ctrlpersis.crearDetalle(detalle1);
    }
    
    public void eliminarDetalle(int id){
        ctrlpersis.eliminarDetalle(id);
    }
    
    public cabecera_remito obtenerCabeceraRemitoPorId(int idCabecera) {
        return ctrlpersis.obtenerCabeceraRemitoPorId(idCabecera);
    }
    public void eliminarDetallesPorCabecera(cabecera_remito cabecera) {
        ctrlpersis.eliminarDetallesPorCabecera(cabecera);
    }
    
    public List<detalle_remito> consultarListaDetalles(){
        return ctrlpersis.consultarListaDetalles();
    }

    
    public void eliminarDetallePorIdCabecera(int idCabecera) {
        System.out.println("Intentando eliminar detalles con idCabecera: " + idCabecera); // LOG
        ctrlpersis.eliminarPorIdCabecera(idCabecera);
        System.out.println("Detalle con ID: " + idCabecera + " eliminado correctamente."); // LOG
}
  
    public void eliminarDetPorCliente(int idClient){
        ctrlpersis.eliminarDetPorCliente(idClient);
        
    }
    
    public void modifDetalle(detalle_remito detalle1){
        ctrlpersis.modifDetalle(detalle1);
    }
    
    public detalle_remito verDetalle(int id){
        return ctrlpersis.consultarDetalle(id);
    }
    
    public List<detalle_remito> consultarDetalleList(){
        return ctrlpersis.consultarDetalleList();
    }
    
    public List<detalle_remito> consultarDetalleListCabec(List<Integer> remitosSeleccionados) {
        return ctrlpersis.consultarDetalleListCabec(remitosSeleccionados);
    }
    
    public void actPrecioDetalle(int idRemito, double nuevoprecio, double importe){
        ctrlpersis.actualizarPreciosDetalleRemito(idRemito, nuevoprecio, importe);
    }
    
    public void actRefDetalle(){
     ctrlpersis.actualizarReferenciasPorCodProd();
    }
    
    public void actimportetotal( int cabec){
        ctrlpersis.actualizarImporteTotal(cabec);
    }
    
    //CRUD PAGO
    public void crearPago(pago pago1){
        ctrlpersis.crearPago(pago1);
    }
    
    public void eliminarPago(int id){
        ctrlpersis.eliminarPago(id);
    }
    
    public void modifPago(pago pago1){
        ctrlpersis.modifPago(pago1);
    }
    
    public pago consultarPago(int id){
        return ctrlpersis.consultarPago(id);
    }
    
    public ArrayList<pago> cosultarPagoList(){
        return ctrlpersis.consultarPagoList();
    }
    
    public List<pago> consultarPagoXcabec(List<Integer> pagoIdCabec){
        return ctrlpersis.consultarPagoXcabec(pagoIdCabec);
    }
    
    
    //CRUD PRODUCTO
    public void crearProducto(producto prod1){
        ctrlpersis.crearProducto(prod1);
    }
    
    public void eliminarProducto(int id){
        ctrlpersis.eliminarProducto(id);
    }
    
    public void modifProducto(producto prod1){
        ctrlpersis.modifProducto(prod1);
    }
    
    public producto consultarProductoStr(String codProd){
        return ctrlpersis.buscarProductoPorCodProd(codProd);
    }
    
    public producto consultarProducto(int id){
        return ctrlpersis.buscarProducto(id);
    }
    
    public ArrayList<producto> consultarProductosList(){
        return ctrlpersis.consultarProductosList();
    }
    
    public void guardarProduct(List<producto> productos){
        try {
            ctrlpersis.guardarProduct(productos);
        } catch (Exception ex) {
            Logger.getLogger(controladoraLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void vaciarProd(int idLista){
        ctrlpersis.vaciarProductos(idLista);
    }
    
    //CRUD USUARIO
    public void crearUsuario(usuario user1){
        ctrlpersis.crearUsuario(user1);
    }
    
    public void eliminarUsuario(int id){
        ctrlpersis.eliminarUsuario(id);
    }
    
    public void modifUsuario(usuario user1){
        ctrlpersis.modifUsuario(user1);
    }
    
    public usuario consultarUsuario(int id){
        return ctrlpersis.consultarUsuario(id);
    }
    
    public ArrayList<usuario> consultarUsuariosList(){
        return ctrlpersis.consultarUsuariosList();
    }
    
    public void validarIngreso(String nombUsuario, String contrasenia, HttpServletRequest request) {
        List<usuario> listaUs = ctrlpersis.consultarUsuariosList();

    for (usuario usu : listaUs) {
        if (usu.getNombUsuario().equals(nombUsuario) &&
            usu.getContraseña().equals(contrasenia)) {
            // Guardar datos en los atributos del request
            request.setAttribute("usuarioValido", true);
            request.setAttribute("idUsuario", usu.getIdUsuario());
            return;  // Salimos del método
        }
    }

    // Si no es válido, marcamos que falló la autenticación
    request.setAttribute("usuarioValido", false);
}
    
}
