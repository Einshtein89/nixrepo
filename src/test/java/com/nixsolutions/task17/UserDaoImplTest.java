package com.nixsolutions.task17;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.task17.dao.UserDao;
import com.nixsolutions.task17.model.Role;
import com.nixsolutions.task17.model.User;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.sql.Date;

import javax.sql.DataSource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigTest.class })
@Transactional(propagation = Propagation.SUPPORTS)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;
    
    private IDatabaseTester tester;
    
    @Autowired
    private DataSource dataSource;
    
    @Before
    public void setUp() throws Exception {
        tester = new DataSourceDatabaseTester(dataSource);
        tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    }

    @Test
    @DatabaseSetup("classpath:user_create.xml")
    @ExpectedDatabase(value = "classpath:user_delete.xml")
    public void deleteUser(){
        User user = userDao.findByLogin("Jonny");
        userDao.remove(user);
    }


    @Test
    @DatabaseSetup("classpath:user_create.xml")
    public void findAllUser(){
        Assert.assertEquals(userDao.findAll().size(), 3);
    }

    @Test
    @DatabaseSetup("classpath:user_create.xml")
    public void findByLogin(){
        Assert.assertNotNull(userDao.findByLogin("Jonny"));
        Assert.assertNull(userDao.findByLogin("vasya"));
    }
    
    
    @Test
    @DatabaseSetup("classpath:user_create.xml")
    @ExpectedDatabase(value = "classpath:user_update.xml")
    public void updateUser(){
        User user = userDao.findByLogin("Jonny");
        user.setFirstName("qwerty");
        userDao.update(user);
    }
}
