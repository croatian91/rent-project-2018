package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {

    Booking findById(int id);

    Collection<Booking> findAllByUserIdOrderByEndDesc(int id);

    Collection<Booking> findAllByRentEndIsNullAndUserIdOrderByEndDesc(int id);

    Page findAllByRentEndIsNullAndUserIdOrderByEndDesc(Pageable pageable, int id);
}
