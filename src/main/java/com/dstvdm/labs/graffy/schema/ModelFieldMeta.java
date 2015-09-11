package com.dstvdm.labs.graffy.schema;

import com.dstvdm.labs.graffy.BaseModel;
import com.orientechnologies.orient.core.metadata.schema.OType;

/**
 * Represents the field metadata for a single model field.
 */
public class ModelFieldMeta {
  
  /** The name of the field. */
  protected String name = null;
  /** What type of field is this? */
  protected ModelFieldType type = null;
  /** The OrientDB field type, if applicable. */
  protected OType orientType = null;
  /** If this field is a foreign key reference. */
  protected Class<? extends BaseModel> refersTo = null;

  public ModelFieldMeta() {
    // TODO Auto-generated constructor stub
  }

}
