/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.dao.VMDao;
import com.sg.vendingmachinespringmvc.dao.VMInventoryDaoException;
import com.sg.vendingmachinespringmvc.dto.Transaction;
import com.sg.vendingmachinespringmvc.dto.User;
import com.sg.vendingmachinespringmvc.service.VMInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VMNoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VMServiceLayer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author janie
 */
@Controller
public class VendingMachineController {

    User user;
    VMDao dao;
    Transaction transaction;
    VMServiceLayer service;

    @Inject
    public VendingMachineController(VMServiceLayer service, User user) {
        this.service = service;
        this.user = user;
    }

    @GetMapping("/")
    public String getVMInventory(Model model) {
        try {
            if (transaction != null) {
                model.addAttribute("transactionChange", transaction.getTransactionChange());
                model.addAttribute("transactionMessage", transaction.getMessage());
            }
            model.addAttribute("userSelection", user.getUserSelection());
            model.addAttribute("userMoney", user.getMoneyDeposited().setScale(2, RoundingMode.HALF_UP));
            model.addAttribute("itemInventory", service.getVMInventory());

        } catch (VMInventoryDaoException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
    }

    @PostMapping("chooseItem/{locationItem}")
    public String chooseItem(@PathVariable("locationItem") String locationItem) {
        user.setUserSelection(locationItem);
        return "redirect:/";
    }

    @PostMapping("purchaseItem")
    public String purchaseItem() {
        if (user.getUserSelection() != null) {
            try {
                transaction = service.purchaseItem(user.getUserSelection());
                String change = "";

                if (transaction.getAmountOfQuarters().equals(BigDecimal.ZERO)
                        && transaction.getAmountOfNickels().equals(BigDecimal.ZERO)
                        && transaction.getAmountOfDimes().equals(BigDecimal.ZERO)
                        && transaction.getAmountOfPennies().equals(BigDecimal.ZERO)) {
                    transaction.setTransactionChange("No Change");

                } else {
                    if (!transaction.getAmountOfQuarters().equals(BigDecimal.ZERO)) {
                        change += transaction.getAmountOfQuarters().toString() + " Quarter(s) \n";
                    }
                    if (!transaction.getAmountOfDimes().equals(BigDecimal.ZERO)) {
                        change += transaction.getAmountOfDimes().toString() + " Dime(s) \n";
                    }
                    if (!transaction.getAmountOfNickels().equals(BigDecimal.ZERO)) {
                        change += transaction.getAmountOfNickels().toString() + " Nickel(s) \n";
                    }
                    if (!transaction.getAmountOfPennies().equals(BigDecimal.ZERO)) {
                        change += transaction.getAmountOfPennies().toString() + " Pennies";
                    }

                    transaction.setTransactionChange(change);
                }
                transaction.setMessage("Thank You!!!");

            } catch (VMInsufficientFundsException | VMNoItemInventoryException | VMInventoryDaoException e) {
                transaction = new Transaction(null);
                transaction.setMessage(e.getMessage());
            }
        }
        return "redirect:/";
    }

    @PostMapping("returnChange")
    public String returnChange() {
        transaction = new Transaction(null);
        if (!user.getMoneyDeposited().equals(BigDecimal.ZERO)) {
            transaction.setTransactionChange(service.getChange(transaction, user.getMoneyDeposited()));
        } else {
            transaction.setTransactionChange("No Change");
        }
        user.setMoneyDeposited(BigDecimal.ZERO);
        user.setUserSelection("");
        transaction.setMessage("");

        return "redirect:/";
    }

}
