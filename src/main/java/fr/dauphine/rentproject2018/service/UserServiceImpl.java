package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.repository.RoleRepository;
import fr.dauphine.rentproject2018.elastic.UserElasticRepository;
import fr.dauphine.rentproject2018.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserElasticRepository userElasticRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserElasticRepository userElasticRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userElasticRepository = userElasticRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User user) {
        return userElasticRepository.save(userRepository.save(user));
    }

    @Override
    public User update(User user) {
        User current = userRepository.findById(user.getId());

        if (current != null) {
            current.setRoles(user.getRoles());
            current.setAddress(user.getAddress());
            current.setEmail(user.getEmail());
            current.setPassword(user.getPassword());

            userElasticRepository.save(current);
            userRepository.save(current);
        }

        return current;
    }

    @Override
    public void delete(User user) {
        userElasticRepository.delete(user);
        userRepository.delete(user);
    }

    @Override
    public User findOne(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));

        userElasticRepository.save(userRepository.save(user));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
