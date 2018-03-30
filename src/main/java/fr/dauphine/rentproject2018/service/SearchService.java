package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface SearchService {
    Collection<User> findUserByUsernameOrEmailOrFirstNameOrLastName(String username, String email, String firstName, String lastName);
}
