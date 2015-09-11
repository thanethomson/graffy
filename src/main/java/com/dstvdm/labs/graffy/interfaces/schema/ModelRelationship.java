package com.dstvdm.labs.graffy.interfaces.schema;

/**
 * A generic interface for relationships between two model classes.
 */
public interface ModelRelationship {
  
  /**
   * Should attempt to load all of the relevant parts of the relationship from
   * the database into memory.
   * @return A self-reference for chaining of commands.
   * @throws Throwable
   */
  public ModelRelationship load() throws Throwable;
  
  /**
   * Should connect all of the required edges between the relevant vertices to
   * facilitate this relationship.
   * @throws Throwable
   */
  public void connect() throws Throwable;

}
