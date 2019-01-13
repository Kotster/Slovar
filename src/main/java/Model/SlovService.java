package Model;

import Controller.ISlovService;

public class SlovService{

    public SlovService() {
    }
    public void Show(ISlovService service){
        service.Show();
    }
    public void Delete(ISlovService service, String key) throws ValidKeyException{
        if(Check.validKey(service,key)){
            service.Delete(key);
        }
        else
            throw new ValidKeyException();
    }
    public String Serch(ISlovService service, String key) throws ValidKeyException{
        if (Check.validKey(service,key)) {
            return service.Serch(key);
        }
        throw new  ValidKeyException();
    }
    public void Add(ISlovService service, String key, String Value) throws ValidKeyException, Slovar.NotUniqException {
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
