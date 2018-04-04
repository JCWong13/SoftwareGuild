/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Location;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author janie
 */
public class LocationDaoTest {

    JdbcTemplate jt;
    SuperPowerDao powerDao;
    LocationDao locationDao;
    SuperPersonDao personDao;
    SightingDao sightingDao;

    public LocationDaoTest() {
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
                ="delete from sighting";
        
        final String SQL_DELETE_ALL_SUPERPOWERS
                ="delete from superpower";
        
        final String SQL_DELETE_SUPERPERSONSIGHTING
                ="delete from SuperPersonSighting";

        jt.update(SQL_DELETE_SUPERPERSONSIGHTING);
        jt.update(SQL_DELETE_ALL_SUPERPERSON);
        jt.update(SQL_DELETE_ALL_SIGHTINGS);
        jt.update(SQL_DELETE_ALL_LOCATIONS);
        jt.update(SQL_DELETE_ALL_SUPERPOWERS);
         
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationByID(location.getLocationID());
        assertEquals(fromDao, location);

    }

    /**
     * Test of getLocationByID method, of class LocationDao.
     */
    @Test
    public void testGetLocationByID() {
        //THIS METHOD IS TESTED INDIRECTLY BY OTHER METHODS.  
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationByID(location.getLocationID());
        assertEquals(fromDao, location);

        location.setLocationName("MT OLYMPUS");
        location.setLocationDescription("LIGHT AND AIRY");

        locationDao.updateLocation(location);
        Location fromDao1 = locationDao.getLocationByID(location.getLocationID());
        assertEquals("MT OLYMPUS", fromDao1.getLocationName());
        assertEquals("LIGHT AND AIRY", fromDao1.getLocationDescription());
        
        assertEquals(location ,fromDao1);

    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("MT OLYMPUS");
        location2.setLocationDescription("LIGHT AND AIRY");
        location2.setLatitude(new BigDecimal("123.555475"));
        location2.setLongitude(new BigDecimal("475.002222"));

        locationDao.addLocation(location2);

        Location fromDao2 = locationDao.getLocationByID(location2.getLocationID());
        assertEquals(fromDao2, location2);

        locationDao.deleteLocation(location2.getLocationID());
        assertNull(locationDao.getLocationByID(location2.getLocationID()));
        assertEquals(1, locationDao.getAllLocations().size());

    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {
        //INDIRECTLY TESTED ABOVE IN DELETE METHOD
    }

    /**
     * Test of getAllSuperPeople method, of class LocationDao.
     */
    @Test
    public void testGetAllLocationsBySuperPersonId() {

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
        
        assertEquals(1, locationDao.getAllLocationsBySuperPersonID(person.getSuperPersonID()).size());
        
        Location location2 = new Location();
        location.setLocationName("Minnesota");
        location.setLocationDescription("Icy");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("100.222222"));

        locationDao.addLocation(location2);
        
        assertEquals(1, locationDao.getAllLocationsBySuperPersonID(person.getSuperPersonID()).size());
    }

}
