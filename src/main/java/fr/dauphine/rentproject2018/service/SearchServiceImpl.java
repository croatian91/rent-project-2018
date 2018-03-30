package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.elastic.UserElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService {
    private final UserElasticRepository userElasticRepository;

    @Autowired
    public SearchServiceImpl(UserElasticRepository userElasticRepository) {
        this.userElasticRepository = userElasticRepository;
    }


    @Override
    public Collection<User> findUserByUsernameOrEmailOrFirstNameOrLastName(String username, String email, String firstName, String lastName) {
        return userElasticRepository.findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(username, email, firstName, lastName);
    }
}
