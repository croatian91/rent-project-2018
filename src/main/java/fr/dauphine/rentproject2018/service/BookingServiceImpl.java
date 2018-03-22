package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        Booking current = bookingRepository.findById(booking.getId());

        if (current != null) {
            current.setEnd(booking.getEnd());

            bookingRepository.save(current);
        }

        return current;
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public Booking findOne(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Collection<Booking> findAllByUser(int id) {
        return bookingRepository.findAllByUserIdOrderByEndDesc(id);
    }

    @Override
    public Collection<Booking> findAllCurrentByUser(int id) {
        return bookingRepository.findAllByRentEndIsNullAndUserIdOrderByEndDesc(id);
    }

    @Override
    public Page findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Page findAllCurrentByUser(Pageable pageable, int id) {
        return bookingRepository.findAllByRentEndIsNullAndUserIdOrderByEndDesc(pageable, id);
    }
}
