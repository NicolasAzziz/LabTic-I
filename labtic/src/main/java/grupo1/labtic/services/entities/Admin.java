package grupo1.labtic.services.entities;

import javax.persistence.Entity;

@Entity (name = "ADMIN")
public class Admin extends Usuario {

    public Admin(String login, String password, String email) {
        super(login, password, email);
    }

    public Admin(String login, String password) {
        super(login, password);
    }

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
