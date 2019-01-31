package system.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.Check;
import system.dao.ISlovService;
import system.model.ModelDictionary;

import java.util.List;

@Service
@Qualifier("service")
public class ModelService {

    public ModelService() {
    }
    public List<ModelDictionary> Show(ISlovService service, ModelDictionary model){

        return service.all(model);
    }

    public void Delete(ModelDictionary model, ISlovService service) throws Exception{
        //if(Check.validKey(model)){
            service.delete(model);
        //}
        //else
            //throw new Exception("Not valid key");
    }

    public List<ModelDictionary> Serch(ModelDictionary model, ISlovService service) throws Exception{
        //if (Check.validKey(model)) {
            return service.serch(model);
        //}
        //throw new Exception("Not valid key");
    }

    public void Add(ModelDictionary model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            service.add(model);
        }
        else
            throw new Exception("Not valid key");
    }

    public void Update(ModelDictionary model, ISlovService service) throws Exception{
        //if (Check.validKey(model)) {
        service.update(model);
        //}
        //else
        //throw new Exception("Not valid key");
    }
}
