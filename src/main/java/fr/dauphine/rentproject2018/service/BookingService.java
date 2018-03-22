package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookingService {

    Booking create(Booking booking);

    Booking update(Booking booking);

    void delete(Booking booking);

    Booking findOne(int id);

    Collection<Booking> findAllByUser(int id);

    Collection<Booking> findAllCurrentByUser(int id);

    Page findAll(Pageable pageable);

    Page findAllCurrentByUser(Pageable pageable, int id);
}
