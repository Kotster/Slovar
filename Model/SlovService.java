package Slov.Model;

import Controller.*;

public class SlovService{

    public SlovService() {
    }
    public void Show(ISlovService service){
        service.Show();
    }
    void Delete(ISlovService service, String key) throws ValidKeyException{
        if(Check.validKey(service,key)){
            service.Delete(key);
        }
        else
        throw new ValidKeyException();
    }
    String Serch(ISlovService service, String key) throws ValidKeyException{
        if (Check.validKey(service,key)) {
            return service.Serch(key);
        }
        throw new  ValidKeyException();
    }
    void Add(ISlovService service, String key, String Value) throws ValidKeyException, Slovar.NotUniqException {
        if (Check.validKey(service,key)) {
                service.Add(key,Value);
        }
        else
            throw new ValidKeyException();
    }
    public class ValidKeyException extends Exception{
        ValidKeyException(){
            super("The key is not in the dictionary");
        }
    }
}