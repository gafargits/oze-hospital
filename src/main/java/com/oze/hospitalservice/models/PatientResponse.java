package com.oze.hospitalservice.models;

import java.util.Date;

public class PatientResponse {
    private long id;
    private String name;
    private Integer age;
    private Date last_visit_date;

    public PatientResponse() {
    }

    public PatientResponse(long id, String name, Integer age, Date last_visit_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.last_visit_date = last_visit_date;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getLast_visit_date() {
        return last_visit_date;
    }

    public void setLast_visit_date(Date last_visit_date) {
        this.last_visit_date = last_visit_date;
    }
}
