package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    User create(User user);

    User update(User user);

    void delete(User user);

    User findOne(int id);

    Collection<User> findAll();

    void save(User user);

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);
}
