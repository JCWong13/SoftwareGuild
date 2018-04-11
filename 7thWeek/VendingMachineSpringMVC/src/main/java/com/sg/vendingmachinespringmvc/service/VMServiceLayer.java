/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VMInventoryDaoException;
import com.sg.vendingmachinespringmvc.dto.Currency;
import com.sg.vendingmachinespringmvc.dto.Transaction;
import com.sg.vendingmachinespringmvc.dto.VMItem;
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

    Transaction purchaseItem(String location) throws VMInsufficientFundsException, VMNoItemInventoryException, VMInventoryDaoException;
    
    String getChange(Transaction transaction, BigDecimal changeDifference);

}
