package com.codeside.pvapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Province entity.
 */
public class ProvinceDTO implements Serializable {

    private String id;

    private String name;

    private String coordinates;

    private String history;

    private String culture;

    private String photo;

    private String cantons;

    private String emergencyContacts;

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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCantons() {
        return cantons;
    }

    public void setCantons(String cantons) {
        this.cantons = cantons;
    }

    public String getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(String emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProvinceDTO provinceDTO = (ProvinceDTO) o;
        if(provinceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provinceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvinceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", history='" + getHistory() + "'" +
            ", culture='" + getCulture() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", cantons='" + getCantons() + "'" +
            ", emergencyContacts='" + getEmergencyContacts() + "'" +
            "}";
    }
}
