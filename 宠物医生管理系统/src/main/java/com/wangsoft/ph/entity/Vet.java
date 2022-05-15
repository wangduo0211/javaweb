package com.wangsoft.ph.entity;

import java.util.ArrayList;
import java.util.List;
public class Vet {
    private int id;
    private String name;
    private List<Speciality> specialities =new ArrayList<Speciality>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialities=" + specialities +
                '}';
    }
}

