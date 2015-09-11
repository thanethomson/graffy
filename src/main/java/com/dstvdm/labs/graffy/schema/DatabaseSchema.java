package com.dstvdm.labs.graffy.schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.dstvdm.labs.graffy.BaseModel;
import com.dstvdm.labs.graffy.interfaces.logging.Logger;

/**
 * Management class for the schema for the whole database.
 */
public class DatabaseSchema {
  
  protected Map<String, ModelSchema> modelSchemas = new HashMap<>();
  protected final Logger logger;

  /**
   * Constructor - builds up the database schema from the models in the given
   * package path.
   * @param packagePath The Java package path to scan for models (descendants of BaseModel).
   * @param logger A logger to use for debug/error logging.
   */
  public DatabaseSchema(String packagePath, Logger logger) {
    this.logger = logger;
    
    Reflections reflections = new Reflections(packagePath);
    Set<Class<? extends BaseModel>> models = reflections.getSubTypesOf(BaseModel.class);
    
    // run through all of the models, building up their metadata
    for (Class<? extends BaseModel> model: models) {
      modelSchemas.put(model.getName(), new ModelSchema(model, logger));
    }
  }
  
  /**
   * Getter to retrieve the schema for a particular model.
   * @param modelName The name of the model whose schema is to be retrieved.
   * @return
   */
  public ModelSchema get(String modelName) {
    return modelSchemas.get(modelName);
  }
  
  /**
   * Retrieves the schema for the specified model.
   * @param model The actual model class for which the schema should be retrieved.
   * @return
   */
  public ModelSchema get(Class<? extends BaseModel> model) {
    return modelSchemas.get(model.getName());
  }
 
}
