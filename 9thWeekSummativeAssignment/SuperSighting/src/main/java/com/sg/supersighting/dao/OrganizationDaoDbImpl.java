/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting.dao;

import com.sg.supersighting.model.Organization;
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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jt;

      public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt=jt;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (TypeOfOrganization, OrganizationName, OrganizationDescription, "
            + "OrganizationAddress, OrganizationContact) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where OrganizationID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set TypeOfOrganization = ?, OrganizationName = ?, "
            + "OrganizationDescription = ?, OrganizationAddress = ?, "
            + "OrganizationContact = ? where OrganizationID = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where OrganizationId = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERPERSON_ID
            = "select org.organizationID, org.TypeOfOrganization, org.OrganizationName, "
            + "org.OrganizationDescription, org.OrganizationAddress, org.OrganizationContact "
            + "from organization org"
            + "join SuperPersonOrganization spo on org.organizationId = spo.organizationID where "
            + "spo.SuperPersonID = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";
    
    private static final String SQL_SELECT_ALL_ORGANIZATIONS_BY_SUPERHERO
            = "select org.* from SuperPersonOrganization spo "
            + "join Organization org on spo.OrganizationID = org.OrganizationID "
            + "where spo.SuperPersonID = ?";

    @Override
    @Transactional
    public void addOrganization(Organization organization) {
        jt.update(SQL_INSERT_ORGANIZATION,
                organization.getTypeOfOrganization(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddress(),
                organization.getOrganizationContact());
        
        int organizationID
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        organization.setOrganizationID(organizationID);
    }

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try {
            return jt.queryForObject(SQL_SELECT_ORGANIZATION, 
                    new OrganizationMapper(),
                    organizationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateOrganization(Organization organization) {
        jt.update(SQL_UPDATE_ORGANIZATION,
                organization.getTypeOfOrganization(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddress(),
                organization.getOrganizationContact(),
                organization.getOrganizationID());
    }

    @Override
    public void deleteOrganization(int organizationID) {
        jt.update(SQL_DELETE_ORGANIZATION, organizationID);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jt.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(int superPersonID) {
        return jt.query(SQL_SELECT_ALL_ORGANIZATIONS_BY_SUPERHERO, new OrganizationMapper(), superPersonID);
    } 
    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationID(rs.getInt("OrganizationID"));
            org.setTypeOfOrganization(rs.getString("TypeOfOrganization"));
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setOrganizationDescription(rs.getString("OrganizationDescription"));
            org.setOrganizationAddress(rs.getString("OrganizationAddress"));
            org.setOrganizationContact(rs.getString("OrganizationContact"));
            return org;
        }
        
    }

}
