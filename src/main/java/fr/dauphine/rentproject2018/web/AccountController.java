package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("preview")
    public String previewUserDetails(Principal principal, Model model) {
        User current = userService.findByUsername(principal.getName());

        model.addAttribute("user", current);

        return "account/preview";
    }

    @RequestMapping("edit")
    public String editUserDetails(Principal principal, Model model) {
        User current = userService.findByUsername(principal.getName());

        model.addAttribute("user", current);

        return "account/edit";
    }

    @RequestMapping("{accountID}/edit")
    public String editUserDetails(@PathVariable int accountID, Model model) {
        User user = userService.findOne(accountID);

        model.addAttribute("user", user);

        return "account/edit";
    }
}
