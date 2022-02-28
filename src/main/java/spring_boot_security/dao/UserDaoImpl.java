package spring_boot_security.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import spring_boot_security.model.Role;
import spring_boot_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
//        TypedQuery<User> users = entityManager.createQuery("FROM User", User.class);
        TypedQuery<User> users = entityManager.createQuery("SELECT u FROM User u", User.class);
        return users.getResultList();
    }

    @Override
    public void saveUser(User user, Set<Role> roles) {
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setRoles(roles);
        entityManager.merge(user);
    }

    @Override
    public void updateUser(User user, int id, Set<Role> roles) {
        User user1 = entityManager.find(User.class, id);

        if (!user1.getPassword().equals(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            System.out.println("Пароль изменен!");
        }

        user.setId(id);
        user.setRoles(roles);
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User deleteUser = entityManager.find(User.class, id);
        entityManager.remove(deleteUser);
    }

    @Override
    public User findByUsername(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT  u FROM User u WHERE u.email =:email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
