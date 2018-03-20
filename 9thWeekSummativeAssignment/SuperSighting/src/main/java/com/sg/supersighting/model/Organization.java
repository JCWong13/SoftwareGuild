/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.model;

import java.util.Objects;

/**
 *
 * @author janie
 */
public class Organization {
    
    private int organizationID;
    private String typeOfOrganization;
    private String organizationName;
    private String organizationDescription;
    private String organizationAddress;
    private String organizationContact;

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getTypeOfOrganization() {
        return typeOfOrganization;
    }

    public void setTypeOfOrganization(String typeOfOrganization) {
        this.typeOfOrganization = typeOfOrganization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationContact() {
        return organizationContact;
    }

    public void setOrganizationContact(String organizationContact) {
        this.organizationContact = organizationContact;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.organizationID;
        hash = 83 * hash + Objects.hashCode(this.typeOfOrganization);
        hash = 83 * hash + Objects.hashCode(this.organizationName);
        hash = 83 * hash + Objects.hashCode(this.organizationDescription);
        hash = 83 * hash + Objects.hashCode(this.organizationAddress);
        hash = 83 * hash + Objects.hashCode(this.organizationContact);
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
        final Organization other = (Organization) obj;
        if (this.organizationID != other.organizationID) {
            return false;
        }
        if (!Objects.equals(this.typeOfOrganization, other.typeOfOrganization)) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationAddress, other.organizationAddress)) {
            return false;
        }
        if (!Objects.equals(this.organizationContact, other.organizationContact)) {
            return false;
        }
        return true;
    }
    
}
