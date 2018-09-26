//package grupo1.labtic.bdd;
//
//import grupo1.labtic.services.entities.Usuario;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.List;
//
//public class BDDTest {
//
//    private static EntityManager manager;
//
//    public static EntityManagerFactory emf;
//
//    public static void main(String[] args) {
//        emf = Persistence.createEntityManagerFactory("RestaurantRepository");
//        manager = emf.createEntityManager();
//
//        @SuppressWarnings("unchecked")
//        List<Usuario> usuarios = manager.createQuery("SELECT * FROM Usarios").getResultList();
//        System.out.println(usuarios.size() + " usuarios.");
//
//    }
//
//}