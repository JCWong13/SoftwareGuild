/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Organization;
import java.util.List;

/**
 *
 * @author janie
 */
public interface OrganizationDao {
    
    public void addOrganization(Organization organization);

    public Organization getOrganizationByID(int OrganizationID);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(int organizationID);

    public List<Organization> getAllOrganizations();
    
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID);
    
}
