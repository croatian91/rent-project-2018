package fr.dauphine.rentproject2018.index;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Integer> {

    User findById(int id);

    User findByUsername(String username);

    Collection<User> findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String username, String email, String firstName, String lastName);
}
