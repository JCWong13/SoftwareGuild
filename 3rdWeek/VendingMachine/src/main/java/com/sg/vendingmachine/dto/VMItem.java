/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author janie
 */
public class VMItem {

    private String locationItem;
    private String nameOfItem;
    private BigDecimal costOfItem;
    private int numOfItems;

    public VMItem(String locationItem, String nameOfItem, BigDecimal costOfItem, int numOfItems) {
        this.locationItem = locationItem;
        this.nameOfItem = nameOfItem;
        this.costOfItem = costOfItem;
        this.numOfItems = numOfItems;
    }

    public VMItem(String locationItem) {
        this.locationItem = locationItem;
    }

    public String getLocationItem() {
        return locationItem;
    }

    public void setLocationItem(String locationItem) {
        this.locationItem = locationItem;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public BigDecimal getCostOfItem() {
        return costOfItem;
    }

    public void setCostOfItem(BigDecimal costOfItem) {
        this.costOfItem = costOfItem;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nameOfItem);
        hash = 97 * hash + Objects.hashCode(this.costOfItem);
        hash = 97 * hash + this.numOfItems;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VMItem other = (VMItem) obj;
        if (this.numOfItems != other.numOfItems) {
            return false;
        }
        if (!Objects.equals(this.nameOfItem, other.nameOfItem)) {
            return false;
        }
        if (!Objects.equals(this.costOfItem, other.costOfItem)) {
            return false;
        }
        return true;
    }

}
