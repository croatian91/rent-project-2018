package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.RentalPoint;
import fr.dauphine.rentproject2018.repository.RentalPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RentalPointServiceImpl implements RentalPointService {

    private final RentalPointRepository rentalPointRepository;

    @Autowired
    public RentalPointServiceImpl(RentalPointRepository rentalPointRepository) {
        this.rentalPointRepository = rentalPointRepository;
    }

    @Override
    public Collection<RentalPoint> findAll() {
        return rentalPointRepository.findAll();
    }
}
