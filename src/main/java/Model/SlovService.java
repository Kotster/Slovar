package Model;

import Controller.ISlovService;
import Controller.SlovarModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("service")
public class SlovService{

    public SlovService() {
    }
    public List<String> Show(ISlovService service){
        return service.all();
    }
    public void Delete(SlovarModel model,ISlovService service, String key) throws Exception{
        if(Check.validKey(model,key)){
            service.delete(key);
        }
        else
            throw new Exception("Error");
    }
    public List<String> Serch(SlovarModel model, ISlovService service, String key) throws Exception{
        if (Check.validKey(model,key)) {
            return service.serch(key);
        }
        throw new Exception("Error");
    }
    public void Add(SlovarModel model, ISlovService service, String key, String Value) throws Exception{
        if (Check.validKey(model,key)) {
            service.add(key,Value);
        }
        else
            throw new Exception("Error");
    }
    public void Update(SlovarModel model, ISlovService service, String key, String Value) throws Exception{
        if (Check.validKey(model,key)) {
        service.update(key,Value);
        }
        else
        throw new Exception("Error");
    }
}
