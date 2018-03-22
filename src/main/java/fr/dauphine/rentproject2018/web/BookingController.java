package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.BookingService;
import fr.dauphine.rentproject2018.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@PageableDefault(size = 5) Pageable pageable, Principal principal, Model model) {
        User current = userService.findByUsername(principal.getName());

        Page bookings = bookingService.findAllCurrentByUser(pageable, current.getId());

        model.addAttribute("bookings", bookings);

        model.addAttribute("totalPages", bookings.getTotalPages());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "booking/list";
    }

    @RequestMapping(value = "{bookingID}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "bookingID") int bookingID, Model model) {
        Booking current = bookingService.findOne(bookingID);

        model.addAttribute("booking", current);

        return "booking/edit";
    }
}
