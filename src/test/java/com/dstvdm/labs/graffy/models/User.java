package com.dstvdm.labs.graffy.models;

import com.dstvdm.labs.graffy.BaseModel;

/**
 * Represents a user and that user's details.
 */
public class User extends BaseModel {

  private static final long serialVersionUID = 8451765302174990011L;
  
  private String firstName = null;
  private String lastName = null;
  private String email = null;

  
  public User() {
  }

  public User(String id) {
    super(id);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
