package grupo1.labtic.services.entities;

import grupo1.labtic.persistence.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "ADMIN")
public class Admin extends Usuario {

    //  DEFAULT: SE COBRA $1 P/RESERVA
    @Column(name = "importeActual")
    private Long importeActual;


    public Admin(String email, String password) {

        super(email, password);
        this.importeActual = 0L;

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

    public void setNuevoImporte(Long nuevoImporte){
        this.importeActual = nuevoImporte;
    }
    public Long getImporteActual(){
        return importeActual;
    }


}
