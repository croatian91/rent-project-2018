package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(User user);

    User update(User user);

    void delete(User user);

    User findOne(int id);

    Iterable<User> findAll();

    User save(User user);

    User saveElastic(User user);

    User findByUsername(String username);
}
