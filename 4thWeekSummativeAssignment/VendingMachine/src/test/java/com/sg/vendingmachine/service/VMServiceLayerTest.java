/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VMDao;
import com.sg.vendingmachine.dao.VMInventoryDaoException;
import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.Transaction;
import com.sg.vendingmachine.dto.User;
import com.sg.vendingmachine.dto.VMItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author janie
 */
public class VMServiceLayerTest {

    VMServiceLayer service;
    User user;

    public VMServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VMServiceLayer.class);
        user = ctx.getBean("user", User.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        dao = new VMDaoStub();
//        user = new User(new BigDecimal("10.00"));
//        service = new VMServiceLayerImpl(user, dao);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateAddUserBalance method, of class VMServiceLayer.
     */
    @Test
    public void testCalculateAddUserBalance() {
        assertEquals(new BigDecimal("10.00"), user.getUserBalance());
        service.calculateAddUserBalance(Currency.DOLLAR);
        assertEquals(new BigDecimal("11.00"), user.getUserBalance());
    }

    /**
     * Test of calculateDepositPurchaseBalance method, of class VMServiceLayer.
     */
    @Test
    public void testCalculateDepositPurchaseBalance() {
        assertEquals(new BigDecimal("10.00"), user.getUserBalance());
        assertEquals(new BigDecimal("0"), user.getMoneyDeposited());
        service.calculateDepositPurchaseBalance(Currency.QUARTER);
        assertEquals(new BigDecimal("9.75"), user.getUserBalance());
        assertEquals(new BigDecimal("0.25"), user.getMoneyDeposited());
    }

    /**
     * Test of getVMInventory method, of class VMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVMInventory() throws Exception {
        assertEquals(3, service.getVMInventory().size());
    }

    /**
     * Test of purchaseItem method, of class VMServiceLayer.
     * @throws com.sg.vendingmachine.service.VMInsufficientFundsException
     * @throws com.sg.vendingmachine.service.VMNoItemInventoryException
     * @throws com.sg.vendingmachine.dao.VMInventoryDaoException
     */
    @Test
    public void testPurchaseItem() throws VMInsufficientFundsException, VMNoItemInventoryException, VMInventoryDaoException {
        user.setMoneyDeposited(new BigDecimal("20.00"));
        Transaction transaction = service.purchaseItem("A1");

        assertTrue(transaction.getHasChange());
        assertEquals(transaction.getAmountOfFiveDollars(), new BigDecimal("3"));
        assertEquals(transaction.getAmountOfOneDollars(), new BigDecimal("3"));
        assertEquals("Blah", transaction.getItembought().getNameOfItem());
    }

}
