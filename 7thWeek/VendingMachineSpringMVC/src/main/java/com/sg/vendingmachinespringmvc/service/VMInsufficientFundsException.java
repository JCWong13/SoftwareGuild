/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dto.Transaction;
import com.sg.vendingmachinespringmvc.dto.VMItem;

/**
 *
 * @author janie
 */
public class VMInsufficientFundsException extends Exception {

    public VMInsufficientFundsException(String message) {
        super(message);
    }

    public VMInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
   

}
