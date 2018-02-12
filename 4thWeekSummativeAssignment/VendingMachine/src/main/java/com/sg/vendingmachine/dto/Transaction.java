/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author janie
 */
public class Transaction {
    
   BigDecimal amountOfFiveDollars;
   BigDecimal amountOfOneDollars;
   BigDecimal amountOfQuarters;
   BigDecimal amountOfDimes;
   BigDecimal amountOfNickels;
   BigDecimal amountOfPennies;
   VMItem itembought;
   Boolean hasChange;

    public Transaction(VMItem itembought) {
        this.itembought = itembought;
    }

    public Boolean getHasChange() {
        return hasChange;
    }

    public void setHasChange(Boolean hasChange) {
        this.hasChange = hasChange;
    }
   

    public BigDecimal getAmountOfFiveDollars() {
        return amountOfFiveDollars;
    }

    public void setAmountOfFiveDollars(BigDecimal amountOfFiveDollars) {
        this.amountOfFiveDollars = amountOfFiveDollars;
    }

    public BigDecimal getAmountOfOneDollars() {
        return amountOfOneDollars;
    }

    public void setAmountOfOneDollars(BigDecimal amountOfOneDollars) {
        this.amountOfOneDollars = amountOfOneDollars;
    }

    public BigDecimal getAmountOfQuarters() {
        return amountOfQuarters;
    }

    public void setAmountOfQuarters(BigDecimal amountOfQuarters) {
        this.amountOfQuarters = amountOfQuarters;
    }

    public BigDecimal getAmountOfDimes() {
        return amountOfDimes;
    }

    public void setAmountOfDimes(BigDecimal amountOfDimes) {
        this.amountOfDimes = amountOfDimes;
    }

    public BigDecimal getAmountOfNickels() {
        return amountOfNickels;
    }

    public void setAmountOfNickels(BigDecimal amountOfNickels) {
        this.amountOfNickels = amountOfNickels;
    }

    public BigDecimal getAmountOfPennies() {
        return amountOfPennies;
    }

    public void setAmountOfPennies(BigDecimal amountOfPennies) {
        this.amountOfPennies = amountOfPennies;
    }

    public VMItem getItembought() {
        return itembought;
    }

    public void setItembought(VMItem itembought) {
        this.itembought = itembought;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.amountOfFiveDollars);
        hash = 43 * hash + Objects.hashCode(this.amountOfOneDollars);
        hash = 43 * hash + Objects.hashCode(this.amountOfQuarters);
        hash = 43 * hash + Objects.hashCode(this.amountOfDimes);
        hash = 43 * hash + Objects.hashCode(this.amountOfNickels);
        hash = 43 * hash + Objects.hashCode(this.amountOfPennies);
        hash = 43 * hash + Objects.hashCode(this.itembought);
        hash = 43 * hash + Objects.hashCode(this.hasChange);
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
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.amountOfFiveDollars, other.amountOfFiveDollars)) {
            return false;
        }
        if (!Objects.equals(this.amountOfOneDollars, other.amountOfOneDollars)) {
            return false;
        }
        if (!Objects.equals(this.amountOfQuarters, other.amountOfQuarters)) {
            return false;
        }
        if (!Objects.equals(this.amountOfDimes, other.amountOfDimes)) {
            return false;
        }
        if (!Objects.equals(this.amountOfNickels, other.amountOfNickels)) {
            return false;
        }
        if (!Objects.equals(this.amountOfPennies, other.amountOfPennies)) {
            return false;
        }
        if (!Objects.equals(this.itembought, other.itembought)) {
            return false;
        }
        if (!Objects.equals(this.hasChange, other.hasChange)) {
            return false;
        }
        return true;
    }
    
    
}
