package com.nixsolutions.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.webservices.webservices.soapservice.Role;
import com.nixsolutions.webservices.webservices.soapservice.SoapRoleService;
import com.nixsolutions.webservices.webservices.soapservice.SoapRoleServiceImplService;

public class RoleSoapClientTest {

    private SoapRoleService soapRole;
    private Role role;

    @Before
    public void setUp() throws Exception {
        SoapRoleServiceImplService roleSoapService = new SoapRoleServiceImplService();
        soapRole = roleSoapService.getSoapRoleServiceImplPort();
    }

    @Test
    public void addRole() {
        soapRole.createRole(createRole());
        assertEquals(soapRole.findRoleByName("guest").getName(), "guest");
        soapRole.removeRole(soapRole.findRoleByName("guest").getId());
    }

    @Test(expected = SOAPFaultException.class)
    public void addRoleBad() {
        Role role = new Role();
        soapRole.createRole(role);
    }

    @Test
    public void editRole() throws DatatypeConfigurationException {
        soapRole.createRole(createRole());
        Role role = soapRole.findRoleByName("guest");
        role.setName("guest1");
        soapRole.updateRole(role);
        assertEquals(soapRole.findRoleByName("guest1").getName(), "guest1");
        soapRole.removeRole(soapRole.findRoleByName("guest1").getId());
    }

    @Test
    public void findAllRoles() {
        List<Role> roleList = soapRole.findAllRoles().getItem();
        assert (roleList.size() > 0);
    }

    @Test
    public void findRoleByName() throws DatatypeConfigurationException {
        soapRole.createRole(createRole());
        assertEquals(soapRole.findRoleByName("guest").getName(), "guest");
        soapRole.removeRole(soapRole.findRoleByName("guest").getId());
    }

    @Test
    public void findRoleByNameBad() throws DatatypeConfigurationException {
        soapRole.createRole(createRole());
        try {
            assertNotEquals(soapRole.findRoleByName("guest1").getName(),
                    "guest");
            fail();
        } catch (Exception e) {
        } finally {
            soapRole.removeRole(soapRole.findRoleByName("guest").getId());
        }

    }

    private Role createRole() {
        role = new Role();
        role.setId(5l);
        role.setName("guest");
        return role;
    }
}
