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
public class FMInvalidProductException extends Exception {
    
    public FMInvalidProductException (String message) {
        super(message);
    }
    
    public FMInvalidProductException (String message, Throwable cause){
        super(message, cause);
    }
    
}
