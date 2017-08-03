package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic Photo Reference.
 */
@Document(collection = "photos")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("url")
    private String url;

    @Field("state")
    private String state;

    @Field("reports")
    private List<String> reports;
    
    public Photo() {
    	reports = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getReports() {
		return reports;
	}

	public void setReports(List<String> reports) {
		this.reports = reports;
	}

	@Override
    public String toString() {
        return "Photo{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", state='" + getState() + "'" +
            ", reporst='" + getReports().size() + "'" +
            "}";
    }
}
