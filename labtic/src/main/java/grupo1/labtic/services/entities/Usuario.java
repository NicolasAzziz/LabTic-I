package grupo1.labtic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue( generator = "usuario_id")
    @GenericGenerator( name = "usuario_id", strategy = "increment")
    private long id;

    //@Column
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurante_id")
    private Restaurant restaurante;

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
