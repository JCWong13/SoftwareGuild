/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Location;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author janie
 */
public class LocationDaoDbImpl implements LocationDao {
    
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt=jt;
    }
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "select * from sighting where LocationID = ?";

    private static final String SQL_INSERT_LOCATION
            = "insert into location (LocationName, LocationDescription, Latitude, Longitude) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where LocationId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set LocationName = ?, LocationDescription = ?, "
            + "Latitude = ?, Longitude = ? where LocationID = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where LocationId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select loca.locationName, loca.LocationDescription, loca.Latitude "
            + "loca.longitude from Location loca "
            + "join Sighting sight on loca.LocationID = sight.LocationID "
            + "where sight.SightingID = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";
    
    private static final String SQL_SELECT_LOCATIONS_BY_SUPERPERSON_ID
            ="select loca.LocationID, loca.LocationName, loca.LocationDescription, "
            + "loca.Latitude, loca.Longitude from SuperPersonSighting sps "
            + "join Sighting sight on sps.sightingID = sight.sightingID "
            + "join Location loca on sight.locationID = loca.locationID where superPersonID = ?";

    @Override
    @Transactional
    public void addLocation(Location location) {
        jt.update(SQL_INSERT_LOCATION, 
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLatitude(),
                location.getLongitude());
        
        int locationID
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        location.setLocationID(locationID);
    }

    @Override
    public Location getLocationByID(int id) {
        try {
            return jt.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateLocation(Location location) {
        jt.update(SQL_UPDATE_LOCATION, 
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    public void deleteLocation(int locationID) {
        jt.update(SQL_DELETE_LOCATION, locationID);
    }

    @Override
    public List<Location> getAllLocations() {
        return jt.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getAllLocationsBySuperPersonID(int superPersonID) {
        return jt.query(SQL_SELECT_LOCATIONS_BY_SUPERPERSON_ID, new LocationMapper(), superPersonID);
    }
    
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loca = new Location();
            loca.setLocationID(rs.getInt("LocationID"));
            loca.setLocationName(rs.getString("LocationName"));
            loca.setLocationDescription(rs.getString("LocationDescription"));
            loca.setLatitude(rs.getBigDecimal("Latitude").setScale(6, RoundingMode.HALF_UP));
            loca.setLongitude(rs.getBigDecimal("Longitude").setScale(6, RoundingMode.HALF_UP));
            return loca;
        }
        
    }
    
}
