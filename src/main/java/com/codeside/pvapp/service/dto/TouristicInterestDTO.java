package com.codeside.pvapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TouristicInterest entity.
 */
public class TouristicInterestDTO implements Serializable {

    private String id;

    private String name;

    private String type;

    private String workingHours;

    private String contact;

    private String address;

    private String province;

    private String reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TouristicInterestDTO touristicInterestDTO = (TouristicInterestDTO) o;
        if(touristicInterestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristicInterestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TouristicInterestDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", workingHours='" + getWorkingHours() + "'" +
            ", contact='" + getContact() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", reviews='" + getReviews() + "'" +
            "}";
    }
}
