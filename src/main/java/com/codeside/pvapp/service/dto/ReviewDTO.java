package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * A DTO for Generic Review.
 */
public class ReviewDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String userName;
    private String userPhoto;
    private String comment;
    private int rating;
    private List<String> reports;
    private LocalDate commentDate;


    public ReviewDTO() {
    	reports = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
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
    public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewDTO touristDestination = (ReviewDTO) o;
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
