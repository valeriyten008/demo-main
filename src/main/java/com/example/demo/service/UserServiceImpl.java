package com.example.demo.service;

import jakarta.transaction.Transactional;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @Override
    public void save(User user) {
        List<Role> roles = user.getRoles().stream()
                .map(role -> roleService.findById(role.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = findById(id);

        if (optionalUser.isPresent()) {
            User userToBeUpdated = optionalUser.get();

            userToBeUpdated.setFirstName(updatedUser.getFirstName());
            userToBeUpdated.setLastName(updatedUser.getLastName());
            userToBeUpdated.setAge(updatedUser.getAge());
            userToBeUpdated.setEmail(updatedUser.getEmail());
            userToBeUpdated.setPassword(updatedUser.getPassword());
            userToBeUpdated.setRoles(updatedUser.getRoles());

            userRepository.save(userToBeUpdated);
        }
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}