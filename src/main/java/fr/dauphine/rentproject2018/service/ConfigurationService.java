package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Configuration;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ConfigurationService {

    Configuration create(Configuration configuration);

    Configuration update(Configuration configuration);

    void delete(Configuration configuration);

    Configuration findOne(int id);

    Collection<Configuration> findAll();

    Configuration findLast();
}
