/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.SuperPerson;
import com.sg.supersighting.model.SuperPower;
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
public class SuperPowerDaoDbImpl implements SuperPowerDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_SELECT_SUPERPEOPLE_BY_SUPERPOWER_ID
            = "select * from SuperPerson where SuperPowerID = ?";

    private static final String SQL_INSERT_SUPERPOWER
            = "insert into superpower (SuperPowerName) "
            + "values (?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from superpower where SuperPowerId = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "update superpower set SuperPowerName = ? where SuperPowerID = ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "select * from superPower where superPowerId = ?";

    private static final String SQL_SELECT_SUPERPOWER_BY_SUPERPERSON_ID
            = "select power.* from superpower power "
            + "join superperson person on power.superpowerID = person.superpowerID "
            + "where person.SuperPersonID = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "select * from superpower";

    private static final String SQL_SELECT_ALL_SUPERPOWERS_BY_NAME
            = "select * from superpower where superpower.superpowername = ?";

    @Override
    @Transactional
    public void addSuperPower(SuperPower superPower) {
        jt.update(SQL_INSERT_SUPERPOWER,
                superPower.getSuperPowerName());

        int superPowerID
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPower.setSuperPowerID(superPowerID);
    }

    @Override
    public SuperPower getSuperPowerByID(int id) {
        try {
            return jt.queryForObject(SQL_SELECT_SUPERPOWER, new SuperPowerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSuperPower(SuperPower superPower) {
        jt.update(SQL_UPDATE_SUPERPOWER,
                superPower.getSuperPowerName(), superPower.getSuperPowerID());
    }

    @Override
    public void deleteSuperPower(int superPowerID) {
        jt.update(SQL_DELETE_SUPERPOWER, superPowerID);
    }

    @Override
    public SuperPower getSuperPowerBySuperPersonID(int superPersonId) {
        return jt.queryForObject(SQL_SELECT_SUPERPOWER_BY_SUPERPERSON_ID,
                new SuperPowerMapper(),
                superPersonId);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return jt.query(SQL_SELECT_ALL_SUPERPOWERS, new SuperPowerMapper());
    }

   
    @Override
    public SuperPower getSuperPowerBySuperPowerName(String superPowerName) {
        try {
            return jt.queryForObject(SQL_SELECT_ALL_SUPERPOWERS_BY_NAME, new SuperPowerMapper(), superPowerName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }   
    }

    public static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower power = new SuperPower();
            power.setSuperPowerID(rs.getInt("SuperPowerID"));
            power.setSuperPowerName(rs.getString("SuperPowerName"));
            return power;
        }

    }

}
