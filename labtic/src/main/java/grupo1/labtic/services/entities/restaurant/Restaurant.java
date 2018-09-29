package grupo1.labtic.services.entities.restaurant;

import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.comida.Cocina;
import grupo1.labtic.services.entities.restaurant.comida.Hamburgesas;
import grupo1.labtic.services.entities.restaurant.pago.MetodoDePago;
import grupo1.labtic.services.entities.restaurant.pago.TarjetaCredito;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "RESTAURANT")
public class Restaurant extends Usuario {

    @Column(name = "NombreRestaurant")
    private String nombreRestaurant;
    @Column( name = "direccion", unique = true)
    protected String direccion;
    @Column( name = "horarioApertura")
    protected String horarioApertura;
    @Column( name = "horarioCierre")
    protected String horarioCierre;
    @Column(name = "barrio")
    protected String barrio;
    @Column(name = "telefono")
    protected String telefono;
    @Column(name = "descripcion")
    protected String descripcion;

 /*   @OneToMany(cascade = CascadeType.ALL)
    protected List<Mesa> mesas;*/


    public Restaurant(String login, String password, String email, String direccion, String horarioApertura, String horarioCierre, String barrio, String telefono, String descripcion) {
        super(login, password, email);
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.barrio = barrio;
        this.telefono = telefono;
        this.descripcion = descripcion;
//        this.mesas = new ArrayList<>();
    }

    public Restaurant(String login, String password, String email) {
        super(login, password, email);
    }

    public Restaurant(String login, String password) {
        super(login, password);
    }


    public Restaurant() {
        super();
//        this.mesas = new ArrayList<Mesa>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return horarioApertura == that.horarioApertura &&
                horarioCierre == that.horarioCierre &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(barrio, that.barrio) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direccion, horarioApertura, horarioCierre, barrio, telefono, descripcion);
    }

    public String getNombreRestaurant() {
        return nombreRestaurant;
    }

    public void setNombreRestaurant(String nombreRestaurant) {
        this.nombreRestaurant = nombreRestaurant;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

//    public List<Mesa> getMesa() {
//        return mesas;
//    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public void addMesa(Mesa mesa) {
//
//    }
}
