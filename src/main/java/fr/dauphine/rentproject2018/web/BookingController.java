package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("bookings", null);

        return "booking/list";
    }

    @RequestMapping(value = "{bookingID}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "bookingID") int bookingID, Model model) {
        Booking current = bookingService.findOne(bookingID);

        model.addAttribute("booking", current);

        return "booking/edit";
    }
}
