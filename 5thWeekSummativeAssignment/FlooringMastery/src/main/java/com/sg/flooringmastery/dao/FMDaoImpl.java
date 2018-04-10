/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author janie
 */
public class FMDaoImpl implements FMDao {

    private Map<LocalDate, List<Order>> orders = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();

    public String collectionFileName;
    public static final String DELIMITER = ",";
    Scanner scanner;

    public FMDaoImpl() {

    }

    @Override
    public String initializeDao() throws FMDaoPersistenceException {
        try {
            scanner = new Scanner(new File("Data/Environment.txt"));
        } catch (FileNotFoundException e) {
            throw new FMDaoPersistenceException(
                    "Error: Could not load File", e);
        }
        String currentLine;
        String[] currentTokens;

        String training = scanner.nextLine();
        scanner.close();

        loadFMAllOrders();
        loadFMProducts();
        loadFMTaxes();
        return training;
    }

    private void loadFMProducts() throws FMDaoPersistenceException {
        try {
            scanner = new Scanner(new File("Data/Products.txt"));
        } catch (FileNotFoundException e) {
            throw new FMDaoPersistenceException(
                    "Error: Could not load File", e);
        }
        String currentLine;
        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType().toLowerCase(), currentProduct);
        }
        scanner.close();
    }

    private void loadFMTaxes() throws FMDaoPersistenceException {
        try {
            scanner = new Scanner(new File("Data/Taxes.txt"));
        } catch (FileNotFoundException e) {
            throw new FMDaoPersistenceException(
                    "Error: Could not load File", e);
        }
        String currentLine;
        String[] currentTokens;
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            taxes.put(currentTax.getStateInitials(), currentTax);
        }
        scanner.close();
    }

    private void loadFMAllOrders() throws FMDaoPersistenceException {
        File folder = new File("Data/Orders");
        File[] listOfFiles = folder.listFiles();
        for (File currentFile : listOfFiles) {
            loadFMOrdersForDay(currentFile);
        }
    }

    private void loadFMOrdersForDay(File file) throws FMDaoPersistenceException {
        List<Order> dayOrders = new ArrayList<>();
        LocalDate orderDate = LocalDate.parse(file.getName().replaceAll("[^0-9]", ""), DateTimeFormatter.ofPattern("MMddyyyy"));
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new FMDaoPersistenceException(
                    "Error: Could not load File", e);
        }
        String currentLine;
        String[] currentTokens;
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order(currentTokens[0]);
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setStateInitials(currentTokens[2]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(new BigDecimal(currentTokens[5]));
            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setCalculatedTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));
            dayOrders.add(currentOrder);
        }
        scanner.close();
        orders.put(orderDate, dayOrders);
    }

    @Override
    public List<Order> getAllOrdersForDate(LocalDate ld) {
        List<Order> ordersForGivenDay = orders.get(ld);
        return ordersForGivenDay;
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
    public boolean getProductVerification(Order order) {
        return products.containsKey(order.getProductType());
    }

    @Override
    public Order getProduct(Order order) {
        Product currentProduct = products.get(order.getProductType());
        order.setCostPerSquareFoot(currentProduct.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(currentProduct.getLaborCostPerSquareFoot());
        return order;
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
        List<Order> listOfOrdersForDate = orders.get(date);
        for (Iterator<Order> iterator = listOfOrdersForDate.iterator(); iterator.hasNext();) {
            Order order = iterator.next();
            if (order.getOrderNumber() == orderNumber) {
                iterator.remove();
            }
        }
        orders.put(date, listOfOrdersForDate);
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
        if (highestOrder.isPresent()) {
            Order highestRealOrder = highestOrder.get();
            return highestRealOrder.getOrderNumber() + 1;
        } else {
            return 1;
        }

    }

    @Override
    public void writeFMAllOrders() throws FMDaoPersistenceException {
        for (Entry<LocalDate, List<Order>> entry : orders.entrySet()) {
            String fileName = "Data/Orders/Orders_" + entry.getKey().format(DateTimeFormatter.ofPattern("MMddyyyy"));
            writeFMOrdersForDay(fileName, entry.getValue());
        }

    }

    private void writeFMOrdersForDay(String fileName, List<Order> listOfOrders) throws FMDaoPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FMDaoPersistenceException(
                    "Could not save data.", e);
        }
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCost,PerSquareFoot,"
                + "MaterialCost,LaborCost,Tax,Total");

        Comparator<Order> byOrderNumber = (Order o1, Order o2)
                -> o1.getOrderNumber() - (o2.getOrderNumber());
        listOfOrders.stream()
                .sorted(byOrderNumber)
                .forEach((i) -> {
                    out.println(i.getOrderNumber() + DELIMITER
                            + i.getCustomerName() + DELIMITER
                            + i.getStateInitials() + DELIMITER
                            + i.getTaxRate() + DELIMITER
                            + i.getProductType() + DELIMITER
                            + i.getArea() + DELIMITER
                            + i.getCostPerSquareFoot() + DELIMITER
                            + i.getLaborCostPerSquareFoot() + DELIMITER
                            + i.getMaterialCost() + DELIMITER
                            + i.getLaborCost() + DELIMITER
                            + i.getCalculatedTax() + DELIMITER
                            + i.getTotalCost());
                });
        out.flush();

        out.close();
    }

}
