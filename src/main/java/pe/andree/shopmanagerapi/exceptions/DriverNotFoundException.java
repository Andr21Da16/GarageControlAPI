package pe.andree.shopmanagerapi.exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) {
        super(message);
    }
    public DriverNotFoundException(){
        super();
    }
}
