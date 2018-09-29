package grupo1.labtic.services.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

@Entity(name = "USUARIO")
@Inheritance( strategy = InheritanceType.JOINED)
public abstract class Usuario {


    @Id
    @GeneratedValue( generator = "usuario_id")
    @GenericGenerator( name = "usuario_id", strategy = "increment")
    @Column( name = "id", updatable = false, nullable = false)
    private long id;

    @Column( name = "login", unique = true)
    private String login;

    @Column( name = "password")
    private String password;

    @Column(name = "email")
    @Email
    private String email;

    public Usuario(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Usuario() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id &&
                Objects.equals(login, usuario.login) &&
                Objects.equals(password, usuario.password) &&
                Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
