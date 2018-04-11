/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VMDao;
import com.sg.vendingmachinespringmvc.dao.VMInventoryDaoException;
import com.sg.vendingmachinespringmvc.dto.Currency;
import com.sg.vendingmachinespringmvc.dto.Transaction;
import com.sg.vendingmachinespringmvc.dto.User;
import com.sg.vendingmachinespringmvc.dto.VMItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author janie
 */
public class VMServiceLayerImpl implements VMServiceLayer {

    User user;
    VMDao dao;

    public VMServiceLayerImpl(User user, VMDao dao) {
        this.user = user;
        this.dao = dao;
    }

    @Override
    public BigDecimal calculateAddUserBalance(Currency userChoice) {
        user.setUserBalance(user.getUserBalance().add(userChoice.getValue()));
        return user.getUserBalance();
    }

    @Override
    public BigDecimal calculateDepositPurchaseBalance(Currency userChoice) {
        user.setUserBalance(user.getUserBalance().subtract(userChoice.getValue()));
        user.setMoneyDeposited(user.getMoneyDeposited().add(userChoice.getValue()));
        return user.getUserBalance();
    }

    @Override
    public List<VMItem> getVMInventory() throws VMInventoryDaoException {
        return dao.getInventory();

    }

    @Override
    public Transaction purchaseItem(String location) throws VMInsufficientFundsException, VMNoItemInventoryException, VMInventoryDaoException {
       
            VMItem item = dao.getItemByLocation(location);
            BigDecimal itemCost = item.getCostOfItem();
            Transaction transaction = new Transaction(item);
            if (item.getNumOfItems() == 0) {
                throw new VMNoItemInventoryException("SOLD OUT!!!");
            }
            if (user.getMoneyDeposited().compareTo(itemCost) < 0) {
                BigDecimal changeDifference = item.getCostOfItem().subtract(user.getMoneyDeposited());
                throw new VMInsufficientFundsException("Please insert: " + getChange(transaction, changeDifference));
            }
            BigDecimal changeDifference = user.getMoneyDeposited().subtract(item.getCostOfItem());
            
            dao.lowerItemQuantity(item);

            user.setMoneyDeposited(new BigDecimal("0"));
            if (changeDifference.equals(BigDecimal.ZERO)) {
                return transaction;
            } else {
                transaction.setHasChange(true);
                user.setUserBalance(user.getUserBalance().add(changeDifference));
                BigDecimal[] changeArray = changeDifference.divideAndRemainder(Currency.FIVEDOLLARS.getValue());
                transaction.setAmountOfFiveDollars(changeArray[0]);
                changeArray = changeArray[1].divideAndRemainder(Currency.DOLLAR.getValue());
                transaction.setAmountOfOneDollars(changeArray[0]);
                changeArray = changeArray[1].divideAndRemainder(Currency.QUARTER.getValue());
                transaction.setAmountOfQuarters(changeArray[0]);
                changeArray = changeArray[1].divideAndRemainder(Currency.DIME.getValue());
                transaction.setAmountOfDimes(changeArray[0]);
                changeArray = changeArray[1].divideAndRemainder(Currency.NICKEL.getValue());
                transaction.setAmountOfNickels(changeArray[0]);
                changeArray = changeArray[1].divideAndRemainder(Currency.PENNY.getValue());
                transaction.setAmountOfPennies(changeArray[0]);
                
                return transaction;
            }
    
    }
    
    @Override
    public String getChange (Transaction transaction, BigDecimal changeDifference) {
        
        String change="";
                BigDecimal[] changeArray = changeDifference.divideAndRemainder(Currency.QUARTER.getValue());
                transaction.setAmountOfQuarters(changeArray[0]);
                changeArray=changeArray[1].divideAndRemainder(Currency.DIME.getValue());
                transaction.setAmountOfDimes(changeArray[0]);
                changeArray=changeArray[1].divideAndRemainder(Currency.NICKEL.getValue());
                transaction.setAmountOfNickels(changeArray[0]);
                changeArray=changeArray[1].divideAndRemainder(Currency.PENNY.getValue());
                transaction.setAmountOfPennies(changeArray[0]);
                
                if (!transaction.getAmountOfQuarters().equals(BigDecimal.ZERO)) {
                    change += transaction.getAmountOfQuarters().toString() + " Quarter(s) \n";
                }
                if (!transaction.getAmountOfDimes().equals(BigDecimal.ZERO)) {
                    change += transaction.getAmountOfDimes().toString() + " Dime(s) \n";
                }
                if (!transaction.getAmountOfNickels().equals(BigDecimal.ZERO)) {
                    change += transaction.getAmountOfNickels().toString() + " Nickel(s) \n";
                }
                if (!transaction.getAmountOfPennies().equals(BigDecimal.ZERO)) {
                    change += transaction.getAmountOfPennies().toString() + " Pennies";
                }
        
        return change;
    }
   
    
}


