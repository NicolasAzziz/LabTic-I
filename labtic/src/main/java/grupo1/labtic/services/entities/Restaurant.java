package grupo1.labtic.services.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RESTAURANTES")
public class Restaurant extends Usuario {

    @Id
    @GeneratedValue(generator = "restaurantes_ids")
    @GenericGenerator( name = "restaurantes_ids", strategy = "increment")
    private Long id;

    @Column( name = "nombre")
    private String nombre;

    @Column( name = "direccion", unique = true)
    private String direccion;
    @Column( name = "horarioCierre")
    private int horarioCierre;
    @Column( name = "horarioApertura")
    private int horarioApertura;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "formaDePago")
    private String formasDePago;
    @Column(name = "cocinas")
    private String cocinas;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "mesas")
    private List<Mesa> mesas;

    public Restaurant(int login, int password, String nombre, String direccion, int horarioCierre,
                      int horarioApertura, String barrio, String telefono, String formasDePago, String cocinas,
                      String descripcion, List<Mesa> mesas) {
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
        this.mesas = mesas;
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

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
