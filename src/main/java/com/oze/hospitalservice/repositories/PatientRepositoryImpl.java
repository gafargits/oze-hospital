package com.oze.hospitalservice.repositories;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.DeletePatientRequest;
import com.oze.hospitalservice.models.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_PATIENTS = "SELECT ID, NAME, (YEAR(CURDATE()) - YEAR(DOB)) AS AGE, LAST_VISIT_DATE FROM HOSPITAL_PATIENT " +
            "WHERE YEAR(CURDATE()) - YEAR(LAST_VISIT_DATE) < 2";
    private static final String SQL_DELETE_PATIENTS = "DELETE FROM HOSPITAL_PATIENT WHERE LAST_VISIT_DATE BETWEEN ? AND ?";
    private static final String SQL_GET_PATIENT = "SELECT ID, NAME, (YEAR(CURDATE()) - YEAR(DOB)) AS AGE, LAST_VISIT_DATE FROM HOSPITAL_PATIENT WHERE ID = ?";

    @Override
    public List<PatientResponse> getPatients() {
        return jdbcTemplate.query(SQL_GET_PATIENTS, patientMapper);
    }

    @Override
    public int deletePatients(DeletePatientRequest deletePatientRequest) {
        return jdbcTemplate.update(SQL_DELETE_PATIENTS, new Object[]{deletePatientRequest.getFrom(), deletePatientRequest.getTo()});
    }

    @Override
    public List<PatientResponse> getPatientCSV(Long patientId) {
        try {
             return jdbcTemplate.query(SQL_GET_PATIENT, new Object[]{patientId}, patientMapper);
        } catch (Exception e){
            throw new InvalidRequestException(e.getMessage());
        }
    }

    private RowMapper<PatientResponse> patientMapper = ((rs, rowNum) -> {
        return new PatientResponse(rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getInt("AGE"),
                rs.getDate("LAST_VISIT_DATE"));
    });
}
