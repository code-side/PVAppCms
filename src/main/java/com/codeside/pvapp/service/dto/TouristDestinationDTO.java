package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A DTO for the TouristDestination entity.
 */
public class TouristDestinationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private CoordinateDTO coordinates;
    private String description;
    private List<PhotoDTO> photos;
    private String address;
    private ProvinceRefDTO province;
    private List<AttributeDTO> attributes;
    private List<ReviewDTO> reviews;
		private List<String> reports;

    public TouristDestinationDTO() {
    	photos = new ArrayList<>();
    	attributes = new ArrayList<>();
    	reviews = new ArrayList<>();
			reports =  new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public CoordinateDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinateDTO coordinates) {
        this.coordinates = coordinates;
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

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

		public List<String> getReports() {
				return reports;
		}

		public void setReports(List<String> reports) {
				this.reports = reports;
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
            ", attributes='" + getAttributes().size() + "'" +
            ", reviews='" + getReviews().size() + "'" +
            "}";
    }
}
