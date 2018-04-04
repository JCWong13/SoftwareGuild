/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.service.SSServiceLayer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
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
public class SightingController {

    private SSServiceLayer layer;

    @Inject
    public SightingController(SSServiceLayer layer) {
        this.layer = layer;
    }

    @PostMapping("/Sighting/{ids}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sighting addSighting(@Valid @RequestBody Sighting sighting,
            @PathVariable("ids") String superPersonID,
            @PathVariable("id") Integer locationID) {
        sighting.setLocation(layer.getLocationByID(locationID));
        layer.addSighting(sighting);

        String[] numbers = superPersonID.split(",");

        for (int i = 0; i < numbers.length; i++) {
            SuperPerson person = layer.getSuperPersonById(Integer.parseInt(numbers[i]));
            List<Sighting> sightings = person.getSightings();
            sightings.add(sighting);
            person.setSightings(sightings);
            layer.updateSuperPerson(person);
        }
        return sighting;

    }

    @GetMapping("/Sighting/{id}")
    public Sighting getSightingByID(@PathVariable("id") Integer sightingId) {
        return layer.getSightingByID(sightingId);
    }

    @PutMapping("/Sighting/{id}/{ids}/{location}")
    public Sighting updateSighting(@PathVariable("id") Integer id, 
            @Valid @RequestBody Sighting sighting, 
            @PathVariable("ids") String superPeople, 
            @PathVariable("location") Integer locationID) throws Exception {
        
        if (id != sighting.getSightingID()) {
            throw new Exception("Sighting Id must match Sighting Id in submitted data");
        }
        
        sighting.setLocation(layer.getLocationByID(locationID));
        
        layer.updateSighting(sighting);
        String[] numbers = superPeople.split(",");

        for (int i = 0; i < numbers.length; i++) {
            SuperPerson person = layer.getSuperPersonById(Integer.parseInt(numbers[i]));
            List<Sighting> sightings = person.getSightings();
            sightings.add(sighting);
            person.setSightings(sightings);
            layer.updateSuperPerson(person);
        }
        
        return layer.getSightingByID(sighting.getSightingID());
    }

    @DeleteMapping("/Sighting/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSighting(@PathVariable("id") Integer sightingID) {
        layer.deleteSighting(sightingID);
    }

    @GetMapping("/Sightings")
    public List<Sighting> getAllSightings() {
        return layer.getAllSightings();
    }

    @GetMapping("/SightingsDate/{date}")
    public List<Sighting> getAllSightingsForDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate localDate) {
        List<Sighting> sightings = layer.getAllSightingsForDate(localDate);
        return sightings;
    }

    @GetMapping("/LastTenSightings")
    public List<Sighting> getLastTenSightings() {
        return layer.getLastTenSightings();
    }

    @GetMapping("/Sightings/{id}")
    public List<Sighting> getAllSightingsBySuperPersonID(@PathVariable("id") Integer superPersonId) {
        return layer.getAllSightingsBySuperPersonID(superPersonId);
    }

    public class SightingAddException extends RuntimeException {

        public SightingAddException(String message) {
            super(message);
        }
    }

}
