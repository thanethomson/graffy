package com.dstvdm.labs.graffy.schema;

/**
 * Helps to identify different types of fields in our schema.
 */
public enum ModelFieldType {
  VALUE,               ///< A simple value, like a string, integer, boolean, etc.
  REL_ONE_TO_ONE,      ///< A one-to-one relationship.
  REL_MANY_TO_ONE,     ///< A many-to-one relationship.
  REL_ONE_TO_MANY,     ///< A one-to-many relationship.
  REL_MANY_TO_MANY     ///< A many-to-many relationship.
}
