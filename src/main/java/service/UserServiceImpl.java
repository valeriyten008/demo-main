package service;

import jakarta.transaction.Transactional;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

        @Override
        public void updateUser(Long id, User updatedUser) {
            Optional<User> optionalUser = findById(id);

            if (optionalUser.isPresent()) {
                User userToBeUpdated = optionalUser.get();
                userToBeUpdated.setUsername(updatedUser.getUsername());
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