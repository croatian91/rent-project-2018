package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);
}
