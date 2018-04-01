package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.Exception.BookingDateConflictException;
import fr.dauphine.rentproject2018.domain.Booking;
import fr.dauphine.rentproject2018.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ConfigurationService configurationService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ConfigurationService configurationService) {
        this.bookingRepository = bookingRepository;
        this.configurationService = configurationService;
    }

    @Override
    public Booking create(Booking booking) {
        long N_DmL = configurationService.findLast().getN_DmL();
        long N_DML = configurationService.findLast().getN_DML();
        long diff = (booking.getEnd().getTime() - booking.getStart().getTime()) / 86400000;

        if (booking.getEnd().getTime() < booking.getStart().getTime())
            throw new BookingDateConflictException("End DATE cannot be earlier than start DATE");
        else if (diff < N_DmL)
            throw new BookingDateConflictException("RG2: Rent duration can't be less than " + N_DmL + " days");
        else if (diff > N_DML)
            throw new BookingDateConflictException("RG4: Rent duration can't be more than " + N_DML + " days");

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
