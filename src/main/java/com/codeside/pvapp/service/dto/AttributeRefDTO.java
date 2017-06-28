package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for Generic Province Reference.
 */
public class AttributeRefDTO implements Serializable {


    private String name;
    private String value;


    public String getName() {
        return name;
    }

    public AttributeRefDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public AttributeRefDTO value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "AttributeRefDTO{" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
