package grupo1.labtic.services;

import grupo1.labtic.persistence.AdminRepository;
import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.services.entities.Admin;
import grupo1.labtic.services.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ReservaService reservaService;

    public void admin(){
        Admin admin = new Admin("admin@yendo","admin");
        boolean exist = adminRepository.existsByEmail("admin@yendo");
        if(!exist)
            adminRepository.save(admin);
            reservaService.setAdmin(admin);
    }

    public void setNuevoImporte(Admin admin, Long nuevoImporte){

        Admin admin1 = adminRepository.getByEmail(admin.getEmail());
        admin1.setNuevoImporte(nuevoImporte);
        adminRepository.save(admin1);

    }

}
