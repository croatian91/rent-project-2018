package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

    Configuration findById(int id);

    Configuration findFirstByOrderByIdDesc();
}
