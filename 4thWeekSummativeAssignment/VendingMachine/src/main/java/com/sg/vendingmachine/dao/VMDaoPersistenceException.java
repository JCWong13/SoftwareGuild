/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author janie
 */
public class VMDaoPersistenceException extends Exception {
        
    public VMDaoPersistenceException (String message) {
        super(message);
    }
    
    public VMDaoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
