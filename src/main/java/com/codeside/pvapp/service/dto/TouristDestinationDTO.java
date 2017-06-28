package com.codeside.pvapp.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A DTO for the TouristDestination entity.
 */
public class TouristDestinationDTO implements Serializable {

    private String id;

    private String name;

    private String coordinates;

    private String description;

    private List<String> photos = new ArrayList<>();

    private String address;

    private ProvinceRefDTO province;

    private AttributeRefDTO[] attributes;

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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProvinceRefDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceRefDTO province) {
        this.province = province;
    }

    public AttributeRefDTO[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeRefDTO[] attributes) {
        this.attributes = attributes;
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

        TouristDestinationDTO touristDestinationDTO = (TouristDestinationDTO) o;
        if(touristDestinationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristDestinationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TouristDestinationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", description='" + getDescription() + "'" +
            ", photos='" + getPhotos().size() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince().toString() + "'" +
            ", reviews='" + getReviews() + "'" +
            "}";
    }
}
