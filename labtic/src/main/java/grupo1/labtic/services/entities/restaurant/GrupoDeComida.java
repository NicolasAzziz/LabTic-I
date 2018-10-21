package grupo1.labtic.services.entities.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "GrupoComida")
public class GrupoDeComida {
    @Id
    @GeneratedValue(generator = "grupo_id")
    @GenericGenerator(name = "grupo_id", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "grupoDeComida_Nombre", unique = true)
    private String grupo;

    public GrupoDeComida(String grupo) {
        this.grupo = grupo;
    }

    public GrupoDeComida() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
