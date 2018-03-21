/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.dao.LocationDaoDbImpl.LocationMapper;
import com.sg.supersighting.model.Location;
import com.sg.supersighting.model.Sighting;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (SightingDateTime, LocationId) "
            + "values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where SightingID = ?";
    
    private static final String SQL_DELETE_SUPERPERSON_SIGHTING_BY_SIGHTINGID
            ="delete from SuperPersonSighting where SightingId = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set SightingDateTime = ?, "
            + "LocationID = ? where SightingID = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where SightingId = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE
            = "select sight.* from Sighting sight "
            + "where cast(sight.SightingDateTime as Date) = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERPERSONID
            = "select sight.* from Sighting sight "
            + "join SuperPersonSighting sps on sight.SightingId = sps.SightingId "
            + "where sps.SuperPersonId = ?";
    
    private static final String SQL_GET_LOCATION_BY_SIGHTING_ID
            = "select loca.* from Location loca "
            + "join Sighting sight on loca.LocationID = sight.LocationID "
            + "where sight.SightingID = ?";

    @Override
    @Transactional
    public void addSighting(Sighting sighting) {
        
        jt.update(SQL_INSERT_SIGHTING,
                Timestamp.valueOf(sighting.getSightingDateTime()),
                sighting.getLocation().getLocationID());

        int sightingID
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingID(sightingID);
    }

    @Override
    public Sighting getSightingByID(int sightingId) {
        try {
            List<Sighting> sightings = new ArrayList<>();
            Sighting sighting = jt.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingId);
            sightings.add(sighting);
            sightings=associateLocationWithSighting(sightings);
            return sightings.get(0);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jt.update(SQL_UPDATE_SIGHTING,
                Timestamp.valueOf(sighting.getSightingDateTime()),
                sighting.getLocation().getLocationID(),
                sighting.getSightingID());
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingID) {
        jt.update(SQL_DELETE_SUPERPERSON_SIGHTING_BY_SIGHTINGID, sightingID);
        jt.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings;
        sightings=jt.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        return associateLocationWithSighting(sightings);
    }

    @Override
    public List<Sighting> getAllSightingsForDate(LocalDate localDate) {
        List<Sighting> sightings;
        sightings=jt.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE, new SightingMapper(), Date.valueOf(localDate));
        return associateLocationWithSighting(sightings);
    }

    @Override
    public List<Sighting> getAllSightingsBySuperPersonID(int superPersonId) {  
         List<Sighting> sightings=jt.query(SQL_SELECT_SIGHTINGS_BY_SUPERPERSONID, new SightingMapper(), superPersonId);
         return associateLocationWithSighting(sightings);
    }
    
    private Location getLocationBySightingId(int sightingId) {
        return jt.queryForObject(SQL_GET_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingId);
    }
    
    private List<Sighting> associateLocationWithSighting(List<Sighting> sightings) {
        for(Sighting currentSighting: sightings){
        Location location = getLocationBySightingId(currentSighting.getSightingID());
                      currentSighting.setLocation(location);          
        }
        return sightings;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sight = new Sighting();
            sight.setSightingID(rs.getInt("SightingID"));
            sight.setSightingDateTime(rs.getTimestamp("SightingDateTime").toLocalDateTime());
            return sight;
        }

    }
}
