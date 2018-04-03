package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.BookingService;
import fr.dauphine.rentproject2018.service.RentService;
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

    private final UserService userService;

    private final RentService rentService;

    private final BookingService bookingService;

    @Autowired
    public AccountController(UserService userService, RentService rentService, BookingService bookingService) {
        this.userService = userService;
        this.rentService = rentService;
        this.bookingService = bookingService;
    }

    @RequestMapping("preview")
    public String previewUserDetails(Principal principal, Model model) {
        User current = userService.findByUsername(principal.getName());

        model.addAttribute("user", current);
        model.addAttribute("allRents", rentService.findAllByUser(current.getId()));
        model.addAttribute("currentRents", rentService.findAllCurrentByUser(current.getId()));
        model.addAttribute("currentBookings", bookingService.findAllCurrentByUser(current.getId()));

        return "account/preview";
    }

    @RequestMapping("{accountID}")
    public String previewUserDetails(@PathVariable("accountID") int accountID, Model model) {
        User current = userService.findOne(accountID);

        model.addAttribute("user", current);
        model.addAttribute("allRents", rentService.findAllByUser(current.getId()));
        model.addAttribute("currentRents", rentService.findAllCurrentByUser(current.getId()));
        model.addAttribute("currentBookings", bookingService.findAllCurrentByUser(current.getId()));

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
