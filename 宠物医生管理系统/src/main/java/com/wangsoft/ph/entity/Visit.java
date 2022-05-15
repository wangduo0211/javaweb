package com.wangsoft.ph.entity;

public class Visit {
    private int id;
    private  int petId;
    private int vetID;
    private String visitdata;
    private String description;
    private String treatment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public String getVisitdata() {
        return visitdata;
    }

    public void setVisitdata(String visitdata) {
        this.visitdata = visitdata;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", petId=" + petId +
                ", vetID=" + vetID +
                ", visitdata='" + visitdata + '\'' +
                ", description='" + description + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
