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
import java.time.LocalDate;
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
public class SightingDaoTest {

    JdbcTemplate jt;
    SuperPowerDao powerDao;
    LocationDao locationDao;
    SuperPersonDao personDao;
    SightingDao sightingDao;

    public SightingDaoTest() {
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
                = "delete from sighting";

        final String SQL_DELETE_ALL_SUPERPOWERS
                = "delete from superpower";

        final String SQL_DELETE_SUPERPERSONSIGHTING
                = "delete from SuperPersonSighting";

        jt.update(SQL_DELETE_SUPERPERSONSIGHTING);
        jt.update(SQL_DELETE_ALL_SUPERPERSON);
        jt.update(SQL_DELETE_ALL_SIGHTINGS);
        jt.update(SQL_DELETE_ALL_LOCATIONS);
        jt.update(SQL_DELETE_ALL_SUPERPOWERS);

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
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddSighting() {

        LocalDateTime local = LocalDateTime.parse("1900-04-08 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<Location> locations = locationDao.getAllLocations();
        Location location = locations.get(0);

        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);
        Sighting sighting1 = sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting1, sighting);
    }

    /**
     * Test of getSightingByID method, of class SightingDao.
     */
    @Test
    public void testGetSightingByID() {
        //INDIRECTLY TESTED, SEE ABOVE
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
        LocalDateTime local = LocalDateTime.parse("1900-04-08 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<Location> locations = locationDao.getAllLocations();
        Location location = locations.get(0);

        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);
        Sighting sighting1 = sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting1, sighting);

        LocalDateTime local2 = LocalDateTime.parse("2001-04-08 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sighting.setSightingDateTime(local2);
        sightingDao.updateSighting(sighting);
        assertEquals(sighting, sightingDao.getSightingByID(sighting.getSightingID()));

    }

    /**
     * Test of deleteSighting method, of class SightingDao.
     */
    @Test
    public void testDeleteSighting() {
        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        LocalDateTime local = LocalDateTime.parse("1900-04-08 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        sightingDao.deleteSighting(sighting.getSightingID());
        sightings = sightingDao.getAllSightings();
        assertEquals(1, sightings.size());
    }

    /**
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testGetAllSightings() {
        //indirectly tested, see above
    }

    /**
     * Test of getAllSightingsForDate method, of class SightingDao.
     */
    @Test
    public void testGetAllSightingsForDate() {
        LocalDate local = LocalDate.parse("1986-04-08", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(1, sightings.size());
        assertEquals(sightings, sightingDao.getAllSightingsForDate(local));

        Location location = new Location();
        location.setLocationName("Gotham");
        location.setLocationDescription("Dark and Gloomy");
        location.setLatitude(new BigDecimal("123.239475"));
        location.setLongitude(new BigDecimal("475.222222"));

        locationDao.addLocation(location);

        LocalDateTime local2 = LocalDateTime.parse("2004-04-12 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local2);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        sightings = sightingDao.getAllSightings();
        Sighting sighting1 = sightings.get(1);
        assertEquals(2, sightings.size());
        LocalDate local3 = LocalDate.parse("2004-04-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(sighting1, sightingDao.getAllSightingsForDate(local3).get(0));

        LocalDateTime local4 = LocalDateTime.parse("2004-04-12 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Sighting sighting2 = new Sighting();
        sighting2.setSightingDateTime(local4);
        sighting2.setLocation(location);
        sightingDao.addSighting(sighting);
        assertEquals(2, sightingDao.getAllSightingsForDate(local3).size());
    }

    /**
     * Test of getAllSightingsBySuperPersonID method, of class SightingDao.
     */
    @Test
    public void testGetAllSightingsBySuperPersonID() {
        List<SuperPerson> people = personDao.getAllSuperPeople();
        SuperPerson person = people.get(0);
        assertEquals(1, sightingDao.getAllSightingsBySuperPersonID(person.getSuperPersonID()).size());

        Location location = new Location();
        location.setLocationName("Minnesota");
        location.setLocationDescription("Cold");
        location.setLatitude(new BigDecimal("500.239475"));
        location.setLongitude(new BigDecimal("100.222222"));

        locationDao.addLocation(location);

        LocalDateTime local2 = LocalDateTime.parse("2008-04-12 02:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Sighting> sightings = person.getSightings();

        Sighting sighting = new Sighting();
        sighting.setSightingDateTime(local2);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);
        sightings.add(sighting);

        person.setSightings(sightings);
        personDao.updateSuperPerson(person);
        
        assertEquals(2, sightingDao.getAllSightingsBySuperPersonID(person.getSuperPersonID()).size());
        assertEquals(sightings, sightingDao.getAllSightingsBySuperPersonID(person.getSuperPersonID()));
    }

}
