/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author janie
 */
public class FMInvalidStateException extends Exception {
    
    public FMInvalidStateException (String message) {
        super(message);
    }
    
    public FMInvalidStateException (String message, Throwable cause) {
        super(message, cause);
    }
    
}
