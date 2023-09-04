package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    User findById(Long id);
    User createUser(User user);
    User findByUsername(String username);
    void deleteUser(Long id);

    void updateUser(Long id, User updatedUser);

}

