package com.dstvdm.labs.graffy.schema;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dstvdm.labs.graffy.BaseModel;
import com.dstvdm.labs.graffy.interfaces.logging.Logger;

/**
 * Represents the schema for a single model.
 */
public class ModelSchema {
  
  protected final Class<? extends BaseModel> model;
  protected List<Field> fields = new ArrayList<>();
  protected Map<String, ModelFieldMeta> fieldMeta = new HashMap<>();
  
  protected final Logger logger;

  /**
   * Constructor
   * @param model The model class for which this schema is relevant.
   */
  public ModelSchema(Class<? extends BaseModel> model, Logger logger) {
    this.model = model;
    this.logger = logger;
    
    extractMetadata();
  }
  
  /**
   * Extracts all of the fields for the current model and its parent classes.
   */
  protected void getFields() {
    Class<?> curClass = model;
    
    // keep going up the hierarchy until we reach the base model
    while (!curClass.equals(BaseModel.class)) {
      fields.addAll(Arrays.asList(curClass.getDeclaredFields()));
      curClass = curClass.getSuperclass();
    }
    
    logger.debug(String.format("Extracted %d field(s) from %s", fields.size(), model.getName()));
  }
  
  
  protected void extractMetadata() {
    getFields();
    // build up all of the fields' metadata
    for (Field field: fields) {
      fieldMeta.put(field.getName(), new ModelFieldMeta(model, field.getName(), field, logger));
    }
  }
  
  
  /**
   * Getter for field metadata.
   * @param fieldName The field whose metadata is to be retrieved.
   * @return
   */
  public ModelFieldMeta getFieldMeta(String fieldName) {
    return fieldMeta.get(fieldName);
  }

}
