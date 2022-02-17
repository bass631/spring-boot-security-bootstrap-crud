package spring_boot_security.dao;

import org.springframework.stereotype.Repository;
import spring_boot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public List<Role> getAllRoles() {
//        TypedQuery<Role> users = entityManager.createQuery("FROM Role", Role.class);
//        System.out.println(users);
//        return users.getResultList();
//    }

    @Override
    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("FROM Role WHERE name=:name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

//    @Override
//    public List<Role> getRoleById(int id) {
//        return entityManager.createQuery("FROM Role WHERE id=:id", Role.class)
//                .setParameter("id", id).getResultList();
//    }
}
