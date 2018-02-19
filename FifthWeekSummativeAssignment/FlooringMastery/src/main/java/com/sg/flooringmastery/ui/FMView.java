/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author janie
 */
public class FMView {

    FMUserIO io;

    public FMView(FMUserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("==< Flooring Program >==");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit Flooring Program");
        return io.readInt("Please enter a number from the Menu Options (1-6): ", 1, 6);
    }

    public LocalDate displayOrderMenu() {
        io.print("==< Display Orders >==");
        return io.readLocalDate("Which day would you like to view orders for?"
                + "\nPlease enter date in format: MM-dd-YYYY");

    }
    
    public void displayEnvironment(){
        io.print("==< Training Mode >==");
        io.print("In Training Mode.  Saving disabled.");
    }

    public void displayFinalAllOrders(List<Order> ordersForDate) {
        io.print("==< Display Orders >==");
        ordersForDate.forEach((currentOrder) -> {
            io.print(currentOrder.toString());
        });
    }

    public Order displayAddOrderMenu() {
        io.print("==< Add An Order >==");
        Order order = new Order();
        String orderName = io.readString("Please Enter the Customer's Name:");
        String stateInitals = io.readString("Please Enter the Customer's State initials (ex. OH): ");
        String productType = io.readString("Please Enter the Product Type the Customer Wishes to Purchase:").toLowerCase();
        BigDecimal area = io.readBigDecimal("Please Enter the Square Footage desired:");
        order.setCustomerName(orderName);
        order.setStateInitials(stateInitals);
        order.setProductType(productType);
        order.setArea(area);
        return order;
    }
    
    public boolean displayAddCommitChanges (Order order) {
        io.print("Customer's Name: " + order.getCustomerName());
        io.print("Customer's State Initials: " + order.getStateInitials());
        io.print(("Customer's Product Type: " + order.getProductType()));
        io.print(("Customer's Area: " + order.getArea()));
        String userChoice=io.readString("Do you want to Commit this Order?");
        return userChoice.equalsIgnoreCase("yes");
    }

    public String displayKeepAddingOrder() {
        return io.readString("Do you want to Keep Adding an Order for Today("
                + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-YYYY"))
                + ")?");
    }
    

    public void displayEditOrderMenu() {
        io.print("==< Edit An Order >==");
    }

    public LocalDate displayRemoveOrderMenu() {
        io.print("==< Remove An Order >==");
        LocalDate ld = io.readLocalDate("What is the Date of the Order that will be Removed?"
                + "\nPlease enter date in format: MM-dd-yyyy");
        return ld;
    }

    public int displayRemoveOrderNumber() {
        int userOrderChoice = io.readInt("What is the Order Number You Would like to Remove?");
        return userOrderChoice;
    }

    public LocalDate displayEditMenu() {
        io.print("==< Edit An Order >==");
        LocalDate ld = io.readLocalDate("What is the date of the Order that will be Edited? "
                + "\nPlease enter date in format: MM-dd-yyyy");
        return ld;
    }

    public int displayEditOrderNumber() {
        int userOrderChoice = io.readInt("What is the Order Number You Would like to Edit?");
        return userOrderChoice;
    }

    public void displaySaveOrders() {
        io.print("==< Save Orders >==");
        io.print("All Orders Successfully Saved!");
    }

    public void displayBackToMainMenu() {
        io.readString("Please Press Enter to Return to the Main Menu.");
    }

    public String displayRemoveConfirmation(Order order) {
        io.print(order.toString());
        return io.readString("Are you sure you want to remove this order?");
    }

    public Order displayEditConfirmation(Order order) {
        io.print("Please press Enter to Skip Editing Order Information");
        Order transientOrder = new Order();
        io.print("Order Number: " + order.getOrderNumber());
        String zero = io.readString("Please Enter the Customer's Name (" + order.getCustomerName() + "): ");
        String one = io.readString("Please Enter the Customer's State initials (" + order.getStateInitials() + "): ");
        String two = io.readString("Please Enter the Product Type the Customer Wishes to Purchase (" + order.getProductType() + "): ").toLowerCase();
        String three = io.readString("Please Enter the Square Footage desired (" + order.getArea() + "): ");

        String[] userVariables = {zero, one, two, three};
        transientOrder.setCustomerName(order.getCustomerName());
        transientOrder.setStateInitials(order.getStateInitials());
        transientOrder.setProductType(order.getProductType());
        transientOrder.setArea(order.getArea());

        for (int i = 0; i < userVariables.length; i++) {
            if (!userVariables[i].matches("\\s*")) {
                switch (i) {
                    case 0:
                        transientOrder.setCustomerName(userVariables[0]);
                        break;
                    case 1:
                        transientOrder.setStateInitials(userVariables[1]);
                        break;
                    case 2:
                        transientOrder.setProductType(userVariables[2]);
                        break;
                    case 3:
                        transientOrder.setArea(new BigDecimal(userVariables[3]));
                        break;
                }
            }
        }
        return transientOrder;
    }

    public void displayRemoveSuccess() {
        io.print("==< Order Successfully Removed! >==");
    }

    public void displayEditSuccess() {
        io.print("==< Order Successfully Edited! >==");
    }

    public String displayFinalCheck() {
        String response = io.readString("Did you remember to save your work? "
                + "\nAre you sure you want to quit the Flooring Program? Yes or No?");
        return response;
    }

    public void displayExceptionMessage(Exception e) {
        io.print(e.getMessage());
    }

    public void displayGoodBye() {
        io.print("==< Goodbye! >==");
    }

}
