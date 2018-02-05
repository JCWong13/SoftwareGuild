/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VMInventoryDaoException;
import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.Transaction;
import com.sg.vendingmachine.dto.VMItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author janie
 */
public interface VMServiceLayer {

    BigDecimal calculateAddUserBalance(Currency userChoice);
    
    BigDecimal calculateDepositPurchaseBalance(Currency userchoice);
    
    List<VMItem> getVMInventory() throws VMInventoryDaoException;
    
    Transaction purchaseItem(String location);
    
    

}
