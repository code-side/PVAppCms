package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A Province.
 */
@Document(collection = "province")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("coordinates")
    private String coordinates;

    @Field("history")
    private String history;

    @Field("culture")
    private String culture;

    @Field("photo")
    private String photo;

    @Field("cantons")
    private List<String> cantons = new ArrayList<>();

    @Field("emergency_contacts")
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

    public Province name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public Province coordinates(String coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getHistory() {
        return history;
    }

    public Province history(String history) {
        this.history = history;
        return this;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCulture() {
        return culture;
    }

    public Province culture(String culture) {
        this.culture = culture;
        return this;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getPhoto() {
        return photo;
    }

    public Province photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getCantons() {
        return cantons;
    }

    public Province cantons(List<String> cantons) {
        this.cantons = cantons;
        return this;
    }

    public void setCantons(List<String> cantons) {
        this.cantons = cantons;
    }

    public String getEmergencyContacts() {
        return emergencyContacts;
    }

    public Province emergencyContacts(String emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
        return this;
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
        Province province = (Province) o;
        if (province.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), province.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Province{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", history='" + getHistory() + "'" +
            ", culture='" + getCulture() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", cantons='" + getCantons().size() + "'" +
            ", emergencyContacts='" + getEmergencyContacts() + "'" +
            "}";
    }
}
