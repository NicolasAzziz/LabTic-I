package grupo1.labtic.services.entities;

import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import grupo1.labtic.services.entities.restaurant.TipoDePago;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "RESTAURANT")
public class Restaurant extends Usuario {

    @Column(name = "NombreRestaurant")
    private String nombreRestaurant;
    @Column( name = "direccion", unique = true)
    protected String direccion;
    @Column( name = "horarioApertura")
    protected String horarioApertura;
    @Column( name = "horarioCierre")
    protected String horarioCierre;
    @Column(name = "barrio")
    protected String barrio;
    @Column(name = "telefono")
    protected String telefono;
    @Column(name = "descripcion")
    protected String descripcion;
    @Column(name = "SitioWeb")
    private String sitioWeb;
    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<GrupoDeComida> grupoDeComidaList;
    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    protected List<TipoDePago> tipoDePagoList;



    public Restaurant(String login, String password, String email, String direccion, String horarioApertura, String horarioCierre, String barrio, String telefono, String descripcion) {
        super(login, password, email);
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.barrio = barrio;
        this.telefono = telefono;
        this.descripcion = descripcion;
        grupoDeComidaList = new ArrayList<>();
        tipoDePagoList = new ArrayList<>();
    }



    public Restaurant(String login, String password, String email) {
        super(login, password, email);
        grupoDeComidaList = new ArrayList<>();
        tipoDePagoList = new ArrayList<>();
    }

    public Restaurant(String login, String password) {
        super(login, password);
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
        return Objects.equals(nombreRestaurant, that.nombreRestaurant) &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(horarioApertura, that.horarioApertura) &&
                Objects.equals(horarioCierre, that.horarioCierre) &&
                Objects.equals(barrio, that.barrio) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(grupoDeComidaList, that.grupoDeComidaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direccion, horarioApertura, horarioCierre, barrio, telefono, descripcion);
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

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<GrupoDeComida> getGrupoDeComidaList() {
        return grupoDeComidaList;
    }

    public void setGrupoDeComidaList(List<GrupoDeComida> grupoDeComidaList) {
        this.grupoDeComidaList.addAll( grupoDeComidaList );
    }
    public void addGrupoDeComida(GrupoDeComida grupoDeComida){
        this.grupoDeComidaList.add( grupoDeComida );
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
}
