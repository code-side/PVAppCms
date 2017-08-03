package com.codeside.pvapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for Generic Coordinate Reference.
 */
public class CoordinateDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private double latitude;
    private double longitude;
    private double zoom;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getZoom() {
        return zoom;
    }
    
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "CoordinateDTO{" +
            "id=" + getLatitude() +
            ", name='" + getLongitude() + "'" +
            ", type='" + getZoom() + "'" +
            "}";
    }
}
