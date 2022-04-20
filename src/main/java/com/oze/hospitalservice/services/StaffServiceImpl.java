package com.oze.hospitalservice.services;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;
import com.oze.hospitalservice.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public StaffCreateResponse registerStaff(String name, String password) {
        Integer count = staffRepository.getUserCountByName(name);
        if(count > 0)
            throw new InvalidRequestException("User already exist");
        return  staffRepository.create(name, password);
    }

    @Override
    public Staff loginStaff(String name, String password) {
        return staffRepository.findByNameAndPassword(name, password);
    }

    @Override
    public void updateStaff(Integer staffId, Staff staff) {
        staffRepository.updateStaff(staffId, staff);
    }

    @Override
    public Staff getStaff(UUID staffId) {
        return staffRepository.findByStaffId(staffId);
    }
}
