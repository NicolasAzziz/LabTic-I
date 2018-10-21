package grupo1.labtic.services.entities.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "TipoDePago")
public class TipoDePago {
    @Id
    @GeneratedValue(generator = "tipoDePago_id")
    @GenericGenerator(name = "tipoDePago_id", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "Nombre_TipoDePago", unique = true)
    private String nombre;

    public TipoDePago() {
    }

    public TipoDePago(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoDePago that = (TipoDePago) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
