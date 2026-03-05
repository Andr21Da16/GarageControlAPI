package pe.andree.shopmanagerapi.exceptions.company;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException (String message){
        super(message);
    }

    public CompanyNotFoundException(){
        super();
    }
}
