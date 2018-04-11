/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FMDao;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author janie
 */
public class FMServiceLayerTest {

    FMDao dao;
    FMServiceLayer service;

    public FMServiceLayerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        dao = new FMDaoStub();
//        service = new FMServiceLayerImpl(dao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FMServiceLayer.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class FMServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
        //pass-through
    }

    /**
     * Test of addOrder method, of class FMServiceLayer.
     */
    @Test
    public void testAddOrder() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("carpet");
        order.setArea(new BigDecimal("100.00"));

        service.addOrder(order);

        assertEquals(new BigDecimal("225.00"), order.getMaterialCost());
        assertEquals(new BigDecimal("210.00"), order.getLaborCost());
        assertEquals(new BigDecimal("27.19"), order.getCalculatedTax());
        assertEquals(new BigDecimal("462.19"), order.getTotalCost());

    }

    /**
     * Test of editOrder method, of class FMServiceLayer.
     */
    @Test
    public void testEditOrder() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("PA");
        order.setProductType("tile");
        order.setArea(new BigDecimal("100.00"));

        service.editOrder(order);
        assertEquals(new BigDecimal("350.00"), order.getMaterialCost());
        assertEquals(new BigDecimal("415.00"), order.getLaborCost());
        assertEquals(new BigDecimal("51.64"), order.getCalculatedTax());
        assertEquals(new BigDecimal("816.64"), order.getTotalCost());
    }

    /**
     * Test of getProductAndStateVerification method, of class FMServiceLayer.
     *
     * @throws com.sg.flooringmastery.service.FMInvalidOrderException
     */
    @Test
    public void testGetProductAndStateVerification() throws FMInvalidOrderException {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("MN");
        order.setProductType("tile");
        order.setArea(new BigDecimal("100.00"));

        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("tile");
        order2.setArea(new BigDecimal("100.00"));

        try {
            service.getProductAndStateVerification(order);
            assertEquals(order2, service.getProductAndStateVerification(order2));
            fail("Expected FMInvalidProductException or FMInvalidStateException Was Not Thrown.");
        } catch (FMInvalidProductException | FMInvalidStateException e) {
        }

        service.addOrder(order2);
        assertEquals(service.orderDateAndNumberVerification(LocalDate.now(), 1), order2);

        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("carpet");
        order2.setArea(new BigDecimal("100.00"));
        service.editOrder(order2);

        assertEquals(new BigDecimal("225.00"), order2.getMaterialCost());

    }

    /**
     * Test of orderDateAndNumberVerification method, of class FMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testOrderDateAndNumberVerification() throws Exception {
        LocalDate ld = LocalDate.parse("02-02-2015", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        try {
            service.orderDateAndNumberVerification(ld, 1);
            fail("Expected FMInvalidOrderException was Not Thrown.");
        } catch (FMInvalidOrderException e) {
        }

        Order order = new Order();
        order.setCustomerName("Janie Wong");
        order.setStateInitials("PA");
        order.setProductType("tile");
        order.setArea(new BigDecimal("100.00"));

        service.addOrder(order);

        assertEquals(order, service.orderDateAndNumberVerification(LocalDate.now(), 1));
    }

    /**
     * Test of removeOrder method, of class FMServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        //pass-through
    }

    /**
     * Test of initializeDao method, of class FMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInitializeDao() throws Exception {
        //pass-through
    }

    /**
     * Test of saveFMOrders method, of class FMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveFMOrders() throws Exception {
        //pass-through method
    }

}
