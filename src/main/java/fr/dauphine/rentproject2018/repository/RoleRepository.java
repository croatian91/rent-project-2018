package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findById(int id);
}
