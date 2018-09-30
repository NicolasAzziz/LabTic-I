package grupo1.labtic.services.entities.restaurant.comida;

import grupo1.labtic.services.entities.restaurant.Restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity (name= "COMIDA")
public class Comida extends Restaurant {
    @Column(name = "sushi")
    private boolean sushi;
    @Column(name = "hamburguesas")
    private boolean hamburguesas;
    @Column(name = "ensaladas")
    private boolean ensaladas;
    @Column(name = "cafeteria")
    private boolean cafeteria;
    @Column(name = "parrilla")
    private boolean parrilla;
    @Column(name = "celiacos")
    private boolean celiacos;
    @Column(name = "chivitos")
    private boolean chivitos;
    @Column(name = "comida china")
    private boolean comidaChina;
    @Column(name = "comida mexicana")
    private boolean comidaMexicana;
    @Column(name = "comida vegetariana")
    private boolean comidaVegetariana;
    @Column(name = "comida vegana")
    private boolean comidaVegana;
    @Column(name = "milanesas")
    private boolean milanesas;
    @Column(name = "pescado y mariscos")
    private boolean pescadoMariscos;
    @Column(name = "pizza")
    private boolean pizza;
    @Column(name = "sandwiches")
    private boolean sandwiches;
    @Column(name = "tartas")
    private boolean tartas;
    @Column(name = "wraps")
    private boolean wraps;
    @Column(name = "wok")
    private boolean wok;

    public Comida(String login, boolean sushi, boolean hamburguesas, boolean ensaladas, boolean cafeteria, boolean parrilla,
                  boolean celiacos, boolean chivitos, boolean comidaChina, boolean comidaMexicana, boolean comidaVegetariana,
                  boolean comidaVegana, boolean milanesas, boolean pescadoMariscos, boolean pizza, boolean sandwiches, boolean tartas,
                  boolean wraps, boolean wok) {
        super(login);
        this.sushi = sushi;
        this.hamburguesas = hamburguesas;
        this.ensaladas = ensaladas;
        this.cafeteria = cafeteria;
        this.parrilla = parrilla;
        this.celiacos = celiacos;
        this.chivitos = chivitos;
        this.comidaChina = comidaChina;
        this.comidaMexicana = comidaMexicana;
        this.comidaVegetariana = comidaVegetariana;
        this.comidaVegana = comidaVegana;
        this.milanesas = milanesas;
        this.pescadoMariscos = pescadoMariscos;
        this.pizza = pizza;
        this.sandwiches = sandwiches;
        this.tartas = tartas;
        this.wraps = wraps;
        this.wok = wok;
    }

    public boolean isSushi() {
        return sushi;
    }

    public void setSushi(boolean sushi) {
        this.sushi = sushi;
    }

    public boolean isHamburguesas() {
        return hamburguesas;
    }

    public void setHamburguesas(boolean hamburguesas) {
        this.hamburguesas = hamburguesas;
    }

    public boolean isEnsaladas() {
        return ensaladas;
    }

    public void setEnsaladas(boolean ensaladas) {
        this.ensaladas = ensaladas;
    }

    public boolean isCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(boolean cafeteria) {
        this.cafeteria = cafeteria;
    }

    public boolean isParrilla() {
        return parrilla;
    }

    public void setParrilla(boolean parrilla) {
        this.parrilla = parrilla;
    }

    public boolean isCeliacos() {
        return celiacos;
    }

    public void setCeliacos(boolean celiacos) {
        this.celiacos = celiacos;
    }

    public boolean isChivitos() {
        return chivitos;
    }

    public void setChivitos(boolean chivitos) {
        this.chivitos = chivitos;
    }

    public boolean isComidaChina() {
        return comidaChina;
    }

    public void setComidaChina(boolean comidaChina) {
        this.comidaChina = comidaChina;
    }

    public boolean isComidaMexicana() {
        return comidaMexicana;
    }

    public void setComidaMexicana(boolean comidaMexicana) {
        this.comidaMexicana = comidaMexicana;
    }

    public boolean isComidaVegetariana() {
        return comidaVegetariana;
    }

    public void setComidaVegetariana(boolean comidaVegetariana) {
        this.comidaVegetariana = comidaVegetariana;
    }

    public boolean isComidaVegana() {
        return comidaVegana;
    }

    public void setComidaVegana(boolean comidaVegana) {
        this.comidaVegana = comidaVegana;
    }

    public boolean isMilanesas() {
        return milanesas;
    }

    public void setMilanesas(boolean milanesas) {
        this.milanesas = milanesas;
    }

    public boolean isPescadoMariscos() {
        return pescadoMariscos;
    }

    public void setPescadoMariscos(boolean pescadoMariscos) {
        this.pescadoMariscos = pescadoMariscos;
    }

    public boolean isPizza() {
        return pizza;
    }

    public void setPizza(boolean pizza) {
        this.pizza = pizza;
    }

    public boolean isSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(boolean sandwiches) {
        this.sandwiches = sandwiches;
    }

    public boolean isTartas() {
        return tartas;
    }

    public void setTartas(boolean tartas) {
        this.tartas = tartas;
    }

    public boolean isWraps() {
        return wraps;
    }

    public void setWraps(boolean wraps) {
        this.wraps = wraps;
    }

    public boolean isWok() {
        return wok;
    }

    public void setWok(boolean wok) {
        this.wok = wok;
    }
}
