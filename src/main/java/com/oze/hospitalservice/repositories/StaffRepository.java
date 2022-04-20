package com.oze.hospitalservice.repositories;

import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;

import java.util.UUID;

public interface StaffRepository {
    StaffCreateResponse create(String name, String password);
    Integer getUserCountByName(String name);
    Staff findById(Integer staffId);
    Staff findByNameAndPassword(String name, String password);
    void updateStaff(Integer staffId, Staff staff);

    Staff findByStaffId(UUID staffId);
}
