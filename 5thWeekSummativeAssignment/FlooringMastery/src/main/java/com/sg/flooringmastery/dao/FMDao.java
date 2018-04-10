/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janie
 */
public interface FMDao {
    
    List<Order> getAllOrdersForDate(LocalDate ld);
    
    void addOrder(Order order);
    
    Order getOrderThroughDateAndNumber(LocalDate date, int orderNumber);
    
    void removeOrder(LocalDate date, int orderNumber);
    
    int getNextOrderNumber(LocalDate date);
    
    boolean getTaxVerification(Order order);
    
    Order getTax(Order order);
    
    Order getProduct(Order order);
    
    boolean getProductVerification(Order order);
    
    String initializeDao() throws FMDaoPersistenceException;
 
    void writeFMAllOrders() throws FMDaoPersistenceException;
    

}
