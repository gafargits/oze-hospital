package com.oze.hospitalservice.models;

import java.time.LocalDate;

public class Patient {
    private long id;
    private String name;
    private LocalDate dob;
    private LocalDate last_visit;

    public Patient() {
    }

    public Patient(long id, String name, LocalDate dob, LocalDate last_visit) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.last_visit = last_visit;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(LocalDate last_visit) {
        this.last_visit = last_visit;
    }
}
