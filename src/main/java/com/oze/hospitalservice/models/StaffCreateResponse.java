package com.oze.hospitalservice.models;

import java.util.Date;
import java.util.UUID;

public class StaffCreateResponse {
    private long id;
    private UUID staffId;
    private String name;
    private Date registrationDate;

    public StaffCreateResponse(long id, UUID staffId, String name, Date registrationDate) {
        this.id = id;
        this.staffId = staffId;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public StaffCreateResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
