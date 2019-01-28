package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.dao.ISlovService;
import system.model.Model;
import system.service.ModelService;

import java.util.List;

@Controller
@RequestMapping("/models")
public class ModelController {

    @Autowired
    @Qualifier("dictionary1")
    Model model1;

    @Autowired
    @Qualifier("dictionary2")
    Model model2;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService service;

    @Autowired
    ModelService modelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView showModel() {
        ModelAndView view=new ModelAndView();
        view.addObject("msg", "1234");
        return view;
    }
}
