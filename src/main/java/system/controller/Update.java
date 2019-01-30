package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.dao.ISlovService;
import system.model.ModelDictionary;
import system.service.ModelService;

import java.io.IOException;

@Controller
@RequestMapping(value = "/update")
public class Update {

    @Autowired
    ModelService modelService;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @RequestMapping(value = "/update", method=RequestMethod.POST)
    public String update(@ModelAttribute("model") ModelDictionary component, Model model) throws IOException {
        try {
            modelService.Update(component,service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home";
    }
}
