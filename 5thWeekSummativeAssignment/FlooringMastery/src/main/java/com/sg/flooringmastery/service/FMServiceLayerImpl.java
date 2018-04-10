/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FMDao;
import com.sg.flooringmastery.dao.FMDaoPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author janie
 */
public class FMServiceLayerImpl implements FMServiceLayer {

    FMDao dao;

    public FMServiceLayerImpl(FMDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> getAllOrders(LocalDate ld) throws FMInvalidOrderException {
        if (dao.getAllOrdersForDate(ld) == null) {
            throw new FMInvalidOrderException("That is not a Valid Order Date: " + ld.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        }
        return dao.getAllOrdersForDate(ld);
    }

    @Override
    public String initializeDao() throws FMDaoPersistenceException {
        return dao.initializeDao();
    }

    @Override
    public Order getProductAndStateVerification(Order order) throws FMInvalidProductException, FMInvalidStateException {
        if (!dao.getProductVerification(order)) {
            throw new FMInvalidProductException("That is not a current product sold: " + order.getProductType());
        }
        if (!dao.getTaxVerification(order)) {
            throw new FMInvalidStateException("Those are not valid State initials: " + order.getStateInitials());
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        dao.getProduct(order);
        dao.getTax(order);
        order.setOrderNumber(dao.getNextOrderNumber(LocalDate.now()));
        order.setMaterialCost(order.getArea().multiply(order.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost((order.getArea().multiply(order.getLaborCostPerSquareFoot())).setScale(2, RoundingMode.HALF_UP));
        order.setCalculatedTax(order.getTaxRate().movePointLeft(2).multiply(order.getMaterialCost().add(order.getLaborCost())).setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getCalculatedTax().add(order.getMaterialCost().add(order.getLaborCost())).setScale(2, RoundingMode.HALF_UP));
        dao.addOrder(order);
    }

    @Override
    public void editOrder(Order order) {
        dao.getProduct(order);
        dao.getTax(order);
        order.setMaterialCost(order.getArea().multiply(order.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost((order.getArea().multiply(order.getLaborCostPerSquareFoot())).setScale(2, RoundingMode.HALF_UP));
        order.setCalculatedTax(order.getTaxRate().movePointLeft(2).multiply(order.getMaterialCost().add(order.getLaborCost())).setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getCalculatedTax().add(order.getMaterialCost().add(order.getLaborCost())).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public Order orderDateAndNumberVerification(LocalDate ld, int orderNumber) throws FMInvalidOrderException {
        if (dao.getOrderThroughDateAndNumber(ld, orderNumber) == null) {
            throw new FMInvalidOrderException("That is not a valid Order Date/Number " + ld.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ", " + orderNumber);
        }
        return dao.getOrderThroughDateAndNumber(ld, orderNumber);
    }

    @Override
    public void removeOrder(LocalDate ld, int orderNumber) {
        dao.removeOrder(ld, orderNumber);
    }

    @Override
    public void saveFMOrders() throws FMDaoPersistenceException {
        dao.writeFMAllOrders();
    }

}
