package grupo1.labtic.services.entities;

import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import grupo1.labtic.services.entities.restaurant.TipoDePago;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "RESTAURANT")
public class Restaurant extends Usuario {

    @Column(name = "direccion", unique = true)
    protected String direccion;
    @Column(name = "horarioApertura")
    protected String horarioApertura;
    @Column(name = "horarioCierre")
    protected String horarioCierre;
    @Column(name = "barrio")
    protected String barrio;
    @Column(name = "telefono")
    protected String telefono;
    @Column(name = "descripcion")
    protected String descripcion;
    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<GrupoDeComida> grupoDeComidaList;
    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    protected List<TipoDePago> tipoDePagoList;
    @Column(name = "RUT", unique = true)
    private long rut;
    @Column(name = "NombreRestaurant")
    private String nombreRestaurant;
    @Column(name = "SitioWeb")
    private String sitioWeb;



    public Restaurant(String email, String password, long rut, String direccion, String horarioApertura, String horarioCierre, String barrio, String telefono, String descripcion) {
        super(email, password);
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.barrio = barrio;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.rut = rut;
        grupoDeComidaList = new ArrayList<>();
        tipoDePagoList = new ArrayList<>();
    }


    public Restaurant(String email, String password, long rut) {
        super(email, password);
        this.rut = rut;
        grupoDeComidaList = new ArrayList<>();
        tipoDePagoList = new ArrayList<>();
    }

    public Restaurant() {
        super();
        grupoDeComidaList = new ArrayList<>();
        tipoDePagoList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return rut == that.rut &&
                Objects.equals(nombreRestaurant, that.nombreRestaurant) &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(horarioApertura, that.horarioApertura) &&
                Objects.equals(horarioCierre, that.horarioCierre) &&
                Objects.equals(barrio, that.barrio) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(sitioWeb, that.sitioWeb) &&
                Objects.equals(grupoDeComidaList, that.grupoDeComidaList) &&
                Objects.equals(tipoDePagoList, that.tipoDePagoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rut, nombreRestaurant, direccion, horarioApertura, horarioCierre, barrio, telefono, descripcion, sitioWeb, grupoDeComidaList, tipoDePagoList);
    }

    public String getNombreRestaurant() {
        return nombreRestaurant;
    }

    public void setNombreRestaurant(String nombreRestaurant) {
        this.nombreRestaurant = nombreRestaurant;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<GrupoDeComida> getGrupoDeComidaList() {
        return grupoDeComidaList;
    }

    public void setGrupoDeComidaList(List<GrupoDeComida> grupoDeComidaList) {
        this.grupoDeComidaList.addAll(grupoDeComidaList);
    }

    public void addGrupoDeComida(GrupoDeComida grupoDeComida) {
        this.grupoDeComidaList.add(grupoDeComida);
    }

    public List<TipoDePago> getTipoDePagoList() {
        return tipoDePagoList;
    }

    public void setTipoDePagoList(List<TipoDePago> tipoDePagoList) {
        this.tipoDePagoList = tipoDePagoList;
    }

    public void addTipoDePago(TipoDePago tipoDePagoList) {
        this.tipoDePagoList.add(tipoDePagoList);
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public long getRut() {
        return rut;
    }

    public void setRut(long rut) {
        this.rut = rut;
    }
}
