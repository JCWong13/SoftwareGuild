/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
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

/**
 *
 * @author janie
 */
public class SuperPowerDaoDbImplTest {

    SuperPowerDao powerDao;
    SuperPersonDao personDao;

    public SuperPowerDaoDbImplTest() {
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
        personDao = ctx.getBean("superPersonDao", SuperPersonDao.class);

        List<SuperPerson> superPeople = personDao.getAllSuperPeople();

        for (SuperPerson currentSuperPerson : superPeople) {
            personDao.deleteSuperPerson(currentSuperPerson.getSuperPersonID());
        }

        List<SuperPower> superPowers = powerDao.getAllSuperPowers();

        for (SuperPower currentSuperPower : superPowers) {
            powerDao.deleteSuperPower(currentSuperPower.getSuperPowerID());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of SuperPowerDaoDbImpl method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testSuperPowerDaoDbImpl() {
    }

    /**
     * Test of addSuperPower method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testAddSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Money");

        powerDao.addSuperPower(superPower);

        SuperPower fromDao = powerDao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(fromDao, superPower);

    }

    /**
     * Test of getSuperPowerByID method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testGetSuperPowerByID() {
        //indirectly tested by other methods
    }

    /**
     * Test of updateSuperPower method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testUpdateSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Money");

        powerDao.addSuperPower(superPower);

        SuperPower fromDao = powerDao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(fromDao, superPower);

        superPower.setSuperPowerName("Intelligence");
        powerDao.updateSuperPower(superPower);
        SuperPower fromDao1 = powerDao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(fromDao1.getSuperPowerName(), "Intelligence");
        
        assertEquals(superPower, powerDao.getSuperPowerByID(superPower.getSuperPowerID()));

    }

    /**
     * Test of deleteSuperPower method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testDeleteSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Money");

        powerDao.addSuperPower(superPower);

        SuperPower superPower2 = new SuperPower();
        superPower2.setSuperPowerName("Intelligence");

        powerDao.addSuperPower(superPower2);

        SuperPower fromDao = powerDao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(fromDao, superPower);

        powerDao.deleteSuperPower(superPower.getSuperPowerID());
        assertNull(powerDao.getSuperPowerByID(superPower.getSuperPowerID()));
        assertEquals(1, powerDao.getAllSuperPowers().size());
    }

    /**
     * Test of getSuperPowerBySuperPersonID method, of class
     * SuperPowerDaoDbImpl.
     */
    @Test
    public void testGetSuperPowerBySuperPersonID() {
        SuperPerson person = new SuperPerson();
        person.setSuperName("IronMan");
        person.setDescription("Groovy");
        person.setTypeOfSuperPerson("Avenger");

        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Arrogance");
        powerDao.addSuperPower(superPower);

        person.setSuperPower(superPower);

        personDao.addSuperPerson(person);

        assertEquals(superPower, powerDao.getSuperPowerBySuperPersonID(person.getSuperPersonID()));

    }

    /**
     * Test of getAllSuperPowers method, of class SuperPowerDaoDbImpl.
     */
    @Test
    public void testGetAllSuperPowers() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Money");

        powerDao.addSuperPower(superPower);

        SuperPower superPower2 = new SuperPower();
        superPower2.setSuperPowerName("Modesty");

        powerDao.addSuperPower(superPower2);
        assertEquals(2, powerDao.getAllSuperPowers().size());
    }
    
    @Test
    public void testGetSuperPowerBySuperPowerName(){
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Money");

        powerDao.addSuperPower(superPower);
        
        SuperPower fromDao = powerDao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(fromDao, powerDao.getSuperPowerBySuperPowerName("Money"));

    }

}
