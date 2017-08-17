package com.codeside.pvapp.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A DTO for the TouristDestination entity.
 */
public class TouristDestinationRefDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private List<PhotoDTO> photos = new ArrayList<>();


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

    public String getDescription() {
    return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TouristDestinationRefDTO touristDestinationRefDTO = (TouristDestinationRefDTO) o;
        if(touristDestinationRefDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristDestinationRefDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TouristDestinationRefDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", photos='" + getPhotos().size() + "'" +
            "}";
    }
}
