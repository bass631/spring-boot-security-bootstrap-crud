package spring_boot_security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot_security.model.Role;
import spring_boot_security.model.User;
import java.util.List;
import java.util.Set;

//public interface UserService {
public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void saveUser(User user, Set<Role> roles);

    User getUser(int id);

    void deleteUser(int id);
}
