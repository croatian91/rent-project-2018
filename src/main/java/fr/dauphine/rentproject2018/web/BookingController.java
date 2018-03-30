package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.domain.BookingWrapper;
import fr.dauphine.rentproject2018.domain.Product;
import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.BookingService;
import fr.dauphine.rentproject2018.service.ConfigurationService;
import fr.dauphine.rentproject2018.service.ProductService;
import fr.dauphine.rentproject2018.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("booking")
public class BookingController {

    private final UserService userService;

    private final BookingService bookingService;

    private final ProductService productService;

    private final ConfigurationService configurationService;

    @Autowired
    public BookingController(UserService userService, BookingService bookingService, ProductService productService, ConfigurationService configurationService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.productService = productService;
        this.configurationService = configurationService;
    }

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

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestBody ArrayList<BookingWrapper> bookingWrappers, Principal principal, Model model) {
        User current = userService.findByUsername(principal.getName());

        model.addAttribute("user", current);

        for (BookingWrapper bookingWrapper : bookingWrappers) {
            Booking booking = new Booking();
            Product product = productService.findOne(bookingWrapper.getProduct().getId());

            booking.setProduct(product);
            booking.setStart(bookingWrapper.getStart());
            booking.setEnd(bookingWrapper.getEnd());
            booking.setUser(current);
            booking.setConfiguration(configurationService.findLast());

            bookingService.create(booking);
        }

        return "account/preview";
    }

    @RequestMapping(value = "{bookingID}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "bookingID") int bookingID, Model model) {
        Booking current = bookingService.findOne(bookingID);

        model.addAttribute("booking", current);

        return "booking/edit";
    }
}
