package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {

    Booking findById(int id);
}
