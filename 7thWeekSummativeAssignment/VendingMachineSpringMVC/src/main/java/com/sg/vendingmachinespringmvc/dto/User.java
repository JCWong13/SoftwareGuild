/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author janie
 */
public class User {
    
    String userSelection;
    BigDecimal userBalance;
    BigDecimal moneyDeposited;
    

    public User(BigDecimal userBalance) {
        this.userBalance = userBalance;
        moneyDeposited= new BigDecimal("0.00");
    }
    
    
    public String getUserSelection() {
        return userSelection;
    }

    public void setUserSelection(String userSelection) {
        this.userSelection = userSelection;
    }
    
    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public BigDecimal getMoneyDeposited() {
        return moneyDeposited;
    }

    public void setMoneyDeposited(BigDecimal moneyDeposited) {
        this.moneyDeposited = moneyDeposited;
    }
    

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.userBalance);
        hash = 29 * hash + Objects.hashCode(this.moneyDeposited);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userBalance, other.userBalance)) {
            return false;
        }
        if (!Objects.equals(this.moneyDeposited, other.moneyDeposited)) {
            return false;
        }
        return true;
    }

    
    
    
}
