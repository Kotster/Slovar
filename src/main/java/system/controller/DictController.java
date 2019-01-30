package system.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.model.ModelCont;
import system.model.ModelDictionary;

import java.io.IOException;

@Controller
@RequestMapping(value = "select")
public class DictController {
    @RequestMapping(value = "/selectDict", method=RequestMethod.POST)
    public String selectDict(@ModelAttribute("model") ModelCont component, Model model) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        ModelDictionary user = (ModelDictionary) mapper.readValue(component.getStr(), ModelDictionary.class);
        model.addAttribute("modelDictionary",user);
        return "UDdict";
    }
}
