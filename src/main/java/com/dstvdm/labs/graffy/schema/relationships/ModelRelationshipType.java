package com.dstvdm.labs.graffy.schema.relationships;

/**
 * The various ways in which we can represent a relationship between two model
 * instances.
 */
public enum ModelRelationshipType {
  DIRECT,        ///< A simple, direct link between two model instances.
  THROUGH,       ///< A "through" link, where there is one intermediate model instance between the source and destination.
  THROUGH_DATED  ///< A "through" link, but where there are multiple date-based intermediate model instances. 
}
