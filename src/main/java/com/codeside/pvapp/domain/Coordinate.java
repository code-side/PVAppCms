package com.codeside.pvapp.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;

/**
 * A Coordinate.
 */
@Document(collection = "coordinates")
public class Coordinate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Field("latitude")
    private double latitude;

    @Field("longitude")
    private double longitude;

    @Field("zoom")
    private double zoom;

    public double getLatitude() {
        return latitude;
    }

    public Coordinate latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Coordinate longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getZoom() {
        return zoom;
    }

    public Coordinate zoom(double zoom) {
        this.zoom = zoom;
        return this;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "id=" + getLatitude() +
            ", name='" + getLongitude() + "'" +
            ", type='" + getZoom() + "'" +
            "}";
    }
}
