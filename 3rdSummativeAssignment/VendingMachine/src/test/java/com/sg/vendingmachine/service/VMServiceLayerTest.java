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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author janie
 */
public class VMServiceLayerTest {

    VMServiceLayer service;
    User user;
    VMDao dao;

    public VMServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao = new VMDaoStub();
        user = new User(new BigDecimal("10.00"));
        service = new VMServiceLayerImpl(user, dao);

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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVMInventory() throws Exception {
        assertEquals(3, service.getVMInventory().size());
    }

    /**
     * Test of purchaseItem method, of class VMServiceLayer.
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
  

    private class VMDaoStub implements VMDao {

        VMItem blah = new VMItem("A1", "Blah", new BigDecimal("2.00"), 5);
        VMItem blah2 = new VMItem("A2", "Blah1", new BigDecimal("3.00"), 2);
        VMItem blah3 = new VMItem("A3", "Blah2", new BigDecimal("15.00"), 1);
        Map<String, VMItem> VMItems = new HashMap<>();

        public VMDaoStub() {
            VMItems.put(blah.getLocationItem(), blah);
            VMItems.put(blah2.getLocationItem(), blah2);
            VMItems.put(blah3.getLocationItem(), blah3);
        }

        @Override
        public void writeVMInventory() throws VMInventoryDaoException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void loadVMInventory() throws VMInventoryDaoException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<VMItem> getInventory() {
            List<VMItem> inventory = new ArrayList<>(VMItems.values());
            return inventory;
        }

        @Override
        public VMItem getItemByLocation(String location) throws VMNoItemInventoryException {
            VMItem item = VMItems.get(location);
            return item;
        }

        @Override
        public void lowerItemQuantity(VMItem item) throws VMInventoryDaoException {
            item.setNumOfItems(item.getNumOfItems() - 1);
        }
    }
}
