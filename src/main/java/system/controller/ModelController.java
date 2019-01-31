package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import system.dao.ISlovService;
import system.model.ModelDictionary;
import system.service.ModelService;

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
        view.addObject("model", new ModelDictionary());
        view.addObject("ListDict", Arrays.asList(model1.getName(),model2.getName()));
        view.setViewName("home");
        return view;
    }
    @RequestMapping(value = "/dict", method=RequestMethod.POST)
    public String post(@ModelAttribute("model") ModelDictionary component, Model model)  {
        model.addAttribute("list", modelService.Show(service, component));
        return "dictionary";
    }


}
