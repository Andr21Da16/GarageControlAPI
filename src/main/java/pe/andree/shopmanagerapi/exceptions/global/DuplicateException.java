package pe.andree.shopmanagerapi.exceptions.global;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
    public DuplicateException(){
        super();
    }
}
