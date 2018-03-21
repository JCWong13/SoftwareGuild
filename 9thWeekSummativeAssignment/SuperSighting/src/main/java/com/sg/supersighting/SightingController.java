/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.service.SSServiceLayer;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public SightingController(SSServiceLayer layer){
        this.layer=layer;
    }
    
    public void addSighting(Sighting sighting){
        
    }

    public Sighting getSightingByID(int sightingId){
        
    }

    public void updateSighting(Sighting sighting){
        
    }

    public void deleteSighting(int sightingID){
        
    }

    public List<Sighting> getAllSightings(){
        
    }
    
    public List<Sighting> getAllSightingsForDate(LocalDate localDate){
        
    }
    
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId){
        
    }
    
    
}
