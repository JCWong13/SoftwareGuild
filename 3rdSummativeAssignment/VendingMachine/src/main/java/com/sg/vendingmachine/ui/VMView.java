/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VMDao;
import com.sg.vendingmachine.dto.Transaction;
import com.sg.vendingmachine.dto.VMItem;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author janie
 */
public class VMView {

    UserIO io;
    VMDao dao;

    public VMView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("~*~* Vending Machine Menu *~*~");
        io.print("1. Check Balance");
        io.print("2. Add Money");
        io.print("3. View Vending Machine");
        io.print("4. Exit Vending Machine");

        return io.readInt("Please choose from the above options.", 1, 4);
    }

    public void displayCheckBalance(BigDecimal balance) {
        io.print("~*~* Your Balance *~*~");
        io.print("Balance: $" + balance);

    }

    public void displayAddBalance() {
        io.print("~*~* Add Balance *~*~");
        io.print("How much Money would you like to add to your Balance?");
    }

    public String displayAskUserAddMoneyChoice() {
        return io.readString("Would you like to add any other money?");
    }

    public void displaySwitchDefault() {
        System.out.println("Please pick a number from 1-5.");
    }

    public void displayBackToMainMenu() {
        io.readString("Please hit Enter to Return to Main Menu");
    }
    
    public void displayUnknownCommand() {
        io.print("Unknown Command");
    }

    public void displayVMInventory(List<VMItem> inventory) {
        Comparator<VMItem> byLocation = (VMItem o1, VMItem o2)
                -> o1.getLocationItem().compareTo(o2.getLocationItem());
        inventory.stream()
                .sorted(byLocation)
                .forEach((i) -> {
                    if(i.getNumOfItems()!=0) {
                    System.out.println("Location: " + i.getLocationItem()
                            + " <> Name of Item: " + i.getNameOfItem()
                            + " <> Cost of Object: " + i.getCostOfItem()
                            + " <> Number of Items Remaining: " + i.getNumOfItems());
                    } });
    }

    public int displayVMChoice() {
        io.print("1. Deposit Worthless Penny");
        io.print("2. Deposit Stanley Nickel");
        io.print("3. Deposit Dime");
        io.print("4. Deposit Quarter");
        io.print("5. Deposit Dwight Schrute Buck");
        io.print("6. Deposit Five Dollar Foot Long");
        io.print("7. Purchase an Item");
        io.print("8. Back to Main Menu");
        int choice = Integer.parseInt(io.readString("\nPlease pick a number between 1 and 8."));
        return choice;
    }

    public String displayAgreeChoice() {
        String agree = io.readString("Would you like to continue using the Vending Machine? "
                + "\nPlease enter yes or no.");
        return agree;
    }

    public String displayPurchaseChoice() {
        String item = io.readString("What item would you like to buy? Please enter Item Location.");
        return item;
    }

    public int displayAddMoney() {
        io.print("1. Add Worthless Penny to Balance");
        io.print("2. Add Stanley Nickel to Balance");
        io.print("3. Add Dime to Balance");
        io.print("4. Add Quarter to Balance");
        io.print("5. Add Dwight Schrute Buck to Balance");
        io.print("6. Add Five Dollar Foot Long to Balance");
        int choice = Integer.parseInt(io.readString("Please pick a number between 1 and 6."));
        return choice;
    }

    public void displayCalculateUserBalance(String balance, BigDecimal userChoice) {
        io.print("User Balance: $" + balance);
        io.print("Current Balance: $" + userChoice);
    }

    public void displayCalculatePurchaseBalance(String balance, BigDecimal userChoice, BigDecimal amountDeposited, BigDecimal amountInMachine) {
        io.print("User Balance: $" + balance);
        io.print("Remaining Balance: $" + userChoice);
        io.print("Amount Deposited: $" + amountDeposited);
        io.print("Amount in Machine: $" + amountInMachine);
    }

    public void displaySuccessfulItemPurchase(Transaction transaction) {
        io.print("You've successfully bought " + transaction.getItembought().getNameOfItem());
        if (transaction.getHasChange()) {
            io.print("Vending Machine returns:");
            if (!transaction.getAmountOfFiveDollars().equals(BigDecimal.ZERO)) {
                io.print("Five Dollar Bills: " + transaction.getAmountOfFiveDollars());
            }
            if (!transaction.getAmountOfOneDollars().equals(BigDecimal.ZERO)) {
                io.print("One Dollar Bills: " + transaction.getAmountOfOneDollars());
            }
            if (!transaction.getAmountOfQuarters().equals(BigDecimal.ZERO)) {
                io.print("Quarters: " + transaction.getAmountOfQuarters());
            }
            if (!transaction.getAmountOfDimes().equals(BigDecimal.ZERO)) {
                io.print("Dimes: " + transaction.getAmountOfDimes());
            }
            if (!transaction.getAmountOfNickels().equals(BigDecimal.ZERO)) {
                io.print("Nickels: " + transaction.getAmountOfNickels());
            }
            if (!transaction.getAmountOfPennies().equals(BigDecimal.ZERO)) {
                io.print("Pennies: " + transaction.getAmountOfPennies());
            }

        }
    }

}
