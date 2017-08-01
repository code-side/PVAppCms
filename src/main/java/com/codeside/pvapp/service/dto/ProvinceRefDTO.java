package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for Generic Province Reference.
 */
public class ProvinceRefDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String canton;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProvinceRefDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCanton() {
        return canton;
    }

    public ProvinceRefDTO canton(String canton) {
        this.canton = canton;
        return this;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProvinceRefDTO provinceRefDTO = (ProvinceRefDTO) o;
        if (provinceRefDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provinceRefDTO.getId());
    } 

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvinceRef{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", canton='" + getCanton() + "'" +
            "}";
    }
}
