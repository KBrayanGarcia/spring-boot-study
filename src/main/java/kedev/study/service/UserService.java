package kedev.study.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import kedev.study.model.User;
import kedev.study.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    public Optional<User> fetchUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
