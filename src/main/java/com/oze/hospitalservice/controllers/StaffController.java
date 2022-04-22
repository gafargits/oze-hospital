package com.oze.hospitalservice.controllers;

import com.oze.hospitalservice.Constants;
import com.oze.hospitalservice.exceptions.InvalidRequestException;
import com.oze.hospitalservice.models.Staff;
import com.oze.hospitalservice.models.StaffAuthRequest;
import com.oze.hospitalservice.models.StaffCreateResponse;
import com.oze.hospitalservice.services.StaffService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @PostMapping("/register")
    public ResponseEntity<StaffCreateResponse> registerUser(@RequestBody StaffAuthRequest authRequest){
        StaffCreateResponse staff = staffService.registerStaff(authRequest.getName(), authRequest.getPassword());
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginStaff(@RequestBody StaffAuthRequest authRequest){
        Staff staff = staffService.loginStaff(authRequest.getName(), authRequest.getPassword());
        return new ResponseEntity<>(generateJWTToken(staff), HttpStatus.OK);
    }

    @PutMapping("/update/{staffId}")
    public ResponseEntity<Map<String, Object>> updateStaff(@PathVariable("staffId") Integer staffId, HttpServletRequest request, @RequestBody Staff staff){

        if(!request.getAttribute("id").equals(staffId)){
            throw new InvalidRequestException("You're not allowed to update other staff record");
        }
        staffService.updateStaff(staffId, staff);
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("message", "update successful");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/get/{staffId}")
    public ResponseEntity<Staff> getStaff(@PathVariable("staffId") UUID staffId){
        Staff staff = staffService.getStaff(staffId);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(Staff staff){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("uuid", staff.getStaffId())
                .claim("name", staff.getName())
                .claim("id", staff.getId())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
