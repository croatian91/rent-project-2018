package fr.dauphine.rentproject2018.elastic;

import fr.dauphine.rentproject2018.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserElasticRepository extends ElasticsearchRepository<User, Integer> {
    Collection<User> findByUsernameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String username, String email, String firstName, String lastName);
}
