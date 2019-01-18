package Model;

import Controller.ISlovService;
import Controller.SlovarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class SlovService{
    
    @Autowired
    @Qualifier()
    ISlovService service;

    public SlovService() {
    }
    public List<String> Show(){
        return service.All();
    }
    public void Delete(SlovarModel model, String key) throws Exception{
        if(Check.validKey(model,key)){
            service.Delete(key);
        }
        else
            throw new Exception("Error");
    }
    public List<String> Serch(SlovarModel model, String key) throws Exception{
        if (Check.validKey(model,key)) {
            return service.Serch(key);
        }
        throw new Exception("Error");
    }
    public void Add(SlovarModel model, String key, String Value) throws Exception{
        if (Check.validKey(model,key)) {
            service.Add(key,Value);
        }
        else
            throw new Exception("Error");
    }
    public void Update(SlovarModel model, String key, String Value) throws Exception{
        if (Check.validKey(model,key)) {
        service.Update(key,Value);
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
