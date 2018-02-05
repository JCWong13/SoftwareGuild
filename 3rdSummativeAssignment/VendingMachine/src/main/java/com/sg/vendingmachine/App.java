/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VMController;
import com.sg.vendingmachine.dao.VMDao;
import com.sg.vendingmachine.dao.VMDaoImpl;
import com.sg.vendingmachine.dto.User;
import com.sg.vendingmachine.service.VMServiceLayer;
import com.sg.vendingmachine.service.VMServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VMView;
import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * @author janie
 */
public class App {
    public static void main(String[] args) {
    UserIO myIO = new UserIOConsoleImpl();
    VMView myView = new VMView(myIO);
    VMDao myDao = new VMDaoImpl("VMInventory.txt");
    User user = new User(new BigDecimal("20.00"));
    VMServiceLayer service = new VMServiceLayerImpl(user, myDao);
    VMController controller = new VMController(service, myView, user);
    controller.run();
    }
}
