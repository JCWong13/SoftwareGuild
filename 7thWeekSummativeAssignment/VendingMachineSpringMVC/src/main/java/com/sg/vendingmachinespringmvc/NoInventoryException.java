/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

/**
 *
 * @author janie
 */
public class NoInventoryException extends Exception {
    
    public NoInventoryException(String message) {
        super(message);
    }
    
}
