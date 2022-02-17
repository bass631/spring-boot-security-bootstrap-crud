package spring_boot_security.dao;

import spring_boot_security.model.Role;
import spring_boot_security.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user, Set<Role> roles);

    User getUser(int id);

    void deleteUser(int id);

    User findByUsername(String username);
}
