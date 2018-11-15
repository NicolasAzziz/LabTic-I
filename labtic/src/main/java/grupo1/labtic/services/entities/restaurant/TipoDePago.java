package grupo1.labtic.services.entities.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "TipoDePago")
public class TipoDePago {

    @Id
    @Column(name = "nombre_TipoDePago", unique = true)
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
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
