package grupo1.labtic.services;

import grupo1.labtic.persistence.AdminRepository;
import grupo1.labtic.services.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void admin(){
        Admin admin = new Admin("admin@yendo","admin");
        adminRepository.save(admin);
    }
}
