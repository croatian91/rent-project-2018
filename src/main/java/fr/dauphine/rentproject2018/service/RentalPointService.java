package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.RentalPoint;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RentalPointService {

    Collection<RentalPoint> findAll();
}
