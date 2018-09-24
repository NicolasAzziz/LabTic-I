package grupo1.labtic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table( name = "USUARIOS")

public abstract class Usuario {

    @Id
    @GeneratedValue( generator = "usuario_id")
    @GenericGenerator( name = "usuario_id", strategy = "increment")
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
