package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

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
    private Coordinate coordinates;

    @Field("description")
    private String description;

    @Field("photos")
    private List<Photo> photos;

    @Field("address")
    private String address;

    @Field("province")
    private ProvinceRef province;

    @Field("attributes")
    private List<Attribute> attributes;

    @Field("reviews")
    private List<Review> reviews;

    public TouristDestination() {
    	photos = new ArrayList<>();
    	attributes = new ArrayList<>();
    	reviews = new ArrayList<>();
    }
    
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

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public TouristDestination coordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(Coordinate coordinates) {
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public TouristDestination photos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public void setPhotos(List<Photo> photos) {
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public TouristDestination attributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public TouristDestination reviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public void setReviews(List<Review> reviews) {
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
            ", photos='" + getPhotos().size() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince().toString() + "'" +
            ", attributes='" + getAttributes().size() + "'" +
            ", reviews='" + getReviews().size() + "'" +
            "}";
    }
}
