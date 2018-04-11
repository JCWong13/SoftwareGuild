/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author janie
 */
public enum Currency {
    
    PENNY(new BigDecimal("0.01")),
    NICKEL(new BigDecimal("0.05")),
    DIME(new BigDecimal("0.10")),
    QUARTER(new BigDecimal("0.25")),
    DOLLAR(new BigDecimal("1.00")),
    FIVEDOLLARS(new BigDecimal("5.00"));

    private final BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }
    
    Currency(BigDecimal value) {
        this.value = value;
    }
    
    
}
