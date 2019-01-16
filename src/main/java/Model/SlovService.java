package Model;

import Controller.SlovarModel;

import java.util.List;

public class SlovService{

    public SlovService() {
    }
    public void Show(SlovarModel service){
        //service.getControl().Show();
    }
    public void Delete(SlovarModel service, String key) throws Exception{
        if(Check.validKey(service,key)){
            service.getControl().Delete(key);
        }
        else
            throw new Exception("Error");
    }
    public List<String> Serch(SlovarModel service, String key) throws Exception{
        if (Check.validKey(service,key)) {
            return service.getControl().Serch(key);
        }
        throw new Exception("Error");
    }
    public void Add(SlovarModel service, String key, String Value) throws Exception{
        if (Check.validKey(service,key)) {
            service.getControl().Add(key,Value);
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
