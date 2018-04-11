/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VMItem;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author janie
 */
public class VMDaoTest {

    VMItem blah = new VMItem("A1", "Blah", new BigDecimal("2.00"), 5);
    VMItem blah2 = new VMItem("A2", "Blah1", new BigDecimal("3.00"), 2);
    VMItem blah3 = new VMItem("A3", "Blah2", new BigDecimal("15.00"), 1);

    private VMDao dao;
//    = new VMDaoImpl("VMInventoryTest.txt");

    public VMDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("vmdao", VMDao.class);
 
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        final String COLLECTION_FILE = "VMInventoryTest.txt";
        final String DELIMITER = "::";

        Scanner scanner;

        List<VMItem> VMItemList = new ArrayList<>();
        VMItemList.add(blah);
        VMItemList.add(blah2);
        VMItemList.add(blah3);

        final PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
            Comparator<VMItem> byLocation = (VMItem o1, VMItem o2)
                    -> o1.getLocationItem().compareTo(o2.getLocationItem());
            VMItemList.stream()
                    .sorted(byLocation)
                    .forEach((i) -> {
                        out.println(i.getLocationItem() + DELIMITER
                                + i.getNameOfItem() + DELIMITER
                                + i.getCostOfItem() + DELIMITER
                                + i.getNumOfItems());
                    });

            out.flush();

            out.close();
        } catch (IOException e) {
            System.out.println("Couldn't create test text file");
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of writeVMInventory method, of class VMDao.
     */
    @Test
    public void testWriteVMInventory() throws Exception {
        //look at tests below
    }

    /**
     * Test of loadVMInventory method, of class VMDao.
     */
    @Test
    public void testLoadVMInventory() throws Exception {
        //look at tests below
    }

    /**
     * Test of getInventory method, of class VMDao.
     */
    @Test
    public void testGetInventory() throws Exception {
        dao.loadVMInventory();
        List<VMItem> inventory = dao.getInventory();
        assertEquals(inventory.size(), 3);

    }

    /**
     * Test of getItemByLocation method, of class VMDao.
     */
    @Test
    public void testGetItemByLocation() throws Exception {
        dao.loadVMInventory();
        assertEquals(dao.getItemByLocation("A1"), blah);
    }

    /**
     * Test of lowerItemQuantity method, of class VMDao.
     */
    @Test
    public void testLowerItemQuantity() throws Exception {
        assertEquals(5, blah.getNumOfItems());
        dao.lowerItemQuantity(blah);
        assertEquals(4, blah.getNumOfItems());
    }

}
