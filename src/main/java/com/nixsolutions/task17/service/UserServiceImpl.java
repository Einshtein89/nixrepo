package com.nixsolutions.task17.service;

import com.nixsolutions.task17.dao.UserDao;
import com.nixsolutions.task17.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *Implementation of UserService Interface for Role service operations.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void create(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean isUser(String login) {
        User user = findByLogin(login);
        return user == null;
    }

}
