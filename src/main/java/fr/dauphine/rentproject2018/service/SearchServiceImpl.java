package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.index.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService {
    private final UserRepository userRepository;

    @Autowired
    public SearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Collection<User> findUserByUsernameOrEmailOrFirstNameOrLastName(String username, String email, String firstName, String lastName) {
        return userRepository.findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(username, email, firstName, lastName);
    }
}
