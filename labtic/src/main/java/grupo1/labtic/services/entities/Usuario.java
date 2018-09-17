package grupo1.labtic.services.entities;

public class Usuario {

    private int login;
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
