package com.oze.hospitalservice.controllers;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.DeletePatientRequest;
import com.oze.hospitalservice.models.PatientResponse;
import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.services.PatientService;
import com.oze.hospitalservice.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    StaffService staffService;

    @GetMapping("/all")
    public ResponseEntity<List<PatientResponse>> getPatients(HttpServletRequest request) {
        staffService.getStaff(UUID.fromString(request.getAttribute("uuid").toString()));
        List<PatientResponse> patients = patientService.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "{patientId}", produces = "text/csv")
    public void getPatientCSV(HttpServletResponse httpServletResponse, @PathVariable("patientId") Long patientId, HttpServletRequest request) {
        staffService.getStaff(UUID.fromString(request.getAttribute("uuid").toString()));
        patientService.getPatientCSV(httpServletResponse, patientId);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deletePatients(@RequestBody @Valid DeletePatientRequest deletePatientRequest, HttpServletRequest request) {
        staffService.getStaff(UUID.fromString(request.getAttribute("uuid").toString()));
        int deletedRows = patientService.deletePatients(deletePatientRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("message", deletedRows + " Patients profile deleted successfully");
        map.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
