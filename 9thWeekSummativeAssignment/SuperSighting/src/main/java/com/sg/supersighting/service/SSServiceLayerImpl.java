/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.service;

import com.sg.supersighting.dao.LocationDao;
import com.sg.supersighting.dao.OrganizationDao;
import com.sg.supersighting.dao.SightingDao;
import com.sg.supersighting.dao.SuperPersonDao;
import com.sg.supersighting.dao.SuperPowerDao;
import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.Organization;
import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author janie
 */
public class SSServiceLayerImpl implements SSServiceLayer {
    
    private LocationDao locationDao;
    private OrganizationDao orgDao;
    private SightingDao sightDao;
    private SuperPersonDao personDao;
    private SuperPowerDao powerDao;
    
    @Inject
    public SSServiceLayerImpl(LocationDao locationDao, OrganizationDao orgDao, SightingDao sightDao, SuperPersonDao personDao, SuperPowerDao powerDao){
        this.locationDao= locationDao;
        this.orgDao = orgDao;
        this.sightDao=sightDao;
        this.personDao=personDao;
        this.powerDao=powerDao;
    }
    
    @Override
    public void addSuperPower(SuperPower superPower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperPower getSuperPowerByID(int superPowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperPower(SuperPower superPower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPower(int superPowerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public SuperPower getSuperPowerBySuperPersonID(int SuperPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperPower getSuperPowerBySuperPowerName(String superPowerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization getOrganizationByID(int OrganizationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrganization(int organizationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location getLocationByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLocation(int locationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getAllLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getAllLocationsBySuperPersonID(int superPersonID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSightingByID(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSighting(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsForDate(LocalDate localDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSuperPerson(SuperPerson superPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPerson(int superPersonId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPerson> getAllSuperPeople() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
