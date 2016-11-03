package com.nixsolutions.task17;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.task17.dao.RoleDao;
import com.nixsolutions.task17.model.Role;
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
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigTest.class })
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class RoleDaoImplTest {

    @Autowired
    private DataSource dataSource;

    private IDatabaseTester tester;

    @Autowired
    private RoleDao roleDao;

    @Before
    public void setUp() throws Exception {
        tester = new DataSourceDatabaseTester(dataSource);
        tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    }

    @Test
    @DatabaseSetup("classpath:role.xml")
    @ExpectedDatabase(value = "classpath:role_create.xml")
    public void createRole(){
        Role role = new Role(1, "admin");
        roleDao.create(role);
    }

    @Test
    @DatabaseSetup("classpath:role_create.xml")
    @ExpectedDatabase(value = "classpath:role_delete.xml")
    public void deleteRole(){
        Role role = roleDao.findByName("admin");
        roleDao.remove(role);
    }

    @Test
    @DatabaseSetup("classpath:role_create.xml")
    public void findByName(){
        Assert.assertNotNull(roleDao.findByName("admin"));
        Assert.assertNull(roleDao.findByName("user"));
    }

    @Test
    @DatabaseSetup("classpath:role_create.xml")
    public void findById(){
        Assert.assertNotNull(roleDao.findById(1));
        Assert.assertNull(roleDao.findById(3));
    }

    @Test
    @DatabaseSetup("classpath:role_create.xml")
    public void findAll(){
        Assert.assertEquals(roleDao.findAll().size(), 1);
    }
}
