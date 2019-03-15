package system.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.dao.ISlovService;
import system.model.ModelDictionary;
import system.service.ModelService;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("rest")
public class REST {

    @Autowired
    ModelService modelService;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @Autowired
    @Qualifier("dictionary1")
    ModelDictionary model1;

    @Autowired
    @Qualifier("dictionary2")
    ModelDictionary model2;

    @RequestMapping(value = "/postsearch", method=RequestMethod.GET)
    public ModelDictionary search(String key){
        List<ModelDictionary> list=new ArrayList<>();
        try {
            ModelDictionary modelDictionary=new ModelDictionary();
            modelDictionary.setKey(key);
            list = modelService.Serch(modelDictionary,service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    @RequestMapping(value = "/getlistdict", method = RequestMethod.GET)
    public List<String> listDict() {

        return Arrays.asList(model1.getName(),model2.getName());
    }
    @RequestMapping(value = "/postadd", method=RequestMethod.PUT)
    public String add(@RequestBody String string){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            ModelDictionary component=objectMapper.readValue(string, ModelDictionary.class);

            if(component.getName().equals(model1.getName())){
                component.setReg(model1.getReg());
                component.setKeyLength(model1.getKeyLength());
                modelService.Add(component,service);
            }
            else{
                component.setReg(model2.getReg());
                component.setKeyLength(model2.getKeyLength());
                modelService.Add(component,service);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return "OK";
    }


    @RequestMapping(value = "/postdelete", method=RequestMethod.DELETE)
    public String delete(String string){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            ModelDictionary component=objectMapper.readValue(string, ModelDictionary.class);
            modelService.Delete(component,service);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return "OK";
    }

    @RequestMapping(value = "/getlistrecords", method=RequestMethod.GET)
    public List<ModelDictionary> listrecords(@RequestParam String string)  {
//        ObjectMapper objectMapper=new ObjectMapper();
//        ModelDictionary component=new ModelDictionary();
//        try {
            //component = objectMapper.readValue(string, ModelDictionary.class);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        ModelDictionary modelDictionary=new ModelDictionary();
        modelDictionary.setname(string);
        return modelService.Show(service, modelDictionary);
    }

    @RequestMapping(value = "/postupdate", method=RequestMethod.POST)
    public String update(@RequestBody String data) {
        ObjectMapper objectMapper=new ObjectMapper();
        ModelDictionary component;
        try {
            component = objectMapper.readValue(data, ModelDictionary.class);
            modelService.Update(component,service);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return "OK";
    }
}
