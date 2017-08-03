package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for Generic Photo Reference.
 */
public class PhotoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String url;
    private String state;
    private String description;
    private List<String> reports;
    
    public PhotoDTO() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getReports() {
		return reports;
	}

	public void setReports(List<String> reports) {
		this.reports = reports;
	}

	@Override
    public String toString() {
        return "PhotoDTO{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", state='" + getState() + "'" +
            ", description='" + getDescription() + "'" +
            ", reports='" + getReports().size() + "'" +
            "}";
    }
}
