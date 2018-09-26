package grupo1.labtic.services.entities.restaurant;

import grupo1.labtic.services.entities.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "RESTAURANT")
public class Restaurant extends Usuario {

    @Column( name = "direccion", unique = true)
    protected String direccion;
    @Column( name = "horarioApertura")
    protected int horarioApertura;
    @Column( name = "horarioCierre")
    protected int horarioCierre;
    @Column(name = "barrio")
    protected String barrio;
    @Column(name = "telefono")
    protected String telefono;
    @Column(name = "formaDePago")
    protected String formasDePago;
    @Column(name = "cocinas")
    protected String cocinas;
    @Column(name = "descripcion")
    protected String descripcion;
    @OneToMany(mappedBy = "restaurante")
    protected List<Mesa> mesa;


    public Restaurant(String login, String password, String email, String direccion, int horarioApertura, int horarioCierre, String barrio, String telefono, String formasDePago, String cocinas, String descripcion) {
        super(login, password, email);
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.barrio = barrio;
        this.telefono = telefono;
        this.formasDePago = formasDePago;
        this.cocinas = cocinas;
        this.descripcion = descripcion;
        this.mesa = new ArrayList<>();
    }

    public Restaurant(String login, String password, String email) {
        super(login, password, email);
    }

    public Restaurant(String login, String password) {
        super(login, password);
    }


    public Restaurant() {
        super();
        this.mesa = new ArrayList<Mesa>();
    }

    public String getDireccion() {
        return direccion;
    }

    public int getHorarioApertura() {
        return horarioApertura;
    }

    public int getHorarioCierre() {
        return horarioCierre;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFormasDePago() {
        return formasDePago;
    }

    public String getCocinas() {
        return cocinas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Mesa> getMesa() {
        return mesa;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorarioApertura(int horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public void setHorarioCierre(int horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFormasDePago(String formasDePago) {
        this.formasDePago = formasDePago;
    }

    public void setCocinas(String cocinas) {
        this.cocinas = cocinas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addMesa(Mesa mesa) {
        this.mesa.add(mesa);
    }
}
