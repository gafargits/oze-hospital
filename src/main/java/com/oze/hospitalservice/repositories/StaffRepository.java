package com.oze.hospitalservice.repositories;

import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Map;
import java.util.UUID;

public interface StaffRepository {
    Map<String, Object> create(String name, String password);
    Integer getUserCountByName(String name);
    Staff findById(Integer staffId);
    Staff findByNameAndPassword(String name, String password);
    void updateStaff(Integer staffId, Staff staff);

    Staff findByStaffId(UUID staffId);
}
