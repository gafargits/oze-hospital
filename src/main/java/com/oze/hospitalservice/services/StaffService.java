package com.oze.hospitalservice.services;

import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;

import java.util.UUID;

public interface StaffService {
    StaffCreateResponse registerStaff(String name, String password);

    Staff loginStaff(String name, String password);

    void updateStaff(Integer staffId, Staff staff);

    Staff getStaff(UUID staffId);
}
