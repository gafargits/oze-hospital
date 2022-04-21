package com.oze.hospitalservice.services;

import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffCreateResponse;
import com.oze.hospitalservice.repositories.StaffRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public StaffCreateResponse registerStaff(String name, String password) {
        if(name.isEmpty() || password.isEmpty()) throw new InvalidRequestException("Name/Password cannot be blank");
        Integer count = staffRepository.getUserCountByName(name);
        if(count > 0)
            throw new InvalidRequestException("User already exist");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        var response = staffRepository.create(name, hashedPassword);
        return new StaffCreateResponse(Long.valueOf(response.get("id").toString()),
                UUID.fromString(response.get("staff_id").toString()) , name, (Date) response.get("registration_date") );
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
