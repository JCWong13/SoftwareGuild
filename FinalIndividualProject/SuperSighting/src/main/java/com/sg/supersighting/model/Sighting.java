/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author janie
 */
public class Sighting {
    
    private int sightingID;
    @NotNull(message="Please enter a Date and Time, for example: 2018-01-23 12:43:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sightingDateTime;
    private Location location;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public LocalDateTime getSightingDateTime() {
        return sightingDateTime;
    }

    public void setSightingDateTime(LocalDateTime sightingDateTime) {
        this.sightingDateTime = sightingDateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.sightingID;
        hash = 83 * hash + Objects.hashCode(this.sightingDateTime);
        hash = 83 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.sightingDateTime, other.sightingDateTime)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }



    
}
