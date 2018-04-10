/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author janie
 */
public class FMDaoStub implements FMDao {

    private Map<LocalDate, List<Order>> orders = new HashMap<>();
    private List<Order> ordersForDate;
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();

    public FMDaoStub() {
        Product product = new Product("carpet");
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        products.put(product.getProductType(), product);

        Product product2 = new Product("tile");
        product2.setCostPerSquareFoot(new BigDecimal("3.50"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        products.put(product2.getProductType(), product2);

        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));
        taxes.put(tax.getStateInitials(), tax);

        Tax tax2 = new Tax("PA");
        tax2.setTaxRate(new BigDecimal("6.75"));
        taxes.put(tax2.getStateInitials(), tax2);

    }

    public List<Order> getAllOrdersForDate(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrder(Order order) {
        if (orders.containsKey(LocalDate.now())) {
            List<Order> ordersForToday = orders.get(LocalDate.now());
            ordersForToday.add(order);
            orders.put(LocalDate.now(), ordersForToday);
        } else {
            List<Order> ordersForToday = new ArrayList<>();
            ordersForToday.add(order);
            orders.put(LocalDate.now(), ordersForToday);
        }
    }

    @Override
    public Order getOrderThroughDateAndNumber(LocalDate date, int orderNumber) {
        if (orders.containsKey(date)) {
            List<Order> listOfOrdersForSpecificDate = orders.get(date);
            for (Order currentOrder : listOfOrdersForSpecificDate) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    return currentOrder;
                }
            }
        }
        return null;
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextOrderNumber(LocalDate date) {
        List<Order> currentDay = orders.get(date);
        if (currentDay == null) {
            return 1;
        }
        Comparator<Order> byDate = (Order o1, Order o2)
                -> o1.getOrderNumber() - o2.getOrderNumber();
        Optional<Order> highestOrder = currentDay.stream()
                .max(byDate);
        Order highestRealOrder = highestOrder.get();
        return highestRealOrder.getOrderNumber() + 1;
    }

    @Override
    public boolean getTaxVerification(Order order) {
        return taxes.containsKey(order.getStateInitials());
    }

    @Override
    public Order getTax(Order order) {
        Tax currentTax = taxes.get(order.getStateInitials());
        order.setTaxRate(currentTax.getTaxRate());
        return order;
    }

    @Override
    public Order getProduct(Order order) {
        Product currentProduct = products.get(order.getProductType());
        order.setCostPerSquareFoot(currentProduct.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(currentProduct.getLaborCostPerSquareFoot());
        return order;
    }

    @Override
    public boolean getProductVerification(Order order) {
        return products.containsKey(order.getProductType());
    }

    @Override
    public void writeFMAllOrders() throws FMDaoPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String initializeDao() throws FMDaoPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
