/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.Organization;
import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author janie
 */
public class OrganizationDaoTest {

    JdbcTemplate jt;
    SuperPowerDao powerDao;
    LocationDao locationDao;
    SuperPersonDao personDao;
    SightingDao sightingDao;
    OrganizationDao orgDao;

    public OrganizationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        orgDao = ctx.getBean("organizationDao", OrganizationDao.class);
        powerDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        personDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        jt = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        final String SQL_DELETE_ALL_SUPERPERSON
                = "delete from superperson";

        final String SQL_DELETE_ALL_LOCATIONS
                = "delete from location";

        final String SQL_DELETE_ALL_SIGHTINGS
                = "delete from sighting";

        final String SQL_DELETE_ALL_SUPERPOWERS
                = "delete from superpower";

        final String SQL_DELETE_SUPERPERSONSIGHTING
                = "delete from SuperPersonSighting";
        
        final String SQL_DELETE_ORGANIZATIONS
                ="delete from Organization";
        
        final String SQL_DELETE_SUPERPERSONORGANIZATIONS
                ="delete from SuperPersonOrganization";

        jt.update(SQL_DELETE_SUPERPERSONSIGHTING);
        jt.update(SQL_DELETE_SUPERPERSONORGANIZATIONS);
        jt.update(SQL_DELETE_ALL_SUPERPERSON);
        jt.update(SQL_DELETE_ALL_SIGHTINGS);
        jt.update(SQL_DELETE_ALL_LOCATIONS);
        jt.update(SQL_DELETE_ALL_SUPERPOWERS);
        jt.update(SQL_DELETE_ORGANIZATIONS);

        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Money");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Batman");
        person.setDescription("EMO");
        person.setTypeOfSuperPerson("EMO VIGILANTE");
        person.setSightings(sightings);
        person.setSuperPower(power);

        personDao.addSuperPerson(person);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddOrganization() {
        Organization org = new Organization();
            org.setTypeOfOrganization("Evil");
            org.setOrganizationName("League of Extraordinary Cats");
            org.setOrganizationDescription("Puuuurrrrfect");
            org.setOrganizationAddress("1001 CatsRule Lane");
            org.setOrganizationContact("1-800-MEOW");
        
            orgDao.addOrganization(org);

        Organization fromDao = orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(fromDao, org);
    }

    /**
     * Test of getOrganizationByID method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganizationByID() {
        //indirectly tested, please see other methods
    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org = new Organization();
            org.setTypeOfOrganization("Evil");
            org.setOrganizationName("League of Extraordinary Cats");
            org.setOrganizationDescription("Puuuurrrrfect");
            org.setOrganizationAddress("1001 CatsRule Lane");
            org.setOrganizationContact("1-800-MEOW");
        
            orgDao.addOrganization(org);

        Organization fromDao = orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(fromDao, org);
        
        org.setOrganizationName("WOLVES R US");
        org.setTypeOfOrganization("WOLF");
        
        orgDao.updateOrganization(org);
        assertEquals("WOLVES R US", orgDao.getOrganizationByID(org.getOrganizationID()).getOrganizationName());
        assertEquals("WOLF", orgDao.getOrganizationByID(org.getOrganizationID()).getTypeOfOrganization());
        assertEquals(org, orgDao.getOrganizationByID(org.getOrganizationID()));
        
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org = new Organization();
            org.setTypeOfOrganization("Evil");
            org.setOrganizationName("League of Extraordinary Cats");
            org.setOrganizationDescription("Puuuurrrrfect");
            org.setOrganizationAddress("1001 CatsRule Lane");
            org.setOrganizationContact("1-800-MEOW");
        
            orgDao.addOrganization(org);

        Organization fromDao = orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, fromDao);
        assertEquals(1, orgDao.getAllOrganizations().size());
        
        Organization org2 = new Organization();
            org2.setTypeOfOrganization("Good");
            org2.setOrganizationName("The Avengers");
            org2.setOrganizationDescription("Band of Lovable Misfits");
            org2.setOrganizationAddress("New York");
            org2.setOrganizationContact("1-800-AsgardHotline");
        
            orgDao.addOrganization(org2);
            assertEquals(2, orgDao.getAllOrganizations().size());
            assertEquals(org2, orgDao.getOrganizationByID(org2.getOrganizationID()));
            
            orgDao.deleteOrganization(org2.getOrganizationID());
            assertEquals(1, orgDao.getAllOrganizations().size());
            assertNull(orgDao.getOrganizationByID(org2.getOrganizationID()));
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        //tested indirectly please see above
    }

    /**
     * Test of getAllOrganizationsBySuperPerson method, of class
     * OrganizationDao.
     */
    @Test
    public void testGetAllOrganizationsBySuperPerson() {
         Organization org = new Organization();
            org.setTypeOfOrganization("Evil");
            org.setOrganizationName("League of Extraordinary Cats");
            org.setOrganizationDescription("Puuuurrrrfect");
            org.setOrganizationAddress("1001 CatsRule Lane");
            org.setOrganizationContact("1-800-MEOW");
        
            orgDao.addOrganization(org);
            
        List<SuperPerson> people = personDao.getAllSuperPeople();
        SuperPerson person = people.get(0);
        
        List<Organization> organizations = orgDao.getAllOrganizationsBySuperPerson(person.getSuperPersonID());
        assertEquals(0, organizations.size());
       
        organizations.add(org);
        person.setOrganizations(organizations);
        personDao.updateSuperPerson(person);
        assertEquals(1, orgDao.getAllOrganizationsBySuperPerson(person.getSuperPersonID()).size());
        
        Organization org2 = new Organization();
            org2.setTypeOfOrganization("Good");
            org2.setOrganizationName("The Avengers");
            org2.setOrganizationDescription("Lovable Misfits");
            org2.setOrganizationAddress("New York");
            org2.setOrganizationContact("1-800-Tony-Stark");
        
            orgDao.addOrganization(org2);
            
            organizations.add(org2);
            person.setOrganizations(organizations);
            personDao.updateSuperPerson(person);
            
            assertEquals(2, orgDao.getAllOrganizationsBySuperPerson(person.getSuperPersonID()).size());
            assertEquals(organizations, orgDao.getAllOrganizationsBySuperPerson(person.getSuperPersonID()));
    }

  
}
