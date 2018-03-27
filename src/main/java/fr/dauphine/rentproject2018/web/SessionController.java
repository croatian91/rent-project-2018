package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.BookingWrapper;
import fr.dauphine.rentproject2018.domain.Cart;
import fr.dauphine.rentproject2018.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Controller
@RequestMapping("cart")
@Scope("session")
public class SessionController {
    @Autowired
    private ProductService productService;

    @Autowired
    private Cart cart;

    @RequestMapping("")
    public String cart(Model model) {
        model.addAttribute("bookingWrappers", cart.getBookingWrappers());
        model.addAttribute("bookingWrapper", new BookingWrapper());

        return "cart/edit";
    }

    @RequestMapping("add/product/{id}/")
    @ResponseStatus(value = HttpStatus.OK)
    public void add(@PathVariable("id") int id) {
        BookingWrapper bookingWrapper = new BookingWrapper();

        bookingWrapper.setProduct(productService.findOne(id));
        bookingWrapper.setStart(new Date());
        bookingWrapper.setEnd(new Date());

        cart.addBookingWrapper(bookingWrapper);
    }

    @RequestMapping("remove/product/{id}/")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable("id") int id) {
        BookingWrapper bookingWrapper = new BookingWrapper();

        bookingWrapper.setProduct(productService.findOne(id));

        cart.removeBookingWrapper(bookingWrapper);
    }
}
