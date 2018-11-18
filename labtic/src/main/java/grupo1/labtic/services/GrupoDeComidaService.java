package grupo1.labtic.services;

import grupo1.labtic.persistence.GrupoDeComidaRepository;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoDeComidaService {
    @Autowired
    private GrupoDeComidaRepository grupoDeComidaRepository;

    public List<GrupoDeComida> getGrupoDeComidaByGrupo(List<String> grupoList) {
        return grupoDeComidaRepository.getGrupoDeComidaByGrupo(grupoList);
    }
}
