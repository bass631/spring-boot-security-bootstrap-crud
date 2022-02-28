package spring_boot_security.dao;

import org.springframework.stereotype.Repository;
import spring_boot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> roles = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return roles.getResultList();
    }

    @Override
    public Set<Role> getRoleById(List<Integer> rolesId) {
        TypedQuery<Role> role = entityManager.createQuery("select r from Role r where r.id in :role", Role.class)
                .setParameter("role", rolesId);
        return new HashSet<>(role.getResultList());
    }
}
