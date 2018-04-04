/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author janie
 */
public class Location {
    
    private int locationID;
    @NotEmpty(message = "You must supply a value for Location Name")
    @Length(max = 50, message = "The value for Location Name must be no more than 50 characters in length.")
    private String locationName;
    @NotEmpty(message = "You must supply a value for Location Description")
    @Length(max = 1000, message = "The value for Location Description must be no more than 1000 characters in length.")
    private String locationDescription;
    @NotNull(message = "Please enter a value for Location Latitude")
    @DecimalMin(value="-90", inclusive=true, message ="Please enter a coordinate greater than -90.000000")
    @DecimalMax(value="90", inclusive=true, message="Please enter a coordinate lower than 90.000000")
    @Digits(integer=2, fraction=6, message="Please enter a valid Latitude - for example: 89.123456")
    private BigDecimal latitude;
    @NotNull(message = "Please enter a value for Location Longitude")
    @DecimalMin(value="-180", inclusive=true, message ="Please enter a coordinate greater than -180.000000")
    @DecimalMax(value="180", inclusive=true, message="Please enter a coordinate lower than 180.000000")
    @Digits(integer=3, fraction=6, message="Please enter a valid Longitude - for example: 123.123456")
    private BigDecimal longitude;
    

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.locationID;
        hash = 59 * hash + Objects.hashCode(this.locationName);
        hash = 59 * hash + Objects.hashCode(this.locationDescription);
        hash = 59 * hash + Objects.hashCode(this.latitude);
        hash = 59 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

    
}
