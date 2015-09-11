package com.dstvdm.labs.graffy.schema;

import java.util.HashMap;
import java.util.Map;

import com.dstvdm.labs.graffy.BaseModel;

/**
 * Represents the schema for a single model.
 */
public class ModelSchema {
  
  protected final Class<? extends BaseModel> model;
  protected Map<String, ModelFieldMeta> fieldMeta = new HashMap<>();

  /**
   * Constructor
   * @param model The model class for which this schema is relevant.
   */
  public ModelSchema(Class<? extends BaseModel> model) {
    this.model = model;
  }

}
