package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Configuration;
import fr.dauphine.rentproject2018.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administration")
public class AdministrationController {

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping("edit")
    public String editLatestConfiguration(Model model) {
        Configuration current = configurationService.findLast();

        model.addAttribute("configuration", current);

        return "administration/edit";
    }
}
