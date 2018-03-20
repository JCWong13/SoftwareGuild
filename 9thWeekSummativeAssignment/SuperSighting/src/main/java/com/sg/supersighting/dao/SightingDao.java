/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janie
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public Sighting getSightingByID(int sightingId);

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsForDate(LocalDate localDate);
    
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId);
    
}
