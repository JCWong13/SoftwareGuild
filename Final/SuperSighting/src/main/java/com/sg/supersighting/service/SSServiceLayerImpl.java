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
    public SSServiceLayerImpl(LocationDao locationDao, OrganizationDao orgDao, SightingDao sightDao, SuperPersonDao personDao, SuperPowerDao powerDao) {
        this.locationDao = locationDao;
        this.orgDao = orgDao;
        this.sightDao = sightDao;
        this.personDao = personDao;
        this.powerDao = powerDao;
    }

    @Override
    public void addSuperPower(SuperPower superPower) {
        powerDao.addSuperPower(superPower);
    }

    @Override
    public SuperPower getSuperPowerByID(int superPowerId) {
        return powerDao.getSuperPowerByID(superPowerId);
    }

    @Override
    public void updateSuperPower(SuperPower superPower) {
        powerDao.updateSuperPower(superPower);
    }

    @Override
    public void deleteSuperPower(int superPowerID) {
        powerDao.deleteSuperPower(superPowerID);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return powerDao.getAllSuperPowers();
    }

    //These methods not being used in the controller
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
        orgDao.addOrganization(organization);
    }

    @Override
    public Organization getOrganizationByID(int OrganizationID) {
        return orgDao.getOrganizationByID(OrganizationID);
    }

    @Override
    public void updateOrganization(Organization organization) {
        orgDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationID) {
        orgDao.deleteOrganization(organizationID);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return orgDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID) {
        return orgDao.getAllOrganizationsBySuperPerson(superPersonID);
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public Location getLocationByID(int id) {
        return locationDao.getLocationByID(id);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public void deleteLocation(int locationID) {
        locationDao.deleteLocation(locationID);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Location> getAllLocationsBySuperPersonID(int superPersonID) {
        return locationDao.getAllLocationsBySuperPersonID(superPersonID);
    }

    @Override
    public void addSighting(Sighting sighting) {
        sightDao.addSighting(sighting);
    }

    @Override
    public Sighting getSightingByID(int sightingId) {
        return sightDao.getSightingByID(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightDao.updateSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingID) {
        sightDao.deleteSighting(sightingID);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightDao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsForDate(LocalDate localDate) {
        return sightDao.getAllSightingsForDate(localDate);
    }

    @Override
    public List<Sighting> getLastTenSightings() {
        return sightDao.getLastTenSightings();
    }

    @Override
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId) {
        return sightDao.getAllSightingsBySuperPersonID(superPersonId);
    }

    @Override
    public void addSuperPerson(SuperPerson superPerson) {
        personDao.addSuperPerson(superPerson);
    }

    @Override
    public void deleteSuperPerson(int superPersonId) {
        personDao.deleteSuperPerson(superPersonId);
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        personDao.updateSuperPerson(superPerson);
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        return personDao.getSuperPersonById(superPersonId);
    }

    @Override
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID) {
        return personDao.getSuperPeopleByLocationId(locationID);
    }

    @Override
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId) {
        return personDao.getSuperPeopleByOrganizationId(organizationId);
    }

    @Override
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId) {
        return personDao.getSuperPeopleBySightingId(sightingId);
    }

    @Override
    public List<SuperPerson> getAllSuperPeople() {
        return personDao.getAllSuperPeople();
    }

}
