/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Organization;
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
public class OrganizationController {
    
    private SSServiceLayer layer;
    
    public OrganizationController(SSServiceLayer layer){
        this.layer=layer;
    }
    
    
    public void addOrganization(Organization organization){
        
    }

    public Organization getOrganizationByID(int OrganizationID){
        
    }

    public void updateOrganization(Organization organization){
        
    }

    public void deleteOrganization(int organizationID){
        
    }

    public List<Organization> getAllOrganizations(){
        
    }
    
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID){
        
    }
    
}
