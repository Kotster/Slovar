package Model;

import Controller.ISlovService;

public class SlovService{

    public SlovService() {
    }
    public void Show(ISlovService service){
        service.Show();
    }
    public void Delete(ISlovService service, String key) throws Exception{
        if(Check.validKey(service,key)){
            service.Delete(key);
        }
        else
            throw new Exception("Error");
    }
    public String Serch(ISlovService service, String key) throws Exception{
        if (Check.validKey(service,key)) {
            return service.Serch(key);
        }
        throw new Exception("Error");
    }
    public void Add(ISlovService service, String key, String Value) throws Exception{
        if (Check.validKey(service,key)) {
            service.Add(key,Value);
        }
        else
            throw new Exception("Error");
    }
//    public class ValidKeyException extends Exception{
//        ValidKeyException(){
//            super("The key is not in the dictionary");
//        }
//    }
}
