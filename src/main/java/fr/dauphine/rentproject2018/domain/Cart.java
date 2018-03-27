package fr.dauphine.rentproject2018.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Scope("session")
public class Cart {
    private Collection<BookingWrapper> bookingWrappers = new ArrayList<>();

    public Collection<BookingWrapper> getBookingWrappers() {
        return bookingWrappers;
    }

    public void setBookingWrappers(Collection<BookingWrapper> bookingWrappers) {
        this.bookingWrappers = bookingWrappers;
    }

    public void addBookingWrapper(BookingWrapper bookingWrapper) {
        bookingWrappers.add(bookingWrapper);
    }

    public void removeBookingWrapper(BookingWrapper bookingWrapper) {
        bookingWrappers.remove(bookingWrapper);
    }
}
