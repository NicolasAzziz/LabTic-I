package grupo1.labtic.services.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity (name = "admin")
//@Table (name = "admins")
public class Admin extends Usuario {


    public Admin() {
        super();

    }
    public Usuario crearRestaurant(String nombre, String password, String email){
        Usuario restaurante = new Restaurant(nombre, password, email);
        return restaurante;
    }
    public Usuario crearRestaurant(String nombre, String password){
        Usuario restaurante = new Restaurant(nombre, password);
        return restaurante;
    }
    public void facturacion(){

    }
}
