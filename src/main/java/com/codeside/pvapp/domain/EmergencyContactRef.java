package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
* Generic EmergencyContacts Reference
*/

@Document(collection = "emergency_contact_ref")
public class EmergencyContactRef implements Serializable {

  private static final long serialVersionUID = 1L;
  @Field("name")
  private String name;

  @Field("type")
  private String type;

  @Field("contact")
  private String contact;

  @Field("working_hours")
  private String workingHours;

  @Field("coordinates")
  private String coordinates;

  public String getName(){
    return name;
  }

  public EmergencyContactRef name(String name){
    this.name = name;
    return this;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getType(){
    return type;
  }

  public EmergencyContactRef type(String type){
    this.type = type;
    return this;
  }

  public void setType(String type){
    this.type = type;
  }

  public String getContact(){
    return contact;
  }

  public EmergencyContactRef contact (String contact){
    this.contact = contact;
    return this;
  }

  public void setContact(String contact){
    this.contact = contact;
  }

  public String getWorkingHours(){
    return workingHours;
  }

  public EmergencyContactRef workingHours(String workingHours){
    this.workingHours = workingHours;
    return this;
  }

  public void setWorkingHours(String workingHours){
    this.workingHours = workingHours;
  }

  public String getCoordinates(){
    return coordinates;
  }

  public EmergencyContactRef coordinates (String coordinates){
    this.coordinates = coordinates;
    return this;
  }

  public void setCoordinates(String coordinates){
    this.coordinates = coordinates;
  }

  @Override
  public String toString(){
    return "EmergencyContactRef{" +
    ", name= '" + getName() + "'" +
    ", type= '" + getType() + "'" +
    ", contact= '" + getContact() + "'" +
    ", workingHours= '" + getWorkingHours() + "'" +
    ", coordinates= '" + getCoordinates() + "'" +
    "}";
  }

}
