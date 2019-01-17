package Model;

import Controller.ISlovService;

import java.util.List;

public class SlovService{

    public SlovService() {
    }
    public List<String> Show(ISlovService service){
        return service.All();
    }
    public void Delete(ISlovService service, String key) throws Exception{
        //if(Check.validKey(service,key)){
            service.Delete(key);
        //}
        //else
            //throw new Exception("Error");
    }
    public List<String> Serch(ISlovService service, String key) throws Exception{
        //if (Check.validKey(service,key)) {
            return service.Serch(key);
        //}
        //throw new Exception("Error");
    }
    public void Add(ISlovService service, String key, String Value) throws Exception{
        //if (Check.validKey(service,key)) {
            service.Add(key,Value);
        //}
        //else
            //throw new Exception("Error");
    }
    public void Update(ISlovService service, String key, String Value) throws Exception{
        //if (Check.validKey(service,key)) {
        service.Update(key,Value);
        //}
        //else
        //throw new Exception("Error");
    }
//    public class ValidKeyException extends Exception{
//        ValidKeyException(){
//            super("The key is not in the dictionary");
//        }
//    }
}
