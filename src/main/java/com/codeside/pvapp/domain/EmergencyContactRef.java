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
  private String working_hours;

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
  public String getWorking_Hours(){
    return working_hours;
  }
  public EmergencyContactRef working_hours(String working_hours){
    this.working_hours = working_hours;
    return this;
  }
  public void setWorking_Hours(String working_hours){
    this.working_hours = working_hours;
  }


  @Override
  public String toString(){
    return "EmergencyContactRef{" +
    ", name= '" + getName() + "'" +
    ", type= '" + getType() + "'" +
    ", contact= '" + getContact() + "'" +
    ", working_hours= '" + getWorking_Hours() + "'" +
    "}";
  }

}
