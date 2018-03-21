package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RoleService {

    Role create(Role role);

    Role update(Role role);

    void delete(Role role);

    Role findOne(int id);

    Collection<Role> findAll();
}
