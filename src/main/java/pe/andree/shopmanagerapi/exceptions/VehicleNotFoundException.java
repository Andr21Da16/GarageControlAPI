package pe.andree.shopmanagerapi.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }

}
