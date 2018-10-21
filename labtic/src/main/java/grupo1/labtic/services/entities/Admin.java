package grupo1.labtic.services.entities;

import javax.persistence.Entity;

@Entity(name = "ADMIN")
public class Admin extends Usuario {

    public Admin(String email, String password) {
        super(email, password);
    }

    public Admin() {
        super();

    }

    public Restaurant crearRestaurant(String email, String password, long rut) {
        Restaurant restaurante = new Restaurant(email, password, rut);
        return restaurante;
    }

    public void facturacion() {

    }


}
