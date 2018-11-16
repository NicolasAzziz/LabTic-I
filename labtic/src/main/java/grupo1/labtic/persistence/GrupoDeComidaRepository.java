package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupoDeComidaRepository extends CrudRepository<GrupoDeComida, String> {
    GrupoDeComida getGrupoDeComidaByGrupo(String nombreGrupo);


    boolean existsByGrupo(String nombre);

    //    List<GrupoDeComida> getGrupoDeComidasByGrupo(List<String> nombreGrupo);
    @Query("select o from GrupoComida o where o.grupo in :grupoDeComida_Nombre")
    List<GrupoDeComida> getGrupoDeComidaByGrupo(@Param("grupoDeComida_Nombre") List<String> nombreGrupo);
}
