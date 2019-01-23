package Model;

import Controller.ISlovService;
import Controller.SlovarModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("service")
public class SlovService{

    public SlovService() {
    }
//    @Transactional
    public List<String> Show(ISlovService service, SlovarModel model){
        return service.all(model);
    }
//    @Transactional
    public void Delete(SlovarModel model,ISlovService service) throws Exception{
        if(Check.validKey(model)){
            service.delete(model);
        }
        else
            throw new Exception("Error");
    }
//    @Transactional
    public List<String> Serch(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            return service.serch(model);
        }
        throw new Exception("Error");
    }
//    @Transactional
    public void Add(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            service.add(model);
        }
        else
            throw new Exception("Error");
    }
//    @Transactional
    public void Update(SlovarModel model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
        service.update(model);
        }
        else
        throw new Exception("Error");
    }
}
