package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A Review.
 */
@Document(collection = "reviews")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("comment")
    private String comment;
    
    @Field("rating")
    private int rating;
    
    @Field("reports")
    private List<String> reports;
    
    public Review() {
    	reports = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<String> getReports() {
		return reports;
	}

	public void setReports(List<String> reports) {
		this.reports = reports;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review touristDestination = (Review) o;
        if (touristDestination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), touristDestination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", rating='" + getRating() + "'" +
            ", reports='" + getReports().size() + "'" +
            "}";
    }
}
