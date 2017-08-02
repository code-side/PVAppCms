package com.codeside.pvapp.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;

/**
 * A DTO for the PVAppUser entity.
 */
public class PVAppUserDTO implements Serializable {

	public static class TouristDestination implements Serializable {

        private static final long serialVersionUID = 1L;
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

    public static class Achievement implements Serializable {
        private static final long serialVersionUID = 1L;
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
	private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private LocalDate birthday;
    private String nationality;
    private String gender;
    private PhotoDTO photo;
    private Integer status;
    private List<TouristDestination> favoriteList;
    private List<Achievement> achievements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TouristDestination> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<TouristDestination> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<Achievement> getAchievements() {
        return achievements;
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

        PVAppUserDTO pVAppUserDTO = (PVAppUserDTO) o;
        if(pVAppUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pVAppUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PVAppUserDTO{" +
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
