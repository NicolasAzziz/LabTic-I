package grupo1.labtic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue( generator = "usuarios_ids")
    @GenericGenerator( name = "usuarios_ids", strategy = "increment")
    private long id;

    @Column( name = "login", unique = true)
    private int login;

    @Column( name = "password")
    private int password;

    public Usuario(int login, int password) {
        this.login = login;
        this.password = password;
    }

    public Usuario() {
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
