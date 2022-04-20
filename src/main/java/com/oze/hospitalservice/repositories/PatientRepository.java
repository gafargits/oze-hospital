package com.oze.hospitalservice.repositories;

import com.oze.hospitalservice.models.DeletePatientRequest;
import com.oze.hospitalservice.models.PatientResponse;

import java.util.List;

public interface PatientRepository {
    List<PatientResponse> getPatients();

    int deletePatients(DeletePatientRequest deletePatientRequest);

    List<PatientResponse> getPatientCSV(Long patientId);
}
