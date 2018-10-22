package grupo1.labtic.services.entities;

import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "CLIENTE")
public class Cliente extends Usuario {

    private String nombre;

    public Cliente(String nombre, String email, String password) {
        super(email, password);
        this.nombre = nombre;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombre);
    }
}
