package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private final BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/booking/all")
    @ResponseStatus(HttpStatus.OK)
    public Page List(@PageableDefault(size = 5) Pageable pageable) {
        return bookingService.findAll(pageable);
    }

    @RequestMapping("/booking/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@RequestBody Booking booking) {
        return bookingService.create(booking);
    }

    @RequestMapping("/booking/update")
    @ResponseStatus(HttpStatus.OK)
    public Booking update(@ModelAttribute Booking booking) {
        return bookingService.update(booking);
    }

    @RequestMapping("/booking/findOne")
    @ResponseStatus(HttpStatus.OK)
    public Booking findOne(@RequestBody Booking booking) {
        Booking b = bookingService.findOne(booking.getId());

        return b == null ? new Booking() : booking;
    }

    @RequestMapping("/booking/{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        bookingService.delete(bookingService.findOne(id));
    }
}
