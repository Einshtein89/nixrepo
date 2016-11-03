package com.nixsolutions.task17.dao;

import com.nixsolutions.task17.model.Role;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao {
	
	private static final Logger logger = LoggerFactory
            .getLogger(RoleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Role role) {
        newEntity(role);
    }

    @Override
    public void update(Role role) {
        edit(role);
    }

    @Override
    public void remove(Role role) {
        delete(role);
    }

    @Override
    public Role findByName(String name) {
        if (name == null) {
        	logger.error("name is null");
            throw new NullPointerException("name is null");
        }
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Role a WHERE a.name = :name", Role.class);
        query.setParameter("name", name);
        if ((query.getResultList() != null)
                && (query.getResultList().size() != 0)) {
            return (Role)query.getResultList().get(0);
        } else {
            return null;
        }
    }

    @Override
    public Role findById(long id) {
        if (id <= 0) {
        	logger.error("id should be > 0");
        throw new IllegalArgumentException("id should be > 0");
        }
        return getByKey(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll(){
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT a FROM Role a", Role.class);
        if ((query.getResultList() != null)
                && (query.getResultList().size() != 0)) {
            return query.getResultList();
        } else {
            return null;
        }
    }
}
