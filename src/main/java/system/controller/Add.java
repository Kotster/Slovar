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

@Controller
@RequestMapping(value = "/add")
public class Add {

    @Autowired
    ModelService modelService;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @RequestMapping(value = "/getadd", method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView view=new ModelAndView();
        view.addObject("model", new ModelDictionary());
        view.setViewName("Delete");
        return view;
    }
    @RequestMapping(value = "/postadd", method=RequestMethod.POST)
    @ResponseBody
    public String update(@ModelAttribute("model") ModelDictionary component, Model model) throws IOException {
        try {
            modelService.Delete(component,service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
