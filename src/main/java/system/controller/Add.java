package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.dao.ISlovService;
import system.model.ModelDictionary;
import system.service.ModelService;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/add")
public class Add{

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

    @RequestMapping(value = "/getadd", method = RequestMethod.GET)
    public ModelAndView showadd() {
        ModelAndView view=new ModelAndView();
        view.addObject("model", new ModelDictionary());
        view.addObject("ListDict", Arrays.asList(model1.getName(),model2.getName()));
        view.setViewName("Add");
        return view;
    }
    @RequestMapping(value = "/postadd", method=RequestMethod.POST)
    //@ResponseBody
    public String add(@ModelAttribute("model") ModelDictionary component, Model model) throws IOException {
        try {
            if(component.getName().equals(model1.getName())){
                component.setReg(model1.getReg());
                component.setKeyLength(model1.getKeyLength());
                modelService.Add(component,service);
            }
            component.setReg(model2.getReg());
            component.setKeyLength(model2.getKeyLength());
            modelService.Add(component,service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/add/getadd";
    }
}
