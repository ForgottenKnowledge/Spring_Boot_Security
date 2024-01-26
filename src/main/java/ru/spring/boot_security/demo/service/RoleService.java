package ru.spring.boot_security.demo.service;

import ru.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> findRoles(List<Long> roles);
}
