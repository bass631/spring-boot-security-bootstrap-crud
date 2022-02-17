package spring_boot_security.service;

import spring_boot_security.model.Role;

public interface RoleService {

    Role getRoleByName(String name);
}