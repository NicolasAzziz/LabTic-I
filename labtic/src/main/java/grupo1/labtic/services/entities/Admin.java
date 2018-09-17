package grupo1.labtic.services.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Admin extends Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    public Admin(int login, int password) {
        super(login, password);
    }


}
