package com.nixsolutions.task17.service;

import com.nixsolutions.task17.model.Role;

import java.util.List;

/**
 * Interface for Role service operations.
 */
public interface RoleService {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    List<Role> findAll();

    Role findById(long id);
}
