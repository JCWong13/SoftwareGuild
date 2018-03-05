/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VMItem;
import com.sg.vendingmachinespringmvc.service.VMNoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author janie
 */
public class VMDaoStub implements VMDao {

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
