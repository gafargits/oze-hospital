package com.oze.hospitalservice.services;

import com.oze.hospitalservice.models.DeletePatientRequest;
import com.oze.hospitalservice.models.PatientResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PatientService {
    List<PatientResponse> getPatients();

    int deletePatients(DeletePatientRequest deletePatientRequest);

    void getPatientCSV(HttpServletResponse httpServletResponse, Long patientId);
}
