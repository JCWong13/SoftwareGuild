/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FMDaoPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janie
 */
public interface FMServiceLayer {
    
    List<Order> getAllOrders(LocalDate ld) throws FMInvalidOrderException;
    
    void addOrder(Order order);
    
    void editOrder(Order order);
    
    Order getProductAndStateVerification(Order order) throws FMInvalidProductException, FMInvalidStateException;
    
    Order orderDateAndNumberVerification(LocalDate ld, int orderNumber) throws FMInvalidOrderException;
    
    void removeOrder(LocalDate ld, int orderNumber);
    
    String initializeDao() throws FMDaoPersistenceException;
    
    void saveFMOrders() throws FMDaoPersistenceException;
    
}
