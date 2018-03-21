package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Configuration;
import fr.dauphine.rentproject2018.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AdministrationRestController {

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping("/administration/all")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Configuration> List() {
        return configurationService.findAll();
    }

    @RequestMapping("/administration/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Configuration create(@ModelAttribute Configuration configuration) {
        return configurationService.create(configuration);
    }

    @RequestMapping("/administration/update")
    @ResponseStatus(HttpStatus.OK)
    public Configuration update(@ModelAttribute Configuration configuration) {
        return configurationService.update(configuration);
    }

    @RequestMapping("/administration/findOne")
    @ResponseStatus(HttpStatus.OK)
    public Configuration findOne(@RequestBody Configuration configuration) {
        Configuration c = configurationService.findOne(configuration.getId());

        return c == null ? new Configuration() : configuration;
    }

    @RequestMapping("/administration/{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        configurationService.delete(configurationService.findOne(id));
    }
}
