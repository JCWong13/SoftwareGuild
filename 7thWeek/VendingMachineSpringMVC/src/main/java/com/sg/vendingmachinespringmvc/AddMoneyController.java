/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.dao.VMDao;
import com.sg.vendingmachinespringmvc.dto.Currency;
import com.sg.vendingmachinespringmvc.dto.User;
import com.sg.vendingmachinespringmvc.service.VMServiceLayer;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author janie
 */



@Controller
public class AddMoneyController {
    
User user;
VMDao dao;
VMServiceLayer service;

@Inject
public AddMoneyController(VMServiceLayer service) {
    this.service=service;
}
    
    @RequestMapping(value = "addDollar", method = RequestMethod.POST)
    public String addDollar() {
        service.calculateDepositPurchaseBalance(Currency.DOLLAR);
        return "redirect:/";
    }
    
    @RequestMapping(value = "addQuarter", method = RequestMethod.POST)
    public String addQuarter() {
        service.calculateDepositPurchaseBalance(Currency.QUARTER);
        return "redirect:/";
    }
    
    @RequestMapping(value = "addDime", method = RequestMethod.POST)
    public String addDime() {
        service.calculateDepositPurchaseBalance(Currency.DIME);
        return "redirect:/";
    }
    
    @RequestMapping(value = "addNickel", method = RequestMethod.POST)
    public String addNickel() {
        service.calculateDepositPurchaseBalance(Currency.NICKEL);
        return "redirect:/";
    }
    
}
