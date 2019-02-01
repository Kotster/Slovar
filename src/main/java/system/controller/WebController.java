package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class WebController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showModel() {
        return "home";
    }

    @RequestMapping(value = "/getsearch", method = RequestMethod.GET)
    public String showsearch() {
        return "Search";
    }

    @RequestMapping(value = "/getupdate", method = RequestMethod.GET)
    public String showupdate() {
        return "Update";
    }

    @RequestMapping(value = "/getadd", method = RequestMethod.GET)
    public String showadd() {
        return "Add";
    }

    @RequestMapping(value = "/getdelete", method = RequestMethod.GET)
    public String showdelete() {
        return "Delete";
    }

    @RequestMapping(value = "/dict", method=RequestMethod.GET)
    public String showdictionary()  {
        return "dictionary";
    }
}
