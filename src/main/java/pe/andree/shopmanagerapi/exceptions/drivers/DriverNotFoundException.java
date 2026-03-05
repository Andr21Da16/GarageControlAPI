package pe.andree.shopmanagerapi.exceptions.drivers;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) {
        super(message);
    }
    public DriverNotFoundException(){
        super();
    }
}
