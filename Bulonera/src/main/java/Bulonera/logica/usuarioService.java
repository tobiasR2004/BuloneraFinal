/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobi2
 */
public class usuarioService {
    private EntityManager entityManager;
   
    controladoraLogica ctrl = new controladoraLogica();

    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");  // Asegúrate de que el nombre coincida con el de tu archivo persistence.xml
        this.entityManager = emf.createEntityManager();
        Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM usuario u").getSingleResult();
        
        if (count == 0) {
            usuario us1 = new usuario();
            us1.setNombUsuario("admin");
            us1.setContraseña("admin");
            ctrl.crearUsuario(us1);
        }
    }
}
