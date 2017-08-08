package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A TouristicInterest.
 */
@Document(collection = "touristic_interest")
public class TouristicInterest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("type")
    private String type;

    @Field("working_hours")
    private String workingHours;

    @Field("contact")
    private String contact;

    @Field("address")
    private String address;

    @Field("province")
    private ProvinceRef province;

    @Field("reviews")
    private List<Review> reviews;

    @Field("photo")
    private String photo;

    @Field("coordinates")
    private Coordinate coordinates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TouristicInterest name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public TouristicInterest type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public TouristicInterest workingHours(String workingHours) {
        this.workingHours = workingHours;
        return this;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getContact() {
        return contact;
    }

    public TouristicInterest contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public TouristicInterest address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProvinceRef getProvince() {
        return province;
    }

    public TouristicInterest province(ProvinceRef province) {
        this.province = province;
        return this;
    }

    public void setProvince(ProvinceRef province) {
        this.province = province;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public TouristicInterest reviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPhoto() {
        return photo;
    }

    public TouristicInterest photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public TouristicInterest coordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TouristicInterest touristicInterest = (TouristicInterest) o;
        if (touristicInterest.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristicInterest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TouristicInterest{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", workingHours='" + getWorkingHours() + "'" +
            ", contact='" + getContact() + "'" +
            ", address='" + getAddress() + "'" +
            ", photoURL='" + getPhoto()  + "'" +
            ", coordinates='" + getCoordinates()  + "'" +
            "}";
    }
}
