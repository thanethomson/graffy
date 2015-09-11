package com.dstvdm.labs.graffy.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.dstvdm.labs.graffy.BaseModel;
import com.dstvdm.labs.graffy.interfaces.logging.Logger;
import com.dstvdm.labs.graffy.schema.relationships.ModelRelationshipType;
import com.dstvdm.labs.graffy.schema.relationships.ModelThroughFrequency;
import com.dstvdm.labs.graffy.schema.relationships.annotations.Through;
import com.dstvdm.labs.graffy.schema.relationships.annotations.ThroughDated;
import com.orientechnologies.orient.core.metadata.schema.OType;

/**
 * Represents the field metadata for a single model field.
 */
public class ModelFieldMeta {

  /** The model to which this metadata refers. */
  protected Class<? extends BaseModel> model = null;
  /** The name of the field. */
  protected String name = null;
  /** The actual field itself. */
  protected Field field = null;
  
  protected String capitalisedName = null;
  /** What type of field is this? */
  protected ModelFieldType type = null;
  /** The OrientDB field type, if applicable. */
  protected OType orientType = null;
  
  /** If this is a relationship, the relationship destination model. */
  protected Class<? extends BaseModel> destModel = null;
  /** If this is a relationship, and it has a "through" relationship, the "through" model. */
  protected Class<? extends BaseModel> throughModel = null;
  /** The relationship type, if applicable. */
  protected ModelRelationshipType relationshipType = null;
  /** If this is a dated "through" model, this will tell us its frequency. */
  protected ModelThroughFrequency throughFrequency = null;
  
  protected Method getter = null, setter = null;
  
  /** Is this field actually tracked? Fields for which there are errors will not be tracked. */
  protected boolean isTracked = true;
  
  private final Logger logger;

  /**
   * Builds up the metadata from the given model and field name.
   * @param model The model in question.
   * @param name The name of the field whose metadata is to be extracted.
   * @param field The field itself.
   * @param logger A logger for debugging/error logging.
   */
  public ModelFieldMeta(Class<? extends BaseModel> model, String name, Field field, Logger logger) {
    this.model = model;
    this.name = name;
    this.field = field;
    this.capitalisedName = name.substring(0, 1).toUpperCase() + name.substring(1);
    this.logger = logger;
    
    extractMetadata();
  }

  /**
   * Actually performs the metadata extraction using Java reflections.
   */
  @SuppressWarnings("unchecked")
  protected void extractMetadata() {
    // check that the field has a getter and a setter
    try {
      getter = model.getMethod(String.format("get%s", capitalisedName));
    } catch (NoSuchMethodException e) {
      logger.warn(String.format("Unable to retrieve getter for %s.%s", model.getName(), name));
      getter = null;
    } catch (SecurityException e) {
      logger.warn(String.format("Unable to retrieve getter for %s.%s", model.getName(), name));
      getter = null;
    }
    
    // no getter = error
    if (getter == null)
      return;
    
    Class<?> returnType = getter.getReturnType();
    
    try {
      setter = model.getMethod(String.format("set%s", capitalisedName), returnType);
    } catch (NoSuchMethodException e) {
      logger.warn(String.format("Unable to retrieve setter for %s.%s", model.getName(), name));
      setter = null;
    } catch (SecurityException e) {
      logger.warn(String.format("Unable to retrieve setter for %s.%s", model.getName(), name));
      setter = null;
    }
    
    // no setter = error
    if (setter == null)
      return;
    
    // now check the return type...
    
    // if it's another model, this must be a relationship
    if (isModel(returnType)) {
      
      destModel = (Class<? extends BaseModel>)returnType;
      
      // check if it has a particular relational annotation
      if (field.getAnnotation(OneToOne.class) != null) {
        type = ModelFieldType.REL_ONE_TO_ONE;
      } else if (field.getAnnotation(OneToMany.class) != null) {
        type = ModelFieldType.REL_ONE_TO_MANY;
      } else if (field.getAnnotation(ManyToOne.class) != null) {
        type = ModelFieldType.REL_MANY_TO_ONE;
      } else if (field.getAnnotation(ManyToMany.class) != null) {
        type = ModelFieldType.REL_MANY_TO_MANY;
      } else {
        logger.error(String.format("No field annotations found for field %s.%s, skipping", model.getName(), name));
        isTracked = false;
      }
      
      // if we're still going to track this field
      if (isTracked) {
        // now check if it has a "through" relationship
        Through through = field.getAnnotation(Through.class);
        
        if (through != null) {
          relationshipType = ModelRelationshipType.THROUGH;
          throughModel = through.model();
        } else {
          ThroughDated throughDated = field.getAnnotation(ThroughDated.class);
          
          if (throughDated != null) {
            relationshipType = ModelRelationshipType.THROUGH_DATED;
            throughModel = throughDated.model();
            throughFrequency = throughDated.frequency();
          } else {
            relationshipType = ModelRelationshipType.DIRECT;
          }
        }
        
        logger.debug(String.format("Relationship discovered from %s.%s to %s%s",
            model.getName(),
            name,
            destModel.getName(),
            (throughModel != null) ? String.format(", through %s%s", throughModel.getName(),
                (throughFrequency != null) ? String.format(" (%s)", throughFrequency.toString()) : "") : ""));
      }
    } 
    else {
      type = ModelFieldType.VALUE;
      
      if (returnType.equals(String.class)) {
        orientType = OType.STRING;
      } else if (returnType.equals(Integer.class)) {
        orientType = OType.INTEGER;
      } else if (returnType.equals(Boolean.class)) {
        orientType = OType.BOOLEAN;
      } else if (returnType.equals(Long.class)) {
        orientType = OType.LONG;
      } else if (returnType.equals(Date.class)) {
        orientType = OType.DATETIME;
      } else if (returnType.equals(Double.class)) {
        orientType = OType.DOUBLE;
      } else {
        logger.error(String.format("Unrecognised field type: %s, skipping", returnType.getName()));
        isTracked = false;
      }
      
      if (orientType != null) {
        logger.debug(String.format("Simple value field type %s.%s = %s", model.getName(), name, orientType.toString()));
      }
    }
  }
  
  /**
   * Internal helper function to determine whether or not the specified class
   * is a descendent of our base model class.
   * @param cls The class in question.
   * @return true if it is a model, false if not.
   */
  protected static boolean isModel(Class<?> cls) {
    if (cls.equals(BaseModel.class))
      return true;
    
    // trace up the inheritance hierarchy
    if (cls.getSuperclass() != null)
      return isModel(cls.getSuperclass());
    
    return false;
  }
  
  public boolean getTracked() {
    return isTracked;
  }
  
  public ModelFieldType getType() {
    return type;
  }
  
  public OType getOrientType() {
    return orientType;
  }
  
  public Class<? extends BaseModel> getDestModel() {
    return destModel;
  }
  
  public Class<? extends BaseModel> getThroughModel() {
    return throughModel;
  }
  
  public ModelRelationshipType getRelationshipType() {
    return relationshipType;
  }
  
  public ModelThroughFrequency getThroughFrequency() {
    return throughFrequency;
  }

}
