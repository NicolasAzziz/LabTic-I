package grupo1.labtic.services.entities.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Barrio {
    @Id
    @Column(name = "barrio")
    private String barrio;

    public Barrio(String barrio) {
        this.barrio = barrio;
    }

    public Barrio() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrio barrio1 = (Barrio) o;
        return Objects.equals(barrio, barrio1.barrio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barrio);
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
