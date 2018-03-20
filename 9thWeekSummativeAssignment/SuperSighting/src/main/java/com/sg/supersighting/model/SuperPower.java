/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author janie
 */
public class SuperPower {
    private int superPowerID;
    private String superPowerName;
    

    public int getSuperPowerID() {
        return superPowerID;
    }

    public void setSuperPowerID(int superPowerID) {
        this.superPowerID = superPowerID;
    }

    public String getSuperPowerName() {
        return superPowerName;
    }

    public void setSuperPowerName(String superPowerName) {
        this.superPowerName = superPowerName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.superPowerID;
        hash = 67 * hash + Objects.hashCode(this.superPowerName);
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
        final SuperPower other = (SuperPower) obj;
        if (this.superPowerID != other.superPowerID) {
            return false;
        }
        if (!Objects.equals(this.superPowerName, other.superPowerName)) {
            return false;
        }
        return true;
    }




    
}
