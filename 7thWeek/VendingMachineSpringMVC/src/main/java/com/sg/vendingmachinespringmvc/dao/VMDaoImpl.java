/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VMItem;
import com.sg.vendingmachinespringmvc.service.VMNoItemInventoryException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author janie
 */
public class VMDaoImpl implements VMDao {

    private Map<String, VMItem> VMItems = new HashMap<>();
    public static String collectionFileName;
    public static final String DELIMITER = "::";
    Scanner scanner;
    
    public VMDaoImpl(String collectionFileName) {
        try {
            this.collectionFileName = collectionFileName;
            loadVMInventory();
        } catch (VMInventoryDaoException e) {
            System.out.println("blah");
        }
    }

    @Override
    public void loadVMInventory() throws VMInventoryDaoException {
        try {
            

            scanner = new Scanner(new File(getClass().getClassLoader().getResource(collectionFileName).getFile()));
        } catch (FileNotFoundException e) {
            throw new VMInventoryDaoException(
                    "-_- Could not load Vending Machine Inventory into Memory", e);
        }
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            VMItem currentVMItem = new VMItem(currentTokens[0]);
            currentVMItem.setNameOfItem(currentTokens[1]);
            currentVMItem.setCostOfItem(new BigDecimal(currentTokens[2]));
            currentVMItem.setNumOfItems(Integer.parseInt(currentTokens[3]));

            VMItems.put(currentVMItem.getLocationItem(), currentVMItem);
        }
        scanner.close();

    }

    @Override
    public void writeVMInventory() throws VMInventoryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(getClass().getClassLoader().getResource(collectionFileName).getFile()));
        } catch (IOException e) {
            throw new VMInventoryDaoException(
                    "Could not save VMInventory data.", e);
        }

        List<VMItem> VMItemList = new ArrayList<>(VMItems.values());
        Comparator<VMItem> byLocation = (VMItem o1, VMItem o2)
                -> o1.getLocationItem().compareTo(o2.getLocationItem());
        VMItemList.stream()
                .sorted(byLocation)
                .forEach((i) -> {
                  out.println(i.getLocationItem() + DELIMITER
                    + i.getNameOfItem() + DELIMITER
                    + i.getCostOfItem() + DELIMITER
                    + i.getNumOfItems());
                });

            out.flush();
        
            out.close();
    }



    @Override
    public List<VMItem> getInventory() {
        return VMItems.values()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public VMItem getItemByLocation(String location) throws VMNoItemInventoryException {
        VMItem item = VMItems.get(location);
        return item;
    }

    @Override
    public void lowerItemQuantity(VMItem item) throws VMInventoryDaoException {
        item.setNumOfItems(item.getNumOfItems() - 1);
        writeVMInventory();
    }

}
