package fr.dauphine.rentproject2018.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administration")
public class AdministrationController {

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("config", null);

        return "administration/edit";
    }
}
