package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TouristDestination.
 */
@Document(collection = "tourist_destination")
public class TouristDestination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("coordinates")
    private String coordinates;

    @Field("description")
    private String description;

    @Field("photos")
    private String photos;

    @Field("address")
    private String address;

    @Field("province")
    private ProvinceRef province;

    @Field("attributes")
    private AttributeRef[] attributes;

    @Field("reviews")
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

    public TouristDestination name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public TouristDestination coordinates(String coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public TouristDestination description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotos() {
        return photos;
    }

    public TouristDestination photos(String photos) {
        this.photos = photos;
        return this;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getAddress() {
        return address;
    }

    public TouristDestination address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProvinceRef getProvince() {
        return province;
    }

    public TouristDestination province(ProvinceRef province) {
        this.province = province;
        return this;
    }

    public void setProvince(ProvinceRef province) {
        this.province = province;
    }

    public AttributeRef[] getAttributes() {
        return attributes;
    }

    public TouristDestination attributes(AttributeRef[] attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(AttributeRef[] attributes) {
        this.attributes = attributes;
    }

    public String getReviews() {
        return reviews;
    }

    public TouristDestination reviews(String reviews) {
        this.reviews = reviews;
        return this;
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
        TouristDestination touristDestination = (TouristDestination) o;
        if (touristDestination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristDestination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TouristDestination{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", description='" + getDescription() + "'" +
            ", photos='" + getPhotos() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince().toString() + "'" +
            ", reviews='" + getReviews() + "'" +
            "}";
    }
}
