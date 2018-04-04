/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.dao.OrganizationDaoDbImpl.OrganizationMapper;
import com.sg.supersighting.dao.SightingDaoDbImpl.SightingMapper;
import com.sg.supersighting.dao.SuperPowerDaoDbImpl.SuperPowerMapper;
import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.Organization;
import com.sg.supersighting.model.Sighting;
import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author janie
 */
public class SuperPersonDaoDbImpl implements SuperPersonDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_SUPERPERSON
            = "insert into superperson "
            + "(SuperName, Description, TypeOfSuperPerson, SuperPowerID) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_INSERT_SUPERPERSON_SIGHTING
            = "insert into SuperPersonSighting (SuperPersonID, SightingID) values(?, ?)";

    private static final String SQL_INSERT_SUPERPERSON_ORGANIZATION
            = "insert into SuperPersonOrganization (SuperPersonID, OrganizationID) values(?, ?)";

    private static final String SQL_DELETE_SUPERPERSON
            = "delete from SuperPerson where SuperPersonId = ?";

    private static final String SQL_DELETE_SUPERPERSON_SIGHTING
            = "delete from SuperPersonSighting where SuperPersonID = ?";

    private static final String SQL_DELETE_SUPERPERSON_ORGANIZATION
            = "delete from SuperPersonOrganization where SuperPersonID = ?";

    private static final String SQL_UPDATE_SUPERPERSON
            = "update SuperPerson set SuperName = ?, Description = ?, TypeOfSuperPerson = ?, "
            + "SuperPowerID = ? where SuperPersonID = ?";

    private static final String SQL_SELECT_SUPERPERSON
            = "select * from SuperPerson where SuperPersonID = ?";

    private static final String SQL_SELECT_SUPERPERSON_ORGANIZATION_ORGANIZATIONID_BY_SUPERPERSONID
            = "select OrganizationID from SuperPersonOrganization where SuperPersonID = ?";

    private static final String SQL_SELECT_SUPERPERSON_SIGHTING_SIGHTINGID_BY_SUPERPERSONID
            = "select SightingID from SuperPersonSighting where SuperPersonID = ?";

    private static final String SQL_SELECT_ALL_SUPERPERSON
            = "select * from SuperPerson";

    private static final String SQL_SELECT_SUPERPEOPLE_BY_ORGANIZATION_ID
            = "select person.* from SuperPerson person "
            + "join SuperPersonOrganization spo on person.SuperPersonId = spo.SuperPersonId "
            + "join Organization org on spo.OrganizationId = org.OrganizationId "
            + "where spo.OrganizationID = ?";

    private static final String SQL_SELECT_SUPERPEOPLE_BY_SIGHTING_ID
            = "select person.* from SuperPerson person join SuperPersonSighting sps"
            + " on person.SuperPersonID = sps.SuperPersonID "
            + "where sps.SightingID = ?";

    private static final String SQL_SELECT_SUPERPEOPLE_BY_LOCATION_ID
            = "select person.* from SuperPerson person join "
            + "SuperPersonSighting sps on person.SuperPersonId = sps.SuperPersonId "
            + "join Sighting sight on sps.SightingId = sight.SightingId "
            + "join Location loca on sight.LocationId = loca.LocationId where loca.LocationId = ?";

    private static final String SQL_SELECT_SUPERPOWER_BY_SUPERPERSON_ID
            = "select power.* from SuperPower power "
            + "join SuperPerson person on power.SuperPowerId = person.SuperPowerId "
            + "where person.SuperPersonId = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERPERSON_ID
            = "select Org.* from Organization org "
            + "join SuperPersonOrganization spo on org.OrganizationId = spo.OrganizationId "
            + "join SuperPerson person on spo.SuperPersonId = person.SuperPersonId "
            + "where person.SuperPersonId = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERPERSON_ID
            = "select sight.* from Sighting sight "
            + "join SuperPersonSighting sps on sight.SightingId = sps.SightingId "
            + "join SuperPerson person on sps.SuperPersonId = person.SuperPersonId "
            + "where person.SuperPersonId = ?";
    
    private static final String SQL_GET_LOCATION_BY_SIGHTING_ID
            = "select loca.* from Location loca "
            + "join Sighting sight on loca.LocationID = sight.LocationID "
            + "where sight.SightingID = ?";

    @Override
    @Transactional
    public void addSuperPerson(SuperPerson superPerson) {

        jt.update(SQL_INSERT_SUPERPERSON,
                superPerson.getSuperName(),
                superPerson.getDescription(),
                superPerson.getTypeOfSuperPerson(),
                superPerson.getSuperPower().getSuperPowerID());
        
        superPerson.setSuperPersonID(jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

        insertSuperPersonOrganization(superPerson);
        insertSuperPersonSighting(superPerson);
    }

    @Override
    @Transactional
    public void deleteSuperPerson(int superPersonId) {
        jt.update(SQL_DELETE_SUPERPERSON_SIGHTING, superPersonId);
        jt.update(SQL_DELETE_SUPERPERSON_ORGANIZATION, superPersonId);

        jt.update(SQL_DELETE_SUPERPERSON, superPersonId);
    }

    @Override
    @Transactional
    public void updateSuperPerson(SuperPerson superPerson) {
        jt.update(SQL_DELETE_SUPERPERSON_SIGHTING, superPerson.getSuperPersonID());
        jt.update(SQL_DELETE_SUPERPERSON_ORGANIZATION, superPerson.getSuperPersonID());

        jt.update(SQL_UPDATE_SUPERPERSON,
                superPerson.getSuperName(),
                superPerson.getDescription(),
                superPerson.getTypeOfSuperPerson(),
                superPerson.getSuperPower().getSuperPowerID(),
                superPerson.getSuperPersonID());

        insertSuperPersonOrganization(superPerson);
        insertSuperPersonSighting(superPerson);
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        try {
            List<SuperPerson> superPeople = new ArrayList<>();
            SuperPerson superPerson = jt.queryForObject(SQL_SELECT_SUPERPERSON,
                    new SuperPersonMapper(), superPersonId);
            superPeople.add(superPerson);
            superPeople = associateSuperPowerSightingsOrganizationsWithSuperPeople(superPeople);
            
            return superPeople.get(0);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getSuperPeopleByLocationId(int locationID) {
        List<SuperPerson> superPeople
                = jt.query(SQL_SELECT_SUPERPEOPLE_BY_LOCATION_ID,
                        new SuperPersonMapper(),
                        locationID);
        superPeople=associateSuperPowerSightingsOrganizationsWithSuperPeople(superPeople);
        return superPeople;
    }

    @Override
    public List<SuperPerson> getSuperPeopleByOrganizationId(int organizationId) {
        List<SuperPerson> superPeople
                = jt.query(SQL_SELECT_SUPERPEOPLE_BY_ORGANIZATION_ID,
                        new SuperPersonMapper(),
                        organizationId);
        superPeople=associateSuperPowerSightingsOrganizationsWithSuperPeople(superPeople);
        return superPeople;

    }

    @Override
    public List<SuperPerson> getSuperPeopleBySightingId(int sightingId) {
        List<SuperPerson> superPeople
                = jt.query(SQL_SELECT_SUPERPEOPLE_BY_SIGHTING_ID,
                        new SuperPersonMapper(),
                        sightingId);
            superPeople=associateSuperPowerSightingsOrganizationsWithSuperPeople(superPeople);
        return superPeople;
    }

    @Override
    public List<SuperPerson> getAllSuperPeople() {
        List<SuperPerson> superPeople = jt.query(SQL_SELECT_ALL_SUPERPERSON, new SuperPersonMapper());
            superPeople=associateSuperPowerSightingsOrganizationsWithSuperPeople(superPeople);
        return superPeople;
    }

    //HELPER METHODS
    private SuperPower getSuperPowerBySuperPerson(SuperPerson superPerson) {
        return jt.queryForObject(SQL_SELECT_SUPERPOWER_BY_SUPERPERSON_ID, new SuperPowerMapper(), superPerson.getSuperPersonID());
    }

    private List<Sighting> getSightingsBySuperPerson(SuperPerson superPerson) {
        List<Sighting> sightings = jt.query(SQL_SELECT_SIGHTINGS_BY_SUPERPERSON_ID, new SightingMapper(), superPerson.getSuperPersonID());
        sightings = associateLocationWithSighting(sightings);
        return sightings;
    }

    private List<Organization> getOrganizationsBySuperPerson(SuperPerson superPerson) {
        return jt.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERPERSON_ID, new OrganizationMapper(), superPerson.getSuperPersonID());
    }

    private Location getLocationBySightingId(int sightingId) {
        return jt.queryForObject(SQL_GET_LOCATION_BY_SIGHTING_ID, new LocationDaoDbImpl.LocationMapper(), sightingId);
    }
    
    private List<Sighting> associateLocationWithSighting(List<Sighting> sightings) {
        for(Sighting currentSighting: sightings){
        Location location = getLocationBySightingId(currentSighting.getSightingID());
                      currentSighting.setLocation(location);          
        }
        return sightings;
    }
    
    private List<SuperPerson> associateSuperPowerSightingsOrganizationsWithSuperPeople(List<SuperPerson> superPeople) {
        for (SuperPerson superPerson : superPeople) {
            superPerson.setSuperPower(getSuperPowerBySuperPerson(superPerson));
            superPerson.setSightings(getSightingsBySuperPerson(superPerson));
            superPerson.setOrganizations(getOrganizationsBySuperPerson(superPerson));
        }
        return superPeople;
    }

    private void insertSuperPersonOrganization(SuperPerson superPerson) {
        if (superPerson.getOrganizations() != null) {
            final int superPersonId = superPerson.getSuperPersonID();
            final List<Organization> organizations = superPerson.getOrganizations();

            for (Organization currentOrganization : organizations) {
                jt.update(SQL_INSERT_SUPERPERSON_ORGANIZATION,
                        superPersonId,
                        currentOrganization.getOrganizationID());
            }
        }
    }

    private void insertSuperPersonSighting(SuperPerson superPerson) {
        if (superPerson.getSightings() != null) {
            final int superPersonId = superPerson.getSuperPersonID();
            final List<Sighting> sightings = superPerson.getSightings();

            for (Sighting currentSighting : sightings) {
                jt.update(SQL_INSERT_SUPERPERSON_SIGHTING,
                        superPersonId,
                        currentSighting.getSightingID());
            }
        }
    }

    private static final class SuperPersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson person = new SuperPerson();
            person.setSuperPersonID(rs.getInt("SuperPersonID"));
            person.setSuperName(rs.getString("SuperName"));
            person.setDescription(rs.getString("Description"));
            person.setTypeOfSuperPerson(rs.getString("TypeOfSuperPerson"));

            return person;
        }

    }

}
