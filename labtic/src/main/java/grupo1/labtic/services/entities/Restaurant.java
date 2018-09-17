package grupo1.labtic.services.entities;

import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;


@Entity
public class Restaurant extends Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String direccion;
    private int horarioCierre;
    private int horarioApertura;
    private String barrio;
    private String telefono;
    private String formasDePago;
    private String cocinas;
    private String descripcion;
    private List<Mesa> mesa;

    public Restaurant(int login, int password, String nombre, String direccion, int horarioCierre, int horarioApertura, String barrio, String telefono, String formasDePago, String cocinas, String descripcion, List<Mesa> mesa) {
        super(login, password);
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioCierre = horarioCierre;
        this.horarioApertura = horarioApertura;
        this.barrio = barrio;
        this.telefono = telefono;
        this.formasDePago = formasDePago;
        this.cocinas = cocinas;
        this.descripcion = descripcion;
        this.mesa = mesa;
    }

    public Restaurant() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(int horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public int getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(int horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFormasDePago() {
        return formasDePago;
    }

    public void setFormasDePago(String formasDePago) {
        this.formasDePago = formasDePago;
    }

    public String getCocinas() {
        return cocinas;
    }

    public void setCocinas(String cocinas) {
        this.cocinas = cocinas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Mesa> getMesa() {
        return mesa;
    }

    public void setMesa(List<Mesa> mesa) {
        this.mesa = mesa;
    }
}
