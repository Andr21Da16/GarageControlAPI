package pe.andree.shopmanagerapi.exceptions.type;

public class VehicleTypeNotFoundException extends RuntimeException {
    public VehicleTypeNotFoundException(String message) {
        super(message);
    }
    public VehicleTypeNotFoundException(){super();}
}
