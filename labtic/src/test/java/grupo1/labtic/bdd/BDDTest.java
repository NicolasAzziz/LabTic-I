package grupo1.labtic.bdd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import grupo1.labtic.services.entities.Usuario;

public class BDDTest {

    private static EntityManager manager;

    public static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("RestaurantRepository");
        manager = emf.createEntityManager();

        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = manager.createQuery("SELECT * FROM Usarios").getResultList();
        System.out.println(usuarios.size() + " usuarios.");

    }

}