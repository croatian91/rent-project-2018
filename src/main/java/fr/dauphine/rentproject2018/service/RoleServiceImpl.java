package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Role;
import fr.dauphine.rentproject2018.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        Role current = roleRepository.findById(role.getId());

        if (current != null) {
            current.setName(role.getName());
            current.setUsers(role.getUsers());
        }

        return current;
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role findOne(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
