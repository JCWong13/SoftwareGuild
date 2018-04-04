/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.SuperPower;
import com.sg.supersighting.service.SSServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
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
@RestController//at response body no longer needed 
public class LocationController {
    
    private SSServiceLayer layer;
    
    @Inject
    public LocationController(SSServiceLayer layer){
        this.layer=layer;
    }
    
    @PostMapping("/Location")
    @ResponseStatus(HttpStatus.CREATED)
    public Location addLocation(@Valid @RequestBody Location location) {
        for(Location currentLocation:layer.getAllLocations()){
            if(currentLocation.getLocationName().equalsIgnoreCase(location.getLocationName())){
                throw new LocationAddException("Error: That Location already exists.");
            }
        }
            layer.addLocation(location);
            return layer.getLocationByID(location.getLocationID());
    }
    
    @GetMapping("/Location/{id}")
    public Location getLocationByID(@PathVariable("id") Integer id){
        return layer.getLocationByID(id);
    }
    
    @PutMapping("/Location/{id}")
    public Location updateLocation(@PathVariable("id") Integer id, @Valid @RequestBody Location location) throws Exception{
        
        if (id != location.getLocationID()) {
            throw new Exception("Location does not match inputted ID");
        }
        layer.updateLocation(location);
        return layer.getLocationByID(location.getLocationID());
    }

    @DeleteMapping("/Location/{id}")
    public void deleteLocation(@PathVariable("id") Integer locationID){
        try{
        layer.deleteLocation(locationID);
        }catch(DataAccessException ex){
            throw new LocationDataException("Error: Please Delete linked Sighting prior to deleting Location");
    }
    }

    @GetMapping("/Locations")
    public List<Location> getAllLocations(){
        
        List<Location> locations=layer.getAllLocations();
        return locations;
    }
    
    @GetMapping("/Locations/{id}")
    public List<Location> getAllLocationsBySuperPersonID(@PathVariable("id") Integer superPersonID){
        return layer.getAllLocationsBySuperPersonID(superPersonID);
    }
    
    @ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
    public class LocationDataException extends RuntimeException{
        public LocationDataException(String message) {
            super(message);
        }
    }
    
      public class LocationAddException extends RuntimeException {
        public LocationAddException(String message) {
            super(message);
        }
    }
    
}
