package pe.andree.shopmanagerapi.exceptions;

public class DuplicateVehicleTypeException extends RuntimeException {
    public DuplicateVehicleTypeException(String message) {
        super(message);
    }
    public DuplicateVehicleTypeException(){
        super();
    }
}
