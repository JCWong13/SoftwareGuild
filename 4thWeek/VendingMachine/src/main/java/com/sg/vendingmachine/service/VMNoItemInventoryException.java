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
public class VMNoItemInventoryException extends Exception {
    
    VMItem item;
    
    public VMNoItemInventoryException(String message, VMItem item) {
        super(message);
        this.item=item;
    }
    
    public VMNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "VMNoItemInventoryException: " + item.getNameOfItem();
    }
    
}
