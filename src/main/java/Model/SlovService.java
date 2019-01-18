package Model;

import Controller.ISlovService;
import Controller.SlovarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class SlovService{

    public SlovService() {
    }
    public List<String> Show(ISlovService service){
        return service.All();
    }
    public void Delete(SlovarModel model,ISlovService service, String key) throws Exception{
        if(Check.validKey(model,key)){
            service.Delete(key);
        }
        else
            throw new Exception("Error");
    }
    public List<String> Serch(SlovarModel model, ISlovService service, String key) throws Exception{
        if (Check.validKey(model,key)) {
            return service.Serch(key);
        }
        throw new Exception("Error");
    }
    public void Add(SlovarModel model, ISlovService service, String key, String Value) throws Exception{
        if (Check.validKey(model,key)) {
            service.Add(key,Value);
        }
        else
            throw new Exception("Error");
    }
    public void Update(SlovarModel model, ISlovService service, String key, String Value) throws Exception{
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
