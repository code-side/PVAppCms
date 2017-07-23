package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for Generic Attribute Reference.
 */
public class AttributeRefDTO implements Serializable {

    private String id;
    private String name;
    private String value;
    private String type;
    private String idiom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getType() {
        return type;
    }

    public AttributeRefDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdiom() {
        return idiom;
    }

    public AttributeRefDTO idiom(String idiom) {
        this.idiom = idiom;
        return this;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    @Override
    public String toString() {
        return "AttributeRef{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", idiom='" + getIdiom() + "'" +
            "}";
    }
}
