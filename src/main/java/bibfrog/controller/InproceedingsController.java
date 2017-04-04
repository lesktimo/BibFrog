package bibfrog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/inpro")
public class InproceedingsController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addInproceeding() {
        return "inpro";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String postInproceeding() {
        return "To be implemented!";
    }

}
