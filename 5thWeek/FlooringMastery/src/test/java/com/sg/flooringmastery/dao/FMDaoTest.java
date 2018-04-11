/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FMInvalidProductException;
import com.sg.flooringmastery.service.FMInvalidStateException;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author janie
 */
public class FMDaoTest {

    FMDao dao;

    public FMDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dao", FMDao.class);

    }

    @BeforeClass
    public static void setUpClass() throws FMDaoPersistenceException {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FMDaoPersistenceException {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrdersForDate method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void testGetAllOrdersForDate() throws FMDaoPersistenceException {
        dao.initializeDao();
        LocalDate ld = LocalDate.parse("02-14-2018", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        assertEquals(2, dao.getAllOrdersForDate(ld).size());

        LocalDate ld2 = LocalDate.parse("03-15-2009", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        assertEquals(3, dao.getAllOrdersForDate(ld2).size());
    }

    /**
     * Test of addOrder method, of class FMDao.
     */
    @Test
    public void testAddOrder() {

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setCalculatedTax(new BigDecimal("61.88"));
        order.setTotalCost(new BigDecimal("1051.88"));

        dao.addOrder(order);
        assertEquals(order, dao.getOrderThroughDateAndNumber(LocalDate.now(), 1));

    }

    /**
     * Test of orderDateAndNumberVerification method, of class FMDao.
     */
    @Test
    public void testOrderDateAndNumberVerification() {
        Order order2 = new Order();
        order2.setOrderNumber(3);
        order2.setCustomerName("LORD VOLDEMORT");
        order2.setStateInitials("OH");
        order2.setProductType("wood");
        order2.setArea(new BigDecimal("100.00"));
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setCostPerSquareFoot(new BigDecimal("5.15"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order2.setMaterialCost(new BigDecimal("515.00"));
        order2.setLaborCost(new BigDecimal("475.00"));
        order2.setCalculatedTax(new BigDecimal("61.88"));
        order2.setTotalCost(new BigDecimal("1051.88"));

        dao.addOrder(order2);
        assertEquals(dao.getOrderThroughDateAndNumber(LocalDate.now(), 3), order2);
    }

    /**
     * Test of removeOrder method, of class FMDao.
     */
    @Test
    public void testRemoveOrder() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setCalculatedTax(new BigDecimal("61.88"));
        order.setTotalCost(new BigDecimal("1051.88"));

        dao.addOrder(order);

        Order order2 = new Order();
        order2.setOrderNumber(3);
        order2.setCustomerName("LORD VOLDEMORT");
        order2.setStateInitials("OH");
        order2.setProductType("wood");
        order2.setArea(new BigDecimal("100.00"));
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setCostPerSquareFoot(new BigDecimal("5.15"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order2.setMaterialCost(new BigDecimal("515.00"));
        order2.setLaborCost(new BigDecimal("475.00"));
        order2.setCalculatedTax(new BigDecimal("61.88"));
        order2.setTotalCost(new BigDecimal("1051.88"));
        dao.addOrder(order2);

        assertEquals(2, dao.getAllOrdersForDate(LocalDate.now()).size());
        dao.removeOrder(LocalDate.now(), 3);
        assertEquals(1, dao.getAllOrdersForDate(LocalDate.now()).size());

    }

    /**
     * Test of getNextOrderNumber method, of class FMDao.
     */
    @Test
    public void testGetNextOrderNumber() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setCalculatedTax(new BigDecimal("61.88"));
        order.setTotalCost(new BigDecimal("1051.88"));
        assertEquals(1, dao.getNextOrderNumber(LocalDate.now()));
        dao.addOrder(order);
        assertEquals(2, dao.getNextOrderNumber(LocalDate.now()));

    }

    /**
     * Test of getTaxVerification method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.service.FMInvalidStateException
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void testGetTaxVerification() throws FMInvalidStateException, FMDaoPersistenceException {
        dao.initializeDao();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("MN");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));

        assertFalse(dao.getTaxVerification(order));

        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("wood");
        order2.setArea(new BigDecimal("100.00"));

        assertTrue(dao.getTaxVerification(order2));
    }

    /**
     * Test of getTax method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void testGetTax() throws FMDaoPersistenceException {
        dao.initializeDao();
        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("wood");
        order2.setArea(new BigDecimal("100.00"));

        assertEquals(dao.getTax(order2).getTaxRate(), new BigDecimal("6.75"));

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("MI");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));

        assertEquals(dao.getTax(order).getTaxRate(), new BigDecimal("5.75"));

    }

    /**
     * Test of getProduct method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void testGetProduct() throws FMDaoPersistenceException {
        dao.initializeDao();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("MI");
        order.setProductType("carpet");
        order.setArea(new BigDecimal("100.00"));

        assertEquals(dao.getProduct(order).getCostPerSquareFoot(), new BigDecimal("2.25"));
        assertEquals(dao.getProduct(order).getLaborCostPerSquareFoot(), new BigDecimal("2.10"));

        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("laminate");
        order2.setArea(new BigDecimal("100.00"));

        assertEquals(dao.getProduct(order2).getCostPerSquareFoot(), new BigDecimal("1.75"));
        assertEquals(dao.getProduct(order2).getLaborCostPerSquareFoot(), new BigDecimal("2.10"));
    }

    /**
     * Test of getProductVerification method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.service.FMInvalidProductException
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void testGetProductVerification() throws FMInvalidProductException, FMDaoPersistenceException {
        dao.initializeDao();

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("Carp");
        order.setArea(new BigDecimal("100.00"));

        assertFalse(dao.getProductVerification(order));

        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Janie Wong");
        order2.setStateInitials("PA");
        order2.setProductType("laminate");
        order2.setArea(new BigDecimal("100.00"));

        assertTrue(dao.getProductVerification(order2));
    }

    /**
     * Test of initializeDao method, of class FMDao.
     *
     * @throws com.sg.flooringmastery.dao.FMDaoPersistenceException
     */
    @Test
    public void initializeDao() throws FMDaoPersistenceException {
        dao.initializeDao();
        LocalDate date = LocalDate.parse("03-15-2009", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        assertEquals(dao.getAllOrdersForDate(date).size(), 3);

        //see above tests where I use initialize Dao.
    }

    /**
     * Test of writeFMAllOrders method, of class FMDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteFMAllOrders() throws Exception {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Janie Wong");
        order.setStateInitials("OH");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100.00"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setCalculatedTax(new BigDecimal("61.88"));
        order.setTotalCost(new BigDecimal("1051.88"));
        LocalDate ld = LocalDate.parse("09-09-1900", DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        dao.addOrder(order);
        dao.writeFMAllOrders();

        LocalDate todayDate = LocalDate.now();
        String todayDateString = todayDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        File file = new File("Data/Orders/Orders_" + todayDateString);
        assertTrue(file.exists());
        file.delete();

    }

}
