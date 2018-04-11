/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

/**
 *
 * @author janie
 */
public class VMInventoryDaoException extends Exception {

    public VMInventoryDaoException(String message) {
        super(message);
    }
    
    public VMInventoryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
