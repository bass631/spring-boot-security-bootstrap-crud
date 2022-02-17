package spring_boot_security.dao;

import spring_boot_security.model.Role;

public interface RoleDao {

    Role getRoleByName(String name);
}
