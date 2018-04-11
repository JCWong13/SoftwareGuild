/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FMDao;
import com.sg.flooringmastery.dao.FMDaoPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FMInvalidOrderException;
import com.sg.flooringmastery.service.FMInvalidProductException;
import com.sg.flooringmastery.service.FMInvalidStateException;
import com.sg.flooringmastery.service.FMServiceLayer;
import com.sg.flooringmastery.ui.FMView;
import java.time.LocalDate;

/**
 *
 * @author janie
 */
public class FMController {

    FMDao dao;
    FMServiceLayer service;
    FMView view;
    Order order;
    Product product;
    Tax tax;

    public FMController(FMView view, FMServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public FMController(FMDao dao, FMServiceLayer service, FMView view, Order order, Product product, Tax tax) {
        this.dao = dao;
        this.service = service;
        this.view = view;
        this.order = order;
        this.product = product;
        this.tax = tax;
    }

    public void run() {
        String training = null;
        try {
            training = service.initializeDao();
        } catch (FMDaoPersistenceException e) {
            view.displayExceptionMessage(e);
        }

        do {
            boolean keepGoing = true;
            while (keepGoing) {

                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getAllOrders();
                        break;
                    case 2:
                        addFMOrder();
                        break;
                    case 3:
                        editFMOrder();
                        break;
                    case 4:
                        removeFMOrder();
                        break;
                    case 5:
                        saveFMOrders(training);
                        break;
                    case 6:
                        keepGoing = false;

                }
            }
        } while (view.displayFinalCheck().equalsIgnoreCase("no"));
        view.displayGoodBye();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void getAllOrders() {
        try {
            view.displayFinalAllOrders(service.getAllOrders(view.displayOrderMenu()));
        } catch (FMInvalidOrderException e) {
            view.displayExceptionMessage(e);
        }
        view.displayBackToMainMenu();
    }

    private void addFMOrder() {
        do {
            try {
                Order temporaryOrder = service.getProductAndStateVerification(view.displayAddOrderMenu());
                boolean userChoiceCommit = view.displayAddCommitChanges(temporaryOrder);
                if (userChoiceCommit) {
                    service.addOrder(temporaryOrder);
                }
            } catch (FMInvalidProductException | FMInvalidStateException e) {
                view.displayExceptionMessage(e);
            }
        } while (view.displayKeepAddingOrder().equalsIgnoreCase("yes"));
    }

    private void removeFMOrder() {
        LocalDate date = view.displayRemoveOrderMenu();
        int orderNumber = view.displayRemoveOrderNumber();
        try {
            boolean userAgreeRemove = view.displayRemoveConfirmation(service.orderDateAndNumberVerification(date, orderNumber)).equalsIgnoreCase("yes");
            if (userAgreeRemove) {
                service.removeOrder(date, orderNumber);
                view.displayRemoveSuccess();
            }
            view.displayBackToMainMenu();
        } catch (FMInvalidOrderException e) {
            view.displayExceptionMessage(e);
        }
    }

    private void editFMOrder() {
        LocalDate date = view.displayEditMenu();
        int orderNumber = view.displayEditOrderNumber();
        try {
            Order originalOrder = service.orderDateAndNumberVerification(date, orderNumber);
            Order transientOrder = view.displayEditConfirmation(originalOrder);
            transientOrder = service.getProductAndStateVerification(transientOrder);
            originalOrder.setCustomerName(transientOrder.getCustomerName());
            originalOrder.setStateInitials(transientOrder.getStateInitials());
            originalOrder.setProductType(transientOrder.getProductType());
            originalOrder.setArea(transientOrder.getArea());
            service.editOrder(originalOrder);
            view.displayEditSuccess();
            view.displayBackToMainMenu();
        } catch (FMInvalidOrderException | FMInvalidProductException | FMInvalidStateException e) {
            view.displayExceptionMessage(e);
        }
    }

    private void saveFMOrders(String training) {
        if (training.equalsIgnoreCase("training")) {
            view.displayEnvironment();
            view.displayBackToMainMenu();
        } else {
            try {
                service.saveFMOrders();
                view.displaySaveOrders();
                view.displayBackToMainMenu();
            } catch (FMDaoPersistenceException e) {
                view.displayExceptionMessage(e);
            }
        }
    }

}
