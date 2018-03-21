/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.service;

import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.Organization;
import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janie
 */
public interface SSServiceLayer {
    //SUPERPOWER METHODS
    public void addSuperPower(SuperPower superPower);
    
    public SuperPower getSuperPowerByID(int superPowerId);
    
    public void updateSuperPower(SuperPower superPower);
            
    public void deleteSuperPower(int superPowerID);
    
    public List<SuperPower> getAllSuperPowers();
    
    public SuperPower getSuperPowerBySuperPersonID(int SuperPerson);
    
    public SuperPower getSuperPowerBySuperPowerName(String superPowerName);
    
    //ORGANIZATION METHODS   
    public void addOrganization(Organization organization);

    public Organization getOrganizationByID(int OrganizationID);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(int organizationID);

    public List<Organization> getAllOrganizations();
    
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID);
    
    //LOCATION METHODS
    public void addLocation(Location location);

    public Location getLocationByID(int id);

    public void updateLocation(Location location);

    public void deleteLocation(int locationID);

    public List<Location> getAllLocations();
    
    public List<Location> getAllLocationsBySuperPersonID(int superPersonID);
    
    //SIGHTING METHODS
    public void addSighting(Sighting sighting);

    public Sighting getSightingByID(int sightingId);

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsForDate(LocalDate localDate);
    
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId);
    
    //SUPERPERSON METHODS
    public void addSuperPerson(SuperPerson superPerson);
    
    public void deleteSuperPerson(int superPersonId);
    
    public void updateSuperPerson(SuperPerson superPerson);
    
    public SuperPerson getSuperPersonById(int superPersonId);
    
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID);
    
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId);
    
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId);
    
    public List<SuperPerson> getAllSuperPeople();
    
}
