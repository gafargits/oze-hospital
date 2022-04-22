package com.oze.hospitalservice.repositories;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.UUID;

@Repository
public class StaffRepositoryImpl implements StaffRepository {
    private static final String SQL_COUNT_BY_NAME = "SELECT COUNT(*) FROM HOSPITAL_STAFF WHERE NAME = ? ";
    private static final String SQL_CREATE_STAFF = "INSERT INTO HOSPITAL_STAFF (ID, NAME, PASSWORD, STAFF_ID, REGISTRATION_DATE) VALUES(NEXTVAL('OZE_STAFF_SEQ'), ?, ?, random_uuid(), CURRENT_DATE())";
    private static final String SQL_FIND_BY_ID = "SELECT ID, NAME, STAFF_ID, PASSWORD, REGISTRATION_DATE FROM HOSPITAL_STAFF WHERE ID = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT ID, NAME, STAFF_ID, PASSWORD, REGISTRATION_DATE FROM HOSPITAL_STAFF WHERE NAME = ?";
    private static final String SQL_UPDATE_STAFF = "UPDATE HOSPITAL_STAFF SET NAME = ?, PASSWORD = ? WHERE ID = ?";
    private static final String SQL_FIND_BY_STAFF_ID = "SELECT ID, NAME, STAFF_ID, REGISTRATION_DATE FROM HOSPITAL_STAFF WHERE STAFF_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> create(String name, String password) throws InvalidRequestException {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_CREATE_STAFF, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, password);
                return ps;
            }, keyHolder);
            return keyHolder.getKeys();
    }

    @Override
    public Integer getUserCountByName(String name) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_NAME, new Object[]{name}, Integer.class);
    }

    @Override
    public Staff findById(Integer staffId) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{staffId}, staffRowMapper);
        } catch (DataAccessException e){
            throw new InvalidRequestException("Invalid staff ID");
        }
    }

    @Override
    public Staff findByNameAndPassword(String name, String password) {
        try{
            Staff staff = jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, new Object[]{name}, staffRowMapper);
            if(!BCrypt.checkpw(password, staff.getPassword()))
                throw new InvalidRequestException("Invalid name/password");
            return staff;
        } catch (EmptyResultDataAccessException e){
            throw new InvalidRequestException("Invalid name/password");
        }
    }

    @Override
    public void updateStaff(Integer staffId, Staff staff) {
        try{
            String hashedPassword = BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt(10));
           jdbcTemplate.update(SQL_UPDATE_STAFF, new Object[]{staff.getName(), hashedPassword, staffId});
        } catch (Exception e){
            throw new InvalidRequestException("Update failed");
        }
    }

    @Override
    public Staff findByStaffId(UUID staffId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_STAFF_ID, new Object[]{staffId}, staffResponseMapper);
        } catch (Exception e){
            throw new InvalidRequestException("Invalid staff");
        }

    }

    private RowMapper<Staff> staffRowMapper = ((rs, rowNum) -> {
        return new Staff(rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("PASSWORD"),
                (UUID) rs.getObject("STAFF_ID"),
                rs.getDate("REGISTRATION_DATE"));

    });

    private RowMapper<Staff> staffResponseMapper = ((rs, rowNum) -> {
        return new Staff(rs.getLong("ID"),
                rs.getString("NAME"),
                (UUID) rs.getObject("STAFF_ID"),
                rs.getDate("REGISTRATION_DATE"));

    });
}
