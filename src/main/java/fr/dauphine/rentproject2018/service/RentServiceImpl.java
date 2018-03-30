package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Rent;
import fr.dauphine.rentproject2018.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent create(Rent rent) {
        rent.setStart(new Date());

        return rentRepository.save(rent);
    }

    @Override
    public Rent update(Rent rent) {
        Rent current = rentRepository.findById(rent.getId());

        if (current != null) {
            current.setBooking(rent.getBooking());
            current.setEnd(rent.getEnd());

            rentRepository.save(current);
        }

        return current;
    }

    @Override
    public void delete(Rent rent) {
        rentRepository.delete(rent);
    }

    @Override
    public Rent findOne(int id) {
        return rentRepository.findById(id);
    }

    @Override
    public Collection<Rent> findAllCurrentByUser(int id) {
        return rentRepository.findAllByEndIsNullAndBookingUserIdOrderByEndDesc(id);
    }

    @Override
    public Collection<Rent> findAllByUser(int id) {
        return rentRepository.findAllByBookingUserIdOrderByEndDesc(id);
    }

    @Override
    public Page findAll(Pageable pageable, int id) {
        return rentRepository.findAllByBookingUserIdOrderByEndDesc(pageable, id);
    }
}
