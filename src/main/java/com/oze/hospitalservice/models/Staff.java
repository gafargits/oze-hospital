package com.oze.hospitalservice.models;

import java.util.Date;
import java.util.UUID;

public class Staff {
    private long id;
    private String name;
    private String password;
    private UUID staffId;
    private Date registrationDate;

    public Staff () {}
    public Staff(long id, String name, String password, UUID staffId, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.staffId = staffId;
        this.registrationDate = registrationDate;
    }

    public Staff(long id, String name, UUID staffId, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.staffId = staffId;
        this.registrationDate = registrationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
