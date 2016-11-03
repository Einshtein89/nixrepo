package com.nixsolutions.task17.service;

import com.nixsolutions.task17.model.User;

import java.util.List;

/**
 *Interface for User service operations.
 */
public interface UserService {

    void create(User user);

    void update(User user);

    void remove(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);

    User findById(long id);

    boolean isUser(String login);

}
