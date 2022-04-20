package com.oze.hospitalservice.services;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.DeletePatientRequest;
import com.oze.hospitalservice.models.PatientResponse;
import com.oze.hospitalservice.repositories.PatientRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<PatientResponse> getPatients() {
        return patientRepository.getPatients();
    }

    @Override
    public int deletePatients(DeletePatientRequest deletePatientRequest) {
        return patientRepository.deletePatients(deletePatientRequest);
    }

    @Override
    public void getPatientCSV(HttpServletResponse httpServletResponse, Long patientId) {
        String filename = "patient " + patientId + ".csv";
        List<PatientResponse> patients = patientRepository.getPatientCSV(patientId);
        if(patients.size() < 1){
            throw new InvalidRequestException("No such patient in our record");
        }
        try {
            httpServletResponse.setContentType("text/csv");
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            CSVPrinter csvPrinter = new CSVPrinter(httpServletResponse.getWriter(),
                    CSVFormat.DEFAULT.withHeader("id", "name", "age", "last visit date"));
            for(PatientResponse patient : patients){
                csvPrinter.printRecord(patient.getId(), patient.getName(), patient.getAge(), patient.getLast_visit_date());
            }
        } catch (Exception e){
            throw new InvalidRequestException(e.getMessage());
        }
    }
}
