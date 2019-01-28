package system.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.Check;
import system.dao.ISlovService;
import system.model.Model;

import java.util.List;

@Service
@Qualifier("service")
public class ModelService {

    public ModelService() {
    }
    public List<String> Show(ISlovService service, Model model){
        return service.all(model);
    }

    public void Delete(Model model, ISlovService service) throws Exception{
        if(Check.validKey(model)){
            service.delete(model);
        }
        else
            throw new Exception("Not valid key");
    }

    public List<String> Serch(Model model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            return service.serch(model);
        }
        throw new Exception("Not valid key");
    }

    public void Add(Model model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
            service.add(model);
        }
        else
            throw new Exception("Not valid key");
    }

    public void Update(Model model, ISlovService service) throws Exception{
        if (Check.validKey(model)) {
        service.update(model);
        }
        else
        throw new Exception("Not valid key");
    }
}
