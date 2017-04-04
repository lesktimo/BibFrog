package bibfrog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/inpro")
public class InproceedingsController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addInproceeding(Model model) {
        return "inpro";
    }

}
