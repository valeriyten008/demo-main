package service;

import model.Role;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void updateUser(Long id, User updatedUser);
    void deleteById(Long id);
    User findByEmail(String email);
    List<Role> getAllRoles();
}
