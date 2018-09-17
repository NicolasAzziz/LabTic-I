package grupo1.labtic.services.entities;

public class Mesa {

    private int numeroReferencia;
    private int cantLugares;

    public Mesa(int numeroReferencia, int cantLugares) {
        this.numeroReferencia = numeroReferencia;
        this.cantLugares = cantLugares;
    }

    public Mesa() {
    }

    public int getNumeroReferencia() {
        return numeroReferencia;
    }

    public int getCantLugares() {
        return cantLugares;
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public void setCantLugares(int cantLugares) {
        this.cantLugares = cantLugares;
    }
}
