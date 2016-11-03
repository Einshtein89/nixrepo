package com.nixsolutions.task17.dao;


import com.nixsolutions.task17.model.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	private static final Logger logger = LoggerFactory
            .getLogger(UserDaoImpl.class);

	
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        newEntity(user);
    }

    @Override
    public void update(User user) {
        edit(user);
    }

    @Override
    public void remove(User user) {
        delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT a FROM User a", User.class);
        if ((query.getResultList() != null)
                && (query.getResultList().size() != 0)) {
            return query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    public User findByLogin(String login) {
        if (login == null) {
        	logger.error("login is null");
            throw new NullPointerException("login is null");
        }
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT a FROM User a WHERE a.login = :login", User.class);
        query.setParameter("login", login);
        if ((query.getResultList() != null)
                && (query.getResultList().size() != 0)) {
            return (User) query.getResultList().get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) {
        	logger.error("email is null");
            throw new NullPointerException("email is null");
        }
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT a FROM User a WHERE a.email = :email", User.class);
        query.setParameter("email", email);
        if ((query.getResultList() != null)
                && (query.getResultList().size() != 0)) {
            return (User) query.getResultList().get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findById(long id) {
        if (id <= 0) {
        	logger.error("id should be > 0");
            throw new IllegalArgumentException("id should be > 0");
        }
        return getByKey(id);
    }
}
