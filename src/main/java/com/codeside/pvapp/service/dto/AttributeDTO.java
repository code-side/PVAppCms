package com.codeside.pvapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for Generic Attribute Reference.
 */
public class AttributeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
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

    public AttributeDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public AttributeDTO value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public AttributeDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdiom() {
        return idiom;
    }

    public AttributeDTO idiom(String idiom) {
        this.idiom = idiom;
        return this;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    @Override
    public String toString() {
        return "AttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", idiom='" + getIdiom() + "'" +
            "}";
    }
}
