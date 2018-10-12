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
    public Usuario crearRestaurant(String login, String password, long rut){
        Usuario restaurante = new Restaurant(login, password, rut);
        return restaurante;
    }
    public void facturacion(){

    }


}
