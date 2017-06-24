package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TicoStop.
 */
@Document(collection = "tico_stop")
public class TicoStop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("historical_review")
    private String historicalReview;

    @Field("coordinates")
    private String coordinates;

    @Field("photo")
    private String photo;

    @Field("address")
    private String address;

    @Field("province")
    private String province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TicoStop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHistoricalReview() {
        return historicalReview;
    }

    public TicoStop historicalReview(String historicalReview) {
        this.historicalReview = historicalReview;
        return this;
    }

    public void setHistoricalReview(String historicalReview) {
        this.historicalReview = historicalReview;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public TicoStop coordinates(String coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPhoto() {
        return photo;
    }

    public TicoStop photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public TicoStop address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public TicoStop province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
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
        TicoStop ticoStop = (TicoStop) o;
        if (ticoStop.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ticoStop.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TicoStop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", historicalReview='" + getHistoricalReview() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            "}";
    }
}
