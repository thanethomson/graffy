package com.dstvdm.labs.graffy.interfaces.logging;

/**
 * Provides logging interfaces.
 */
public interface LoggerFactory {

  /**
   * Provides a new logger using the given namespace.
   * @param namespace The logging namespace for the new logger.
   * @return An instance and implementation of a logger.
   */
  public Logger provide(String namespace);
  
}
