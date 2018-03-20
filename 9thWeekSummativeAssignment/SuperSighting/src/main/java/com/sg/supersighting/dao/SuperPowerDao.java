/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
import java.util.List;

/**
 *
 * @author janie
 */
public interface SuperPowerDao {
    
    public void addSuperPower(SuperPower superPower);
    
    public SuperPower getSuperPowerByID(int superPowerId);
    
    public void updateSuperPower(SuperPower superPower);
            
    public void deleteSuperPower(int superPowerID);
    
    public List<SuperPower> getAllSuperPowers();
    
    public SuperPower getSuperPowerBySuperPersonID(int SuperPerson);
    
    public SuperPower getSuperPowerBySuperPowerName(String superPowerName);
    
}
