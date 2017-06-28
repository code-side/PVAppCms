package com.codeside.pvapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TicoStop entity.
 */
public class TicoStopDTO implements Serializable {

    private String id;

    private String name;

    private String historicalReview;

    private String coordinates;

    private String photo;

    private String address;

    private ProvinceRefDTO province;

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

    public String getHistoricalReview() {
        return historicalReview;
    }

    public void setHistoricalReview(String historicalReview) {
        this.historicalReview = historicalReview;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TicoStopDTO ticoStopDTO = (TicoStopDTO) o;
        if(ticoStopDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ticoStopDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TicoStopDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", historicalReview='" + getHistoricalReview() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince().toString() + "'" +
            "}";
    }
}
