package com.nixsolutions.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.webservices.webservices.soapservice.Role;
import com.nixsolutions.webservices.webservices.soapservice.SoapUserService;
import com.nixsolutions.webservices.webservices.soapservice.SoapUserServiceImplService;
import com.nixsolutions.webservices.webservices.soapservice.User;

public class UserSoapClientTest {

    private SoapUserService soapUser;
    private Role role;

    @Before
    public void setUp() throws Exception {
        SoapUserServiceImplService userSoapService = new SoapUserServiceImplService();
        soapUser = userSoapService.getSoapUserServiceImplPort();
    }

    @Test
    public void addUser() throws DatatypeConfigurationException {
        soapUser.createUser(createUser());
        assertEquals(soapUser.findUserByLogin("login").getLogin(), "login");
        soapUser.removeUser(soapUser.findUserByLogin("login").getId());
    }

    @Test(expected = SOAPFaultException.class)
    public void addUserBad() {
        User user = new User();
        soapUser.createUser(user);
    }

    @Test
    public void editUser() throws DatatypeConfigurationException {
        soapUser.createUser(createUser());
        User user = soapUser.findUserByLogin("login");
        user.setEmail("vasya111@mail.ru");
        soapUser.updateUser(user);
        assertEquals(soapUser.findUserByLogin("login").getEmail(),
                "vasya111@mail.ru");
        soapUser.removeUser(soapUser.findUserByLogin("login").getId());
    }

    @Test
    public void findAllUsers() {
        List<User> userList = soapUser.findAllUsers().getItem();
        assert (userList.size() > 0);
    }

    @Test
    public void findUserByLogin() throws DatatypeConfigurationException {
        soapUser.createUser(createUser());
        assertEquals(soapUser.findUserByLogin("login").getLogin(), "login");
        soapUser.removeUser(soapUser.findUserByLogin("login").getId());
    }

    @Test
    public void findUserByLoginBad() throws DatatypeConfigurationException {
        soapUser.createUser(createUser());
        try {
            assertNotEquals(soapUser.findUserByLogin("login1").getLogin(),
                    "login");
            fail();
        } catch (Exception e) {
        } finally {
            soapUser.removeUser(soapUser.findUserByLogin("login").getId());
        }

    }

    @Test
    public void findUserByEmail() throws DatatypeConfigurationException {
        soapUser.createUser(createUser());
        assertEquals(soapUser.findUserByEmail("vasya@mail.ru").getEmail(),
                "vasya@mail.ru");
        soapUser.removeUser(soapUser.findUserByLogin("login").getId());
    }

    private User createUser() throws DatatypeConfigurationException {
        role = new Role();
        role.setId(1l);
        role.setName("user");
        User user = new User();
        user.setId(80l);
        user.setLogin("login");
        user.setPassword("password");
        user.setPasswordagain("password");
        user.setEmail("vasya@mail.ru");
        user.setFirstName("vasya");
        user.setLastName("vasya");
        Date date = Date.valueOf("2000-10-10");
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date2 = null;
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        user.setBirthday(date2);
        user.setRole(role);
        return user;
    }

}
