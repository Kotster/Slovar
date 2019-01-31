package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.dao.ISlovService;
import system.model.ModelDictionary;
import system.service.ModelService;

import java.util.List;

@Controller
@RequestMapping(value = "/search")
public class Search {

    @Autowired
    ModelService modelService;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @RequestMapping(value = "/getsearch", method = RequestMethod.GET)
    public String show() {
        //ModelAndView view=new ModelAndView();
        //view.addObject("model", new ModelDictionary());
        //view.setViewName("Search");
        return "Search";
    }
    @RequestMapping(value = "/postsearch", method=RequestMethod.GET)
    @ResponseBody
    public ModelDictionary search(String key){
        try {
            ModelDictionary modelDictionary=new ModelDictionary();
            modelDictionary.setKey(key);
            List<ModelDictionary> list=modelService.Serch(modelDictionary,service);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelDictionary();
    }
}
