package grupo1.labtic.services.exceptions;

public class RestaurantAlreadyExists extends Exception{

    public RestaurantAlreadyExists(String message) {
        super(message);
    }

    public RestaurantAlreadyExists() {
    }
}
