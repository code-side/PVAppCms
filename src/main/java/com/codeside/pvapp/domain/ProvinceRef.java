package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * Generic Province Reference.
 */
@Document(collection = "province_ref")
public class ProvinceRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("canton")
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

    public ProvinceRef name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCanton() {
        return canton;
    }

    public ProvinceRef canton(String canton) {
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
        ProvinceRef provinceRef = (ProvinceRef) o;
        if (provinceRef.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provinceRef.getId());
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
