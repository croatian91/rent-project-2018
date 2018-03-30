package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Configuration;
import fr.dauphine.rentproject2018.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Configuration create(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    @Override
    public Configuration update(Configuration configuration) {
        Configuration current = configurationRepository.findById(configuration.getId());

        if (current != null) {
            current.setA_DJ(configuration.getA_DJ());
            current.setN_DML(configuration.getN_DML());
            current.setN_DmL(configuration.getN_DmL());
            current.setN_DMR(configuration.getN_DMR());
            current.setN_MOL(configuration.getN_MOL());
            current.setN_MOR(configuration.getN_MOR());

            configurationRepository.save(current);
        }

        return current;
    }

    @Override
    public void delete(Configuration configuration) {
        configurationRepository.delete(configuration);
    }

    @Override
    public Configuration findOne(int id) {
        return configurationRepository.findById(id);
    }

    @Override
    public Collection<Configuration> findAll() {
        return configurationRepository.findAll();
    }

    @Override
    public Configuration findLast() {
        return configurationRepository.findFirstByOrderByIdDesc();
    }
}
