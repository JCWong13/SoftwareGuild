/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VMInventoryDaoException;
import com.sg.vendingmachine.dto.Currency;
import com.sg.vendingmachine.dto.Transaction;
import com.sg.vendingmachine.dto.User;
import com.sg.vendingmachine.service.VMInsufficientFundsException;
import com.sg.vendingmachine.service.VMNoItemInventoryException;
import com.sg.vendingmachine.service.VMServiceLayer;
import com.sg.vendingmachine.ui.VMView;
import java.math.RoundingMode;

/**
 *
 * @author janie
 */
public class VMController {

    VMServiceLayer service;
    VMView view;
    User user;

    public VMController(VMServiceLayer service, VMView view, User user) {
        this.service = service;
        this.view = view;
        this.user = user;
    }

    public void run() {
        boolean keepGoing = true;

        try {
            while (keepGoing) {
                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        addBalance();
                        break;
                    case 3:
                        showVMInventory();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommand();
                }
            }

        } catch (VMInventoryDaoException e) {
            view.displayGoodByeMessage();
        }
    }

    private void checkBalance() {
        view.displayCheckBalance(user.getUserBalance());
        view.displayBackToMainMenu();
    }

    private void addBalance() {
        view.displayAddBalance();

        do {
            int choice = view.displayAddMoney();

            switch (choice) {
                case 1:
                    calculateUserBalance(Currency.PENNY);
                    break;
                case 2:
                    calculateUserBalance(Currency.NICKEL);
                    break;
                case 3:
                    calculateUserBalance(Currency.DIME);
                    break;
                case 4:
                    calculateUserBalance(Currency.QUARTER);
                    break;
                case 5:
                    calculateUserBalance(Currency.DOLLAR);
                    break;
                case 6:
                    calculateUserBalance(Currency.FIVEDOLLARS);
                    break;
                default:
                    view.displaySwitchDefault();
            }
        } while (view.displayAskUserAddMoneyChoice().equalsIgnoreCase("yes"));
        view.displayBackToMainMenu();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void calculateUserBalance(Currency currency) {
        view.displayCalculateUserBalance(user.getUserBalance().setScale(2, RoundingMode.HALF_UP).toString(),
                service.calculateAddUserBalance(currency));
    }

    private void calculatePurchaseBalance(Currency currency) {
        view.displayCalculatePurchaseBalance(user.getUserBalance().setScale(2, RoundingMode.HALF_UP).toString(),
                service.calculateDepositPurchaseBalance(currency), currency.getValue(), user.getMoneyDeposited());
    }

    private void purchaseItem() {
        try {
            view.displayVMInventory(service.getVMInventory());
            String itemLocation = view.displayPurchaseChoice();

            Transaction transaction = service.purchaseItem(itemLocation);

            if (transaction != null) {
                view.displaySuccessfulItemPurchase(transaction);
                view.displayCheckBalance(user.getUserBalance());
            }
        } catch (VMInsufficientFundsException | VMNoItemInventoryException | VMInventoryDaoException e) {
            view.displayExceptionMessage(e);
        }

    }

    private void showVMInventory() throws VMInventoryDaoException {
        view.displayVMInventory(service.getVMInventory());
        boolean go = true;

        do {
            int choice = view.displayVMChoice();
            if (choice == 8) {
                break;
            }
            switch (choice) {
                case 1:
                    calculatePurchaseBalance(Currency.PENNY);
                    break;
                case 2:
                    calculatePurchaseBalance(Currency.NICKEL);
                    break;
                case 3:
                    calculatePurchaseBalance(Currency.DIME);
                    break;
                case 4:
                    calculatePurchaseBalance(Currency.QUARTER);
                    break;
                case 5:
                    calculatePurchaseBalance(Currency.DOLLAR);
                    break;
                case 6:
                    calculatePurchaseBalance(Currency.FIVEDOLLARS);
                    break;
                case 7:
                    purchaseItem();
                    break;
                case 8:
                    go = false;
                    break;
                default:
                    view.displayUnknownCommand();

            }

        } while (view.displayAgreeChoice().equalsIgnoreCase("yes") && go == true);
        view.displayBackToMainMenu();
    }
}
