package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RentService {

    Rent create(Rent rent);

    Rent update(Rent rent);

    void delete(Rent rent);

    Rent findOne(int id);

    Collection<Rent> findAllCurrentByUser(int id);

    Collection<Rent> findAllByUser(int id);

    Page findAll(Pageable pageable, int id);
}
