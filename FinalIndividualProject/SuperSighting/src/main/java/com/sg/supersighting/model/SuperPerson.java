/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author janie
 */
public class SuperPerson {

    private int superPersonID;
    @NotEmpty(message = "You must supply a value for SuperPerson Name")
    @Length(max = 50, message = "The value for SuperPerson Name must be no more than 50 characters in length.")
    private String superName;
    
    @NotEmpty(message = "You must supply a value for SuperPerson Description")
    @Length(max = 1000, message = "The value for SuperPerson Description must be no more than 1000 characters in length.")
    private String description;
    
    
    @NotEmpty(message = "You must supply a value for SuperPerson Type")
    @Length(max = 50, message = "The value for SuperPerson Type must be no more than 50 characters in length.")
    private String typeOfSuperPerson;
    private SuperPower superPower;
    private List<Sighting> sightings;
    private List<Organization> organizations;

    public int getSuperPersonID() {
        return superPersonID;
    }

    public void setSuperPersonID(int superPersonID) {
        this.superPersonID = superPersonID;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfSuperPerson() {
        return typeOfSuperPerson;
    }

    public void setTypeOfSuperPerson(String typeOfSuperPerson) {
        this.typeOfSuperPerson = typeOfSuperPerson;
    }

    public SuperPower getSuperPower() {
        return superPower;
    }

    public void setSuperPower(SuperPower superPower) {
        this.superPower = superPower;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.superPersonID;
        hash = 23 * hash + Objects.hashCode(this.superName);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.typeOfSuperPerson);
        hash = 23 * hash + Objects.hashCode(this.superPower);
        hash = 23 * hash + Objects.hashCode(this.sightings);
        hash = 23 * hash + Objects.hashCode(this.organizations);
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
        final SuperPerson other = (SuperPerson) obj;
        if (this.superPersonID != other.superPersonID) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.typeOfSuperPerson, other.typeOfSuperPerson)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }

}
