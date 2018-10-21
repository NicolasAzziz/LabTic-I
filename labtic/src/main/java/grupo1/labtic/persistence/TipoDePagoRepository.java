package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.restaurant.TipoDePago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDePagoRepository extends CrudRepository<TipoDePago, String> {
    TipoDePago getByNombre(String nombreTipoDePago);

    boolean existsByNombre(String nombre);

    //    List<GrupoDeComida> getGrupoDeComidasByGrupo(List<String> nombreGrupo);
    @Query("select o from TipoDePago o where o.nombre in :nombreGrupo")
    List<TipoDePago> getListByNombre(@Param("nombreGrupo") List<String> nombreGrupo);
}
