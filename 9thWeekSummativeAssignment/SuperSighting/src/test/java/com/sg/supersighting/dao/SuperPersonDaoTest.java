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
public class SuperPersonDaoTest {
    
    JdbcTemplate jt;
    SuperPowerDao powerDao;
    LocationDao locationDao;
    SuperPersonDao personDao;
    SightingDao sightingDao;
    OrganizationDao orgDao;
    
    public SuperPersonDaoTest() {
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
     * Test of addSuperPerson method, of class SuperPersonDao.
     */
    @Test
    public void testAddSuperPerson() {
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);
        
        Organization org = new Organization();
        org.setTypeOfOrganization("Good");
        org.setOrganizationName("Boy Scouts Of Murica");
        org.setOrganizationDescription("They tie knots");
        org.setOrganizationAddress("Upper East Side");
        org.setOrganizationContact("1-800-9000");
        
        orgDao.addOrganization(org);

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        
        List<SuperPerson> people=personDao.getAllSuperPeople();
        
        assertEquals("Batman", people.get(0).getSuperName());
        assertEquals(1,people.size());
        
        personDao.addSuperPerson(person);
        person=personDao.getAllSuperPeople().get(0);
        assertEquals(person, personDao.getSuperPersonById(person.getSuperPersonID()));
        assertEquals(2, personDao.getAllSuperPeople().size());
    }

    /**
     * Test of deleteSuperPerson method, of class SuperPersonDao.
     */
    @Test
    public void testDeleteSuperPerson() {
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);
        
        Organization org = new Organization();
        org.setTypeOfOrganization("Good");
        org.setOrganizationName("Boy Scouts Of Murica");
        org.setOrganizationDescription("They tie knots");
        org.setOrganizationAddress("Upper East Side");
        org.setOrganizationContact("1-800-9000");
        
        orgDao.addOrganization(org);

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        
        List<SuperPerson> people=personDao.getAllSuperPeople();
        
        assertEquals("Batman", people.get(0).getSuperName());
        assertEquals(1,people.size());
        
        personDao.addSuperPerson(person);
        people=personDao.getAllSuperPeople();
        
        assertEquals("Dr. Manhattan", people.get(1).getSuperName());
        assertEquals(2, people.size());
        
        personDao.deleteSuperPerson(person.getSuperPersonID());
        people=personDao.getAllSuperPeople();
        assertEquals(1, people.size());
        person=people.get(0);
        assertEquals(person, personDao.getSuperPersonById(person.getSuperPersonID()));
        personDao.deleteSuperPerson(person.getSuperPersonID());

        assertEquals(0, personDao.getAllSuperPeople().size());
        
    }

    /**
     * Test of updateSuperPerson method, of class SuperPersonDao.
     */
    @Test
    public void testUpdateSuperPerson() {
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);
        
        Organization org = new Organization();
        org.setTypeOfOrganization("Good");
        org.setOrganizationName("Boy Scouts Of Murica");
        org.setOrganizationDescription("They tie knots");
        org.setOrganizationAddress("Upper East Side");
        org.setOrganizationContact("1-800-9000");
        
        orgDao.addOrganization(org);

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        
        List<SuperPerson> people=personDao.getAllSuperPeople();
       
        assertEquals(1,people.size());
        
        personDao.addSuperPerson(person);
        people=personDao.getAllSuperPeople();
        
        assertEquals("Dr. Manhattan", people.get(1).getSuperName());
        assertEquals(2, people.size());
        
        person.setSuperName("DJ Khaled");
        person.setTypeOfSuperPerson("BESTEST");
        personDao.updateSuperPerson(person);
        
        people=personDao.getAllSuperPeople();
        person=people.get(1);
        assertEquals(person, personDao.getSuperPersonById(person.getSuperPersonID()));
        assertEquals(2, people.size());
    }

    /**
     * Test of getSuperPersonById method, of class SuperPersonDao.
     */
    @Test
    public void testGetSuperPersonById() {
        //indirectly tested by above method
    }

    /**
     * Test of getSuperPeopleByLocationId method, of class SuperPersonDao.
     */
    @Test
    public void testGetSuperPeopleByLocationId() {
        
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        
        List<SuperPerson> people=personDao.getAllSuperPeople();
        
        assertEquals(1, personDao.getAllSuperPeople().size());
        
        personDao.addSuperPerson(person);
       
        assertEquals(2, personDao.getAllSuperPeople().size());
        
        List<SuperPerson> people2 = personDao.getSuperPeopleByLocationId(location.getLocationID());
        assertEquals(1, people2.size());
        
        people2=personDao.getAllSuperPeople();
        person=people2.get(0);
        person.setSightings(sightings);
        personDao.updateSuperPerson(person);
        assertEquals(2, personDao.getSuperPeopleByLocationId(location.getLocationID()).size());
        assertEquals(personDao.getAllSuperPeople(), personDao.getSuperPeopleByLocationId(location.getLocationID()));
        
    }

    /**
     * Test of getSuperPeopleByOrganizationId method, of class SuperPersonDao.
     */
    @Test
    public void testGetSuperPeopleByOrganizationId() {
        
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);
        
        Organization org = new Organization();
        org.setTypeOfOrganization("Good");
        org.setOrganizationName("Boy Scouts Of Murica");
        org.setOrganizationDescription("They tie knots");
        org.setOrganizationAddress("Upper East Side");
        org.setOrganizationContact("1-800-9000");
        
        orgDao.addOrganization(org);
        List<Organization> organizations = orgDao.getAllOrganizations();

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        person.setOrganizations(organizations);
        
        personDao.addSuperPerson(person);
        person=personDao.getAllSuperPeople().get(1);
        List<SuperPerson> testPeople = new ArrayList<>();
        testPeople.add(person);
        
        assertEquals(1, personDao.getSuperPeopleByOrganizationId(org.getOrganizationID()).size());
        assertEquals(testPeople, personDao.getSuperPeopleByOrganizationId(org.getOrganizationID()));
        
        SuperPerson person2 = new SuperPerson();
        person2.setSuperName("Twinky the Cat");
        person2.setDescription("Fluffy");
        person2.setTypeOfSuperPerson("Purrfect");
        person2.setSightings(sightings);
        person2.setSuperPower(power);
        person2.setOrganizations(organizations);
        
        personDao.addSuperPerson(person2);
        testPeople.add(personDao.getAllSuperPeople().get(2));
        assertEquals(2, personDao.getSuperPeopleByOrganizationId(org.getOrganizationID()).size());
        assertEquals(3, personDao.getAllSuperPeople().size());
        assertEquals(testPeople, personDao.getSuperPeopleByOrganizationId(org.getOrganizationID()));
       
    }

    /**
     * Test of getSuperPeopleBySightingId method, of class SuperPersonDao.
     */
    @Test
    public void testGetSuperPeopleBySightingId() {
        
        Location location = new Location();
        location.setLocationName("Manhattan");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("200.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1986-04-08 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        sightingDao.addSighting(sighting);
        
        Organization org = new Organization();
        org.setTypeOfOrganization("Good");
        org.setOrganizationName("Boy Scouts Of Murica");
        org.setOrganizationDescription("They tie knots");
        org.setOrganizationAddress("Upper East Side");
        org.setOrganizationContact("1-800-9000");
        
        orgDao.addOrganization(org);
        List<Organization> organizations = orgDao.getAllOrganizations();

        SuperPower power = new SuperPower();
        power.setSuperPowerName("Dashing Good Looks");
        powerDao.addSuperPower(power);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Dr. Manhattan");
        person.setDescription("BLUE");
        person.setTypeOfSuperPerson("Large and in charge");
        person.setSightings(sightings);
        person.setSuperPower(power);
        person.setOrganizations(organizations);
        
        personDao.addSuperPerson(person);
        person=personDao.getAllSuperPeople().get(1);
        List<SuperPerson> testPeople = new ArrayList<>();
        testPeople.add(person);
        
        assertEquals(1, personDao.getSuperPeopleBySightingId(sighting.getSightingID()).size());
        assertEquals(testPeople, personDao.getSuperPeopleBySightingId(sighting.getSightingID()));
        
        SuperPerson person2 = new SuperPerson();
        person2.setSuperName("Twinky the Cat");
        person2.setDescription("Fluffy");
        person2.setTypeOfSuperPerson("Purrfect");
        person2.setSightings(sightings);
        person2.setSuperPower(power);
        person2.setOrganizations(organizations);
        
        personDao.addSuperPerson(person2);
        testPeople.add(person2);
        assertEquals(2, personDao.getSuperPeopleBySightingId(sighting.getSightingID()).size());
        assertEquals(testPeople, personDao.getSuperPeopleBySightingId(sighting.getSightingID()));
        
    }

    /**
     * Test of getAllSuperPeople method, of class SuperPersonDao.
     */
    @Test
    public void testGetAllSuperPeople() {
        //Tested indirectly.  Please see above methods.
    }

    
}
