/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Organization;
import com.sg.supersighting.service.SSServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author janie
 */
@CrossOrigin
@RestController
public class OrganizationController {
    
    private SSServiceLayer layer;
    
    @Inject
    public OrganizationController(SSServiceLayer layer){
        this.layer=layer;
    }
    
    @PostMapping("/Organization")
    @ResponseStatus(HttpStatus.CREATED)
    public Organization addOrganization(@Valid @RequestBody Organization organization){
        for(Organization currentOrg: layer.getAllOrganizations()){
            if(currentOrg.getOrganizationName().equalsIgnoreCase(organization.getOrganizationName())){
                throw new OrganizationAddException("Error: That Organization already exists.");
            }
        }
            layer.addOrganization(organization);
            return layer.getOrganizationByID(organization.getOrganizationID());
    }
    
    @GetMapping("/Organization/{id}")
    public Organization getOrganizationByID(@PathVariable("id") Integer organizationID){
        return layer.getOrganizationByID(organizationID);
    }

    @PutMapping("/Organization/{id}")
    public Organization updateOrganization(@PathVariable("id") Integer id, @Valid @RequestBody Organization organization) throws Exception{
        
        if(id != organization.getOrganizationID()) {
            throw new Exception("Organization does not match inputted ID");
        } 
        layer.updateOrganization(organization);
        return layer.getOrganizationByID(organization.getOrganizationID());
    }

    @DeleteMapping("/Organization/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") Integer organizationID){
        layer.deleteOrganization(organizationID);
    }

    @GetMapping("/Organizations")
    public List<Organization> getAllOrganizations(){
        return layer.getAllOrganizations();
    }
    
    @GetMapping("/Organizations/{id}")
    public List<Organization> getAllOrganizationsBySuperPerson(@PathVariable("id") Integer superPersonID){
        return layer.getAllOrganizationsBySuperPerson(superPersonID);
    }
    
    public class OrganizationAddException extends RuntimeException {
        public OrganizationAddException(String message) {
            super(message);
        }
    }
}
