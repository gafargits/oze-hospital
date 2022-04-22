package com.oze.hospitalservice;

import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;

import java.util.Date;
import java.util.UUID;

public class BaseServiceTest {
    Staff registerStaff(String name, String password){
        return new Staff(1000, name, password, UUID.randomUUID(), new Date());
    }
    StaffCreateResponse getRegisteredStaff(String name, String password){
        var staff = registerStaff(name, password);
        return new StaffCreateResponse(staff.getId(), staff.getStaffId(), staff.getName(), staff.getRegistrationDate());
    }
}
