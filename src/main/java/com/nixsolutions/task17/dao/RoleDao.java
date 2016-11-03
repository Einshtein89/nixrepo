package com.nixsolutions.task17.dao;

import com.nixsolutions.task17.model.Role;

import java.util.List;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    Role findById(long id);

    List<Role> findAll();
}
