/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.service.SSServiceLayer;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author janie
 */
@CrossOrigin
@RestController
public class SuperPersonController {
    
    private SSServiceLayer layer;
    
    public SuperPersonController(SSServiceLayer layer) {
        this.layer=layer;
    }
    
    
    public void addSuperPerson(SuperPerson superPerson){
        
    }
    
    public void deleteSuperPerson(int superPersonId){
        
    }
    
    public void updateSuperPerson(SuperPerson superPerson){
        
    }
    
    public SuperPerson getSuperPersonById(int superPersonId){
        
    }
    
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID){
        
    }
    
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId){
        
    }
    
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId){
        
    }
    
    public List<SuperPerson> getAllSuperPeople(){
        
    }
    
}
