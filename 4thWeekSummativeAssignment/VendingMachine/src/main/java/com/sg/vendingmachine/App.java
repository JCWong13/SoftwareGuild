/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VMController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author janie
 */
public class App {

    public static void main(String[] args) {
//    UserIO myIO = new UserIOConsoleImpl();
//    VMView myView = new VMView(myIO);
//    VMDao myDao = new VMDaoImpl("VMInventory.txt");
//    User user = new User(new BigDecimal("20.00"));
//    VMServiceLayer service = new VMServiceLayerImpl(user, myDao);
//    VMController controller = new VMController(service, myView, user);
//    controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VMController controller = ctx.getBean("controller", VMController.class);
        controller.run();
    }
}
