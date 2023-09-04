package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
}
