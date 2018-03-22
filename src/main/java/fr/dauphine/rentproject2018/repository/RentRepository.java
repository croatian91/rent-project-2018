package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, Integer> {

    Rent findById(int id);

    Collection<Rent> findAllByBookingUserIdOrderByEndDesc(int id);

    Collection<Rent> findAllByEndIsNullAndBookingUserIdOrderByEndDesc(int id);

    Page findAllByBookingUserIdOrderByEndDesc(Pageable pageable, int id);
}
