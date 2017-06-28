package com.codeside.pvapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
* A DTO for Generic EmergencyContacts Reference
*/

public class EmergencyContactRefDTO implements Serializable {

  private String name;

  private String type;

  private String contact;

  private String workingHours;


  public String getName(){
    return name;
  }

  public EmergencyContactRefDTO name (String name){
    this.name=name;
    return this;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getType(){
    return type;
  }

  public EmergencyContactRefDTO type (String type){
    this.type = type;
    return this;
  }

  public void setType(String type){
    this.type = type;
  }

  public String getContact(){
    return contact;
  }

  public EmergencyContactRefDTO contact (String contact){
    this.contact = contact;
    return this;
  }

  public void setContact(String contact){
    this.contact = contact;
  }

  public String getWorkingHours(){
    return workingHours;
  }

  public EmergencyContactRefDTO workingHours(String workingHours){
    this.workingHours = workingHours;
    return this;
  }
  public void setWorkingHours(String workingHours){
    this.workingHours = workingHours;
  }


  @Override
  public String toString(){
    return "EmergencyContactRef{" +
    ", name= '" + getName() + "'" +
    ", type= '" + getType() + "'" +
    ", contact= '" + getContact() + "'" +
    ", workingHours= '" + getWorkingHours() + "'" +
    "}";
  }
}
