/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.SuperPower;
import java.util.List;

/**
 *
 * @author janie
 */
public interface LocationDao {

    public void addLocation(Location location);

    public Location getLocationByID(int id);

    public void updateLocation(Location location);

    public void deleteLocation(int locationID);

    public List<Location> getAllLocations();
    
    public List<Location> getAllLocationsBySuperPersonID(int superPersonID);

}
