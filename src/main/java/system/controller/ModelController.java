package system.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import system.dao.ISlovService;
import system.model.ModelCont;
import system.model.ModelDictionary;
import system.service.ModelService;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class ModelController {

    @Autowired
    @Qualifier("dictionary1")
    ModelDictionary model1;

    @Autowired
    @Qualifier("dictionary2")
    ModelDictionary model2;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @Autowired
    ModelService modelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showModel() {
        ModelAndView view=new ModelAndView();
        view.addObject("model", new ModelCont());
        view.addObject("ListDict", Arrays.asList(model1.getName(),model2.getName()));
        view.setViewName("home");
        return view;
    }
    @RequestMapping(value = "/dict", method=RequestMethod.POST)
    public String post(@ModelAttribute("model") ModelCont component, Model model)  {
        model.addAttribute("list", modelService.Show(service, component.getModelDictionary()));
        return "dictionary";
    }


}
