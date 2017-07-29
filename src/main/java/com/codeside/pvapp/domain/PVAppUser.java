package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * A PVAppUser.
 */
@Document(collection = "pv_app_user")
public class PVAppUser implements Serializable {

    public static class TouristDestination implements Serializable {

        @Field("tourist_destination_id")
        private String touristDestinationId;

        private String name;

        public String getTouristDestinationId() {
          return this.touristDestinationId;
        }

        public void setTouristDestinationId(String touristDestinationId){
          this.touristDestinationId = touristDestinationId;
        }

        public String getName() {
          return this.name;
        }

        public void setName(String name){
          this.name = name;
        }

        @Override
        public String toString() {
          return "TouristDestination{" +
              "touristDestinationId=" + this.touristDestinationId +
              ", name='" + this.name + "'" +
              "}";
        }
    };

    public static class Achievement implements Serializable{

        private String name;
        private String description;
        private String progress;
        private String goal;
        private String logo;

        public String getName() {
      		return this.name;
      	}

      	public void setName(String name) {
      		this.name = name;
      	}

      	public String getDescription() {
      		return this.description;
      	}

      	public void setDescription(String description) {
      		this.description = description;
      	}

      	public String getProgress() {
      		return this.progress;
      	}

      	public void setProgress(String progress) {
      		this.progress = progress;
      	}

      	public String getGoal() {
      		return this.goal;
      	}

      	public void setGoal(String goal) {
      		this.goal = goal;
      	}

      	public String getLogo() {
      		return this.logo;
      	}

      	public void setLogo(String logo) {
      		this.logo = logo;
      	}

        @Override
        public String toString() {
          return "Achievement{" +
              "name=" + this.name +
              ", description='" + this.description + "'" +
              ", progress=" + this.progress +
              ", goal='" + this.goal + "'" +
              ", logo =" + this.logo +
              "}";
        }
    };

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
    private Photo photo;

    @Field("status")
    private Integer status;

    @Field("favorite_list")
    private List<TouristDestination> favoriteList = new ArrayList<>();

    @Field("achievements")
    private List<Achievement> achievements = new ArrayList<>();

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

    public Photo getPhoto() {
        return photo;
    }

    public PVAppUser photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
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

    public List<TouristDestination> getFavoriteList() {
        return favoriteList;
    }

    public PVAppUser favoriteList(List<TouristDestination> favoriteList) {
        this.favoriteList = favoriteList;
        return this;
    }

    public void setFavoriteList(List<TouristDestination> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public PVAppUser achievements(List<Achievement> achievements) {
        this.achievements = achievements;
        return this;
    }

    public void setAchievements(List<Achievement> achievements) {
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
            "}";
    }
}
