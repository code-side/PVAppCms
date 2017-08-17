package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for Generic Photo Reference.
 */
public class ReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idUser;
    private String idDestination;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

	@Override
    public String toString() {
        return "ReportDTO{" +
            "idUser=" + getIdUser() +
            "}";
    }
}
