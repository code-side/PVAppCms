package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * Generic Attribute Reference.
 */
@Document(collection = "attribute_ref")
public class AttributeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("value")
    private String value;

    @Field("type")
    private String type;

    @Field("idiom")
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

    public AttributeRef name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public AttributeRef value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public AttributeRef type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdiom() {
        return idiom;
    }

    public AttributeRef idiom(String idiom) {
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
