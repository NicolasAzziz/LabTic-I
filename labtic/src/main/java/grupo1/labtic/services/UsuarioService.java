package grupo1.labtic.services;

import grupo1.labtic.services.exceptions.EmailInvalido;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public boolean isValidEmailAddress(String email) throws EmailInvalido {
        boolean be = false;
        EmailValidator emailAddr = new EmailValidator();
        if(emailAddr.isValid(email,null)){
            be= true;
        }else{
            be = false;
            throw new EmailInvalido("El email ingresado no es válido.");
        }
        return be;
    }
}
