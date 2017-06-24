package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PVAppUser.
 */
@Document(collection = "pv_app_user")
public class PVAppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("registration_date")
    private LocalDate registrationDate;

    @Field("birthday")
    private LocalDate birthday;

    @Field("nationality")
    private String nationality;

    @Field("gender")
    private String gender;

    @Field("photo")
    private String photo;

    @Field("status")
    private Integer status;

    @Field("favorite_list")
    private String favoriteList;

    @Field("achievements")
    private String achievements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PVAppUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public PVAppUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public PVAppUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public PVAppUser registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public PVAppUser birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public PVAppUser nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public PVAppUser gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public PVAppUser photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public PVAppUser status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFavoriteList() {
        return favoriteList;
    }

    public PVAppUser favoriteList(String favoriteList) {
        this.favoriteList = favoriteList;
        return this;
    }

    public void setFavoriteList(String favoriteList) {
        this.favoriteList = favoriteList;
    }

    public String getAchievements() {
        return achievements;
    }

    public PVAppUser achievements(String achievements) {
        this.achievements = achievements;
        return this;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PVAppUser pVAppUser = (PVAppUser) o;
        if (pVAppUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pVAppUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PVAppUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", gender='" + getGender() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", status='" + getStatus() + "'" +
            ", favoriteList='" + getFavoriteList() + "'" +
            ", achievements='" + getAchievements() + "'" +
            "}";
    }
}
