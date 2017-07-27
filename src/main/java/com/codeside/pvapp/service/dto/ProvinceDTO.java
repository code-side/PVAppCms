package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

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

    private String idiom;

    private List<String> cantons = new ArrayList<>();

    private EmergencyContactRefDTO[] emergencyContacts;

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

    public String getIdiom() {
        return idiom;
    }

    public ProvinceDTO idiom(String idiom) {
        this.idiom = idiom;
        return this;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public List<String> getCantons() {
        return cantons;
    }

    public void setCantons(List<String> cantons) {
        this.cantons = cantons;
    }

    public EmergencyContactRefDTO[] getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(EmergencyContactRefDTO[] emergencyContacts) {
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
            ", idiom='" + getIdiom() + "'" +
            ", cantons='" + getCantons().size() + "'" +
            "}";
    }
}
