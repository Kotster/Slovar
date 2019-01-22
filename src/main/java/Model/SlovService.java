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

    public List<String> Show(ISlovService service, SlovarModel model){
        return service.all(model);
    }

    public void Delete(SlovarModel model,ISlovService service) throws Exception{
        if(Check.validKey(model)){
            service.delete(model);
        }
        else
            throw new Exception("Error");
    }

    public List<String> Serch(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            return service.serch(model);
        }
        throw new Exception("Error");
    }

    public void Add(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            service.add(model);
        }
        else
            throw new Exception("Error");
    }

    public void Update(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
        service.update(model);
        }
        else
        throw new Exception("Error");
    }
}
