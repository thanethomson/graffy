package com.dstvdm.labs.graffy;

import java.io.Serializable;

/**
 * The base model from which all other database models must derive.
 */
public abstract class BaseModel implements Serializable {
  
  private static final long serialVersionUID = 3886217343314173061L;
  
  /** The database RID of this model. */
  private String id = null;

  public BaseModel() {}
  
  public BaseModel(String id) {
    setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
