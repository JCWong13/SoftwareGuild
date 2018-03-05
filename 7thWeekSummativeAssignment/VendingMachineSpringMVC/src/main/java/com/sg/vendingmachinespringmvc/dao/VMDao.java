/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VMItem;
import com.sg.vendingmachinespringmvc.service.VMNoItemInventoryException;
import java.util.List;

/**
 *
 * @author janie
 */
public interface VMDao {
    
    void writeVMInventory() throws VMInventoryDaoException;
    
    void loadVMInventory() throws VMInventoryDaoException;
    
    List<VMItem> getInventory();
    
    VMItem getItemByLocation(String location) throws VMNoItemInventoryException;
    
    void lowerItemQuantity(VMItem item) throws VMInventoryDaoException;
    
}
