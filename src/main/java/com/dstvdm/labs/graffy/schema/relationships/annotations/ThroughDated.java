package com.dstvdm.labs.graffy.schema.relationships.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dstvdm.labs.graffy.BaseModel;
import com.dstvdm.labs.graffy.schema.relationships.ModelThroughFrequency;

/**
 * For model "through" relationships where the "through" model instances are
 * date interval-specific (e.g. there is a new "through" model instance every
 * day, or every week).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ThroughDated {

  /**
   * The "through" model class.
   */
  Class<? extends BaseModel> model();
  
  /**
   * How often to create a new "through" model instance.
   */
  ModelThroughFrequency frequency() default ModelThroughFrequency.DAILY;
  
}
