package grupo1.labtic.services.entities.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "GrupoComida")
public class GrupoDeComida {

    @Id
    @Column(name = "grupoDeComida_Nombre", unique = true)
    private String grupo;

    public GrupoDeComida(String grupo) {
        this.grupo = grupo;
    }

    public GrupoDeComida() {
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoDeComida that = (GrupoDeComida) o;
        return Objects.equals(grupo, that.grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupo);
    }
}
