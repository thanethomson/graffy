package com.dstvdm.labs.graffy.schema.relationships.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dstvdm.labs.graffy.BaseModel;

/**
 * Annotation for model relationships where there is a "through" model.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Through {

  /**
   * The "through" model.
   */
  Class<? extends BaseModel> model();
  
}
