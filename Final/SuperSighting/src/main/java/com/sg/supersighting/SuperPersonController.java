/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Organization;
import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.service.SSServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
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
public class SuperPersonController {

    private SSServiceLayer layer;

    @Inject
    public SuperPersonController(SSServiceLayer layer) {
        this.layer = layer;
    }

    @PostMapping("/SuperPerson/{sightIds}/{orgIds}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SuperPerson addSuperPerson(@Valid @RequestBody SuperPerson superPerson,
            @PathVariable("sightIds") String sightIds,
            @PathVariable("orgIds") String orgIds,
            @PathVariable("id") Integer superPowerId) {

        superPerson.setSuperPower(layer.getSuperPowerByID(superPowerId));

        String[] sightingIds = sightIds.split(",");
        String[] organizationIds = orgIds.split(",");
 
        List<Sighting> sightings = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < sightingIds.length; i++) {
            Sighting sighting = layer.getSightingByID((Integer.parseInt(sightingIds[i])));
            sightings.add(sighting);
        }

        for (int i = 0; i < organizationIds.length; i++) {
            Organization org = layer.getOrganizationByID(Integer.parseInt(organizationIds[i]));
            organizations.add(org);
        }
        superPerson.setSightings(sightings);
        superPerson.setOrganizations(organizations);

        layer.addSuperPerson(superPerson);

        return layer.getSuperPersonById(superPerson.getSuperPersonID());
    }

    @DeleteMapping("/SuperPerson/{id}")
    public void deleteSuperPerson(@PathVariable("id") Integer superPersonId) {
        layer.deleteSuperPerson(superPersonId);
    }

    @PutMapping("/SuperPerson/{super}/{sightIds}/{orgIds}/{id}")
    public SuperPerson updateSuperPerson(@PathVariable("super") Integer id, 
            @Valid @RequestBody SuperPerson superPerson,
            @PathVariable("sightIds") String sightIds,
            @PathVariable("orgIds") String orgIds,
            @PathVariable("id") Integer superPowerId) throws Exception {
        if (id != superPerson.getSuperPersonID()) {
            throw new Exception("SuperPerson Id must match id in submitted data");
        }
        superPerson.setSuperPower(layer.getSuperPowerByID(superPowerId));

        String[] sightingIds = sightIds.split(",");
        String[] organizationIds = orgIds.split(",");

        List<Sighting> sightings = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < sightingIds.length; i++) {
            Sighting sighting = layer.getSightingByID((Integer.parseInt(sightingIds[i])));
            sightings.add(sighting);
        }

        for (int i = 0; i < organizationIds.length; i++) {
            Organization org = layer.getOrganizationByID(Integer.parseInt(organizationIds[i]));
            organizations.add(org);
        }
        superPerson.setSightings(sightings);
        superPerson.setOrganizations(organizations);

        layer.updateSuperPerson(superPerson);

        return layer.getSuperPersonById(superPerson.getSuperPersonID());    
        
    }

    @GetMapping("/SuperPerson/{id}")
    public SuperPerson getSuperPersonById(@PathVariable("id") Integer superPersonId) {
        return layer.getSuperPersonById(superPersonId);
    }

    @GetMapping("/SuperPeopleByLocation/{id}")
    public List<SuperPerson> getSuperPeopleByLocationId(@PathVariable("id") Integer locationID) {
        return layer.getSuperPeopleByLocationId(locationID);
    }

    @GetMapping("/SuperPeopleByOrganization/{id}")
    public List<SuperPerson> getSuperPeopleByOrganizationId(@PathVariable("id") Integer organizationId) {
        return layer.getSuperPeopleByOrganizationId(organizationId);
    }

    @GetMapping("/SuperPeopleBySighting/{id}")
    public List<SuperPerson> getSuperPeopleBySightingId(@PathVariable("id") Integer sightingId) {
        return layer.getSuperPeopleBySightingId(sightingId);
    }

    @GetMapping("/SuperPeople")
    public List<SuperPerson> getAllSuperPeople() {
        return layer.getAllSuperPeople();
    }

    public class SuperPersonAddException extends RuntimeException {
        public SuperPersonAddException(String message) {
            super(message);
        }
    }
}
