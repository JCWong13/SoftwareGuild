/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.VMItem;

/**
 *
 * @author janie
 */
public class VMInsufficientFundsException extends Exception {
    
    VMItem item;

    public VMInsufficientFundsException(String message, VMItem item) {
        super(message);
        this.item=item;
    }

    public VMInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "VMInsufficientFundsException: " + item.getNameOfItem();
    }

}
