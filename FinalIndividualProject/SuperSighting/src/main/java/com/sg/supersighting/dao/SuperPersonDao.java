/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.SuperPerson;
import java.util.List;

/**
 *
 * @author janie
 */
public interface SuperPersonDao {
    
    public void addSuperPerson(SuperPerson superPerson);
    
    public void deleteSuperPerson(int superPersonId);
    
    public void updateSuperPerson(SuperPerson superPerson);
    
    public SuperPerson getSuperPersonById(int superPersonId);
    
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID);
    
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId);
    
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId);
    
    public List<SuperPerson> getAllSuperPeople();
    
}
